package me.harry.iam.infrastructure.filter;

import java.io.IOException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoggingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final ContentCachingRequestWrapper req = new ContentCachingRequestWrapper(request);

        long beginTime = System.nanoTime();
        Throwable error = null;

        try {
            filterChain.doFilter(req, response);
        } catch (IOException | ServletException | RuntimeException ex) {
            error = ex;
        } finally {

            logger.info(req.getRequestURI());

            if (error != null) {
                logger.info(error);
            }
        }

        long finishTime = System.nanoTime();
        double elapsedTime = Math.round((finishTime - beginTime) / 1000000.0 * 100.0) / 100.0;
    }
}
