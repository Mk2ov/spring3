package com.example;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect 
@Component
public class LoggingAspect {

    

    @Pointcut("execution(* com.example.Workplace.printInfo(..))")
    public void workplaceInfoMethod() {}


    @Pointcut("execution(* com.example.Processor.*(..))")
    public void allProcessorMethods() {}

    @Pointcut("execution(* com.example..get*(..))")
    public void getterMethods() {}

    @Pointcut("allProcessorMethods() && !execution(* com.example.Processor.init(..))")
    public void processorMethodsWithoutInit() {}



    @Before("workplaceInfoMethod()")
    public void logBeforeInfo(JoinPoint joinPoint) {
        System.out.println("=== AOP LOG: Someone is about to check the Workplace info ===");
        System.out.println("=== Method: " + joinPoint.getSignature().getName());
    }


    @AfterReturning(pointcut = "getterMethods()", returning = "result")
    public void logAfterGetters(Object result) {

    }


    @Around("processorMethodsWithoutInit()")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        System.out.println("=== AOP TIMER: Start measuring for " + joinPoint.getSignature().getName());


        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;

        System.out.println("=== AOP TIMER: " + joinPoint.getSignature().getName() + " took " + executionTime + "ms");

        return proceed;
    }
}
