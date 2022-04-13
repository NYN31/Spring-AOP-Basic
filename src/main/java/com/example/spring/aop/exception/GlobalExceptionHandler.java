package com.example.spring.aop.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
@Component
public class GlobalExceptionHandler {

//    Need to comment out the code of AOPExceptionHandler class

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> commonExceptionHandler(Exception e) {
        logger.error(e.getMessage(), e);
        return ResponseEntity.internalServerError()
                .body("Common Exception handler");
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notfoundExceptionHandler(NotFoundException e) {
        logger.error(e.getMessage(), e);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<?> invalidArgumentExceptionHandler(InvalidArgumentException e) {
        logger.error(e.getMessage(), e);
        logger.info("hello from invalid argument Exception handler");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }
}
