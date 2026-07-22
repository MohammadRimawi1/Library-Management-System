package com.exalt.library.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * logs every service method call - class, method, arguments, execution time, and outcome
 * applies automatically to every method in the services package, no changes needed there
 * @author Mohammad Rimawi
 */
@Aspect
@Component
public class LoggingAspect {
    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    /**
     * Intercepts service method executions and logs their execution details.
     * @param joinPoint the intercepted method invocation
     * @return the value returned by the intercepted method
     * @throws Throwable if the intercepted method throws an exception
     */
    @Around("execution(* com.exalt.library.services..*(..))")
    public Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        log.info("Calling {}.{}({})", className, methodName, Arrays.toString(args));

        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long duration = System.currentTimeMillis() - start;
            log.info("{}.{} completed in {}ms", className, methodName, duration);
            return result;
        } catch (Throwable e) {
            long duration = System.currentTimeMillis() - start;
            log.warn("{}.{} threw {} after {}ms: {}", className, methodName, e.getClass().getSimpleName(), duration, e.getMessage());
            throw e;
        }
    }
}