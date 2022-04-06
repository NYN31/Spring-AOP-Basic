package com.example.spring.aop.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class AOPPaymentConfig {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("@annotation(com.example.spring.aop.annotation.LogExecutionTime)")
    public void BeforeExecution(JoinPoint joinPoint) {
        logger.info("Before Execution: {}", joinPoint.getSignature().getName());
    }

    @After("@annotation(com.example.spring.aop.annotation.LogExecutionTime)")
    public void afterExecution(JoinPoint joinPoint) {
        logger.info("After Execution: {}", joinPoint.getSignature().getName());
    }

    @Around("@annotation(com.example.spring.aop.annotation.LogExecutionTime)")
    public Object aroundExecution(@NotNull ProceedingJoinPoint joinPoint) throws Throwable {


        logger.info("{} start time {}", joinPoint.getSignature().getName(), LocalDateTime.now());
        Object proceed = joinPoint.proceed();
        logger.info("{} end time {}", joinPoint.getSignature().getName(), LocalDateTime.now());

        return proceed;
    }
}
