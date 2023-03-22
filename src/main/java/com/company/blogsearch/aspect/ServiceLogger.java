package com.company.blogsearch.aspect;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@AllArgsConstructor
@Aspect
@Component
public class ServiceLogger {

    @Pointcut("execution(public * com.company.blogsearch.service.*.*(..))")
    public static void loggingPoint() {}

    @Before("loggingPoint()")
    public void loggingBefore(JoinPoint joinPoint) {
        String methodName = getMethodName(joinPoint);
        log.info("[aspect][loggingBefore] method : {}, params: {}",methodName, Arrays.toString(joinPoint.getArgs()));
    }
    @AfterReturning(value = "loggingPoint()", returning = "returnValue")
    public void loggingAfter(JoinPoint joinPoint, Object returnValue) {
        String methodName = getMethodName(joinPoint);
        log.info("[aspect][loggingAfter] method : {}, returnValue {}", methodName, returnValue.toString());
    }

    private String getMethodName(JoinPoint joinPoint) {
        String [] splitStr = joinPoint.getSignature().toString().split("\\.");
        String methodName = "";
        if (splitStr.length!=0) {
            methodName = splitStr[splitStr.length-1];
        }
        return methodName;
    }
}
