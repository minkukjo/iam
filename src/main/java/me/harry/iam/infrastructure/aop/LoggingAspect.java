package me.harry.iam.infrastructure.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger("AOP");

    /**
     * @GetMapping 설정된 메소드 또는 클래스 설정
     * GetMapping 노테이션이 설정된 특정 클래스/메소드에만 AspectJ가 적용됨.
     */
    //  aspectJ를 적용할 타겟을 정의해준다. 전체 컨트롤러의 함수대상, 특정 어노테이션을 설정한 함수대상
    //  특정 메소드 대상 등 개발자가 적용하길 원하는 범위를 정의하는 어노테이션
    @Pointcut("@annotation(Logging)")
    public void GetMapping() {
    }

    /**
     * @param joinPoint
     */
    // aspectJ를 적용할 타겟 메소드가 실행되기 '전' 수행됨
    @Before("GetMapping()")
    public void before(JoinPoint joinPoint) {
        LOGGER.info("=====================AspectJ TEST  : Before Logging Start=====================");
        LOGGER.info("=====================AspectJ TEST  : Before Logging End=====================");
    }

    /**
     * @param joinPoint
     * @param result
     */
    // aspectJ를 적용할 타겟 메소드가 실행된 '후' 수행됨 (제일 마지막에 수행됨)
    @AfterReturning(pointcut = "GetMapping()", returning = "result")
    public void AfterReturning(JoinPoint joinPoint, Object result) {
        LOGGER.info("=====================AspectJ TEST  : AfterReturning Logging Start=====================");
        LOGGER.info("=====================AspectJ TEST  : AfterReturning Logging END=====================");
    }

    /**
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    //  aspectJ를 적용할 타겟 메소드 실행 전 , 후 처리를 모두 할 수 있음
//    @Around("GetMapping()")
//    public Object Around(ProceedingJoinPoint joinPoint) throws Throwable {
//        LOGGER.info("=====================AspectJ TEST  : Around Logging Start=====================");
//        try {
//            Object result = joinPoint.proceed();
//            LOGGER.info("=====================AspectJ TEST  : Around Logging END=====================");
//            return result;
//        } catch (Exception e) {
//            LOGGER.error("=====================AspectJ Around Exception=====================");
//            LOGGER.error(e.toString());
//            return null;
//        }
//    }

}