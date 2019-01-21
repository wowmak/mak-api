package org.mak.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.security.InvalidParameterException;

@ControllerAdvice
public class MakExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ NullPointerException.class, IllegalArgumentException.class, RuntimeException.class})
    public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {
        logger.error("Exception:"+ex);
        final String bodyOfResponse = "Incorrect Payload";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler({ DuplicateUsernameException.class})
    public ResponseEntity<Object> handleDuplicateUsername(final RuntimeException ex, final WebRequest request) {
        logger.error("Exception:"+ex);
        final String bodyOfResponse = "Username already exits";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }


}
