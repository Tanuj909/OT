package com.ot.exception;

import com.ot.dto.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse<Object>> handleApiException(
            ApiException ex,
            HttpServletRequest request) {

        log.error("API Exception: {}", ex.getMessage());

        ApiResponse<Object> response =
                ApiResponse.error(ex.getMessage(), request.getRequestURI());

        return ResponseEntity
                .status(ex.getStatus())
                .body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGenericException(
            Exception ex,
            HttpServletRequest request) {

        log.error("Unexpected error", ex);

        ApiResponse<Object> response =
                ApiResponse.error("Internal Server Error", request.getRequestURI());

        return ResponseEntity
                .internalServerError()
                .body(response);
    }
}