package com.example.lab04.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 404 - không tìm thấy dữ liệu
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<ApiError>(
                new ApiError(404, ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    // 400 - dữ liệu không hợp lệ
    @ExceptionHandler(BusinessValidationException.class)
    public ResponseEntity<ApiError> handleValidation(BusinessValidationException ex) {
        return new ResponseEntity<ApiError>(
                new ApiError(400, ex.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    // 409 - xung đột dữ liệu
    @ExceptionHandler(BusinessConflictException.class)
    public ResponseEntity<ApiError> handleConflict(BusinessConflictException ex) {
        return new ResponseEntity<ApiError>(
                new ApiError(409, ex.getMessage()),
                HttpStatus.CONFLICT
        );
    }

    // 500 - lỗi hệ thống
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleOther(Exception ex) {
        return new ResponseEntity<ApiError>(
                new ApiError(500, ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}