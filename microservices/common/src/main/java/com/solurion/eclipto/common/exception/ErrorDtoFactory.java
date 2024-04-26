package com.solurion.eclipto.common.exception;

import com.solurion.eclipto.common.dto.ErrorDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;

public class ErrorDtoFactory {
    public static ErrorDto initErrorDto(HttpStatus status, HttpServletRequest request) {
        return new ErrorDto()
                .status(status.value())
                .error(status.getReasonPhrase())
                .path(request.getServletPath())
                .timestamp(OffsetDateTime.now());
    }
}
