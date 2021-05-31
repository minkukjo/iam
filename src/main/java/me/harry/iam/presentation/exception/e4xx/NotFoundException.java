package me.harry.iam.presentation.exception.e4xx;

import me.harry.iam.presentation.exception.ResponseDefinition;
import me.harry.iam.presentation.exception.ResponseException;
import org.springframework.http.HttpStatus;

public enum NotFoundException implements ResponseDefinition {
    POST(HttpStatus.BAD_REQUEST, NotFoundException.NOT_FOUND_CODE, "Not found post");

    private static final int NOT_FOUND_CODE = 404;

    private final ResponseException responseException;

    NotFoundException(HttpStatus status, Integer code, String message) {
        this.responseException = new ResponseException(status, code, message);
    }

    @Override
    public ResponseException getResponseException() {
        return responseException;
    }
}
