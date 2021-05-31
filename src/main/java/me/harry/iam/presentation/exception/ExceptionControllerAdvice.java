package me.harry.iam.presentation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(ResponseException.class)
    public ResponseEntity<ErrorResponse> getException(HttpServletRequest request, ResponseException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getCode(), e.getStatus(), e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getStatus()));
    }
}
