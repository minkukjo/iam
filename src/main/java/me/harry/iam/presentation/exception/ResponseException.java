package me.harry.iam.presentation.exception;

import java.util.Objects;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseException extends Exception {

    private int status;

    private Integer code;

    private String message;

    private ResponseException(ResponseException responseException) {
        this.status = responseException.status;
        this.code = responseException.code;
        this.message = responseException.message;
    }

    public ResponseException(HttpStatus httpStatus, Integer code, String message) {
        this.status = httpStatus.value();
        this.code = Objects.requireNonNullElseGet(code, httpStatus::value);
        this.message = Objects.requireNonNullElseGet(message, httpStatus::getReasonPhrase);
    }

    @Override
    public ResponseException clone() {
        return new ResponseException(this);
    }
}
