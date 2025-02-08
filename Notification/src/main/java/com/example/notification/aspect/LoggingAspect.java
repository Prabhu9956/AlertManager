package com.example.notification.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
 
/**
* LoggingAspect is an aspect that logs the execution time of methods in the service layer.
* It uses AspectJ's @Around advice to intercept method calls and log their execution time.
*/
@Aspect
@Component
public class LoggingAspect {
 
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
 
    /**
     * Logs the execution time of methods in the service layer.
     *
     * @param joinPoint the join point representing the method being intercepted
     * @return the result of the method execution
     * @throws Throwable if any error occurs during method execution
     */
    
    @Around("execution(* com.example.notification.service.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
 
        Object proceed = joinPoint.proceed();
 
        long executionTime = System.currentTimeMillis() - start;
 
        logger.info(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        return proceed;
    }
}