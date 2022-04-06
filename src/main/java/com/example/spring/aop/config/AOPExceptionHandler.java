package com.example.spring.aop.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class AOPExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(AOPExceptionHandler.class);
//    1st pointcut only works for the specific method getuserById and second one is for all controller class
//    @Pointcut("execution(public org.springframework.http.ResponseEntitycom.example.spring.aop.controller.UserController.getUserById(Long ))")
    @Pointcut("execution(public org.springframework.http.ResponseEntity com.example.spring.aop.controller..*(..))")
    public void endpointHandlerPointCut() {
        // it represents a variable for a pointcut expression
    }

    @Before("endpointHandlerPointCut()")
    public void BeforeExecution(JoinPoint joinPoint) {
        logger.info("Before Execution: {}", joinPoint.getSignature().getName());
    }

    @After("endpointHandlerPointCut()")
    public void afterExecution(JoinPoint joinPoint) {
        logger.info("After Execution: {}", joinPoint.getSignature().getName());
    }

    @Around("endpointHandlerPointCut()")
    public Object aroundExecution(ProceedingJoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();

        logger.info("{} Starting time: {}", methodName, LocalDateTime.now());
        Object result = null;
        try{
            result = joinPoint.proceed();
            logger.info("Got result from {}", methodName);
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
            result = this.handleException(e);
        } finally {
            logger.info("{} Exiting time: {}", methodName,LocalDateTime.now());
        }
        return result;
    }

    private Object handleException(Throwable e) {
        if (e instanceof RuntimeException) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } else {
            return ResponseEntity.internalServerError()
                    .body(e.getMessage());
        }
    }
}
