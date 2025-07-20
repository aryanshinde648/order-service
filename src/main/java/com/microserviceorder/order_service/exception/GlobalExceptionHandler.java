package com.microserviceorder.order_service.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(CustomException.class)
    ResponseEntity<String> handleCustomException(CustomException e) {
        ResponseEntity<String> ex = ResponseEntity.badRequest().body("CustomException : " + e.getMessage());
        return ex;
    }

    @ExceptionHandler(OrderNotFoundException.class)
    ResponseEntity<String> handleOrderNotFoundException(OrderNotFoundException e) {
        ResponseEntity<String> ex = ResponseEntity.badRequest().body("OrderNotFoundException : " + e.getMessage());
        return ex;
    }
}
