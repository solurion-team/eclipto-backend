package com.solurion.eclipto.common.exception;

import com.solurion.eclipto.common.dto.ErrorDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorDto> handleException(Exception exception, HttpServletRequest request) {
        var httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        var body = ErrorDtoFactory.initErrorDto(httpStatus, request);

        if (!exception.getMessage().isBlank()) {
            body.message(exception.getMessage());
        }
        exception.printStackTrace();
        return ResponseEntity.status(httpStatus).body(body);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorDto> handleResponseStatusException(ResponseStatusException exception, HttpServletRequest request) {
        var httpStatus = HttpStatus.valueOf(exception.getBody().getStatus());
        var body = ErrorDtoFactory.initErrorDto(httpStatus, request)
                .message(exception.getReason());
        return ResponseEntity.status(httpStatus).body(body);
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<ErrorDto> handleAuthenticationServiceException(InternalAuthenticationServiceException exception, HttpServletRequest request) {
        if (exception.getCause() instanceof ResponseStatusException) {
            return handleResponseStatusException((ResponseStatusException) exception.getCause(), request);
        }
        return handleException(exception, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception exception, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request
    ) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(statusCode)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, exception, WebRequest.SCOPE_REQUEST);
        }

        var error = ErrorDtoFactory.initErrorDto((HttpStatus) statusCode, ((ServletWebRequest) request).getRequest())
                .error(exception.getMessage());
        return ResponseEntity.status(statusCode).body(error);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDto> handleConstraintViolationException(
            ConstraintViolationException exception, HttpServletRequest request
    ) {
        var httpStatus = HttpStatus.BAD_REQUEST;
        var body = ErrorDtoFactory.initErrorDto(httpStatus, request);

        var violations = exception.getConstraintViolations();
        if (violations != null) {
            String message = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("; "));
            body.message(message);
        }

        return ResponseEntity.status(httpStatus).body(body);
    }
}
