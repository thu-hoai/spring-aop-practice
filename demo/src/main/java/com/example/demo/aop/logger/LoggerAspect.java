package com.example.demo.aop.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {
    @Autowired private Logger logger;

    @Before("withLogExecution() && publicMethod()")
    public void loggingAdvice(JoinPoint joinPoint) {
        logger.log(joinPoint.getSignature().getName());
    }

    @Pointcut("execution(public * *(..))")
    public void publicMethod() {}

    @Pointcut("@annotation(LogExecution)")
    public void withLogExecution() {}
}

