package com.example;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect // 1. Aspect клас
@Component
public class LoggingAspect {

    // --- 3. Мінімум 3 Pointcut ---

    // Pointcut 1: Вибирає метод printInfo() у класі Workplace
    @Pointcut("execution(* com.example.Workplace.printInfo(..))")
    public void workplaceInfoMethod() {}

    // Pointcut 2: Вибирає всі методи в класі Processor
    @Pointcut("execution(* com.example.Processor.*(..))")
    public void allProcessorMethods() {}

    // Pointcut 3: Вибирає всі методи, що починаються на "get" (геттери) у будь-якому класі пакету
    @Pointcut("execution(* com.example..get*(..))")
    public void getterMethods() {}

    // 4. Комбінований Pointcut (Optional)
    // Вибирає методи Processor, АЛЕ виключає метод init()
    @Pointcut("allProcessorMethods() && !execution(* com.example.Processor.init(..))")
    public void processorMethodsWithoutInit() {}


    // --- 2. Мінімум 3 Advice методи ---

    // Advice 1 (@Before): Логування перед викликом printInfo
    @Before("workplaceInfoMethod()")
    public void logBeforeInfo(JoinPoint joinPoint) {
        System.out.println("=== AOP LOG: Someone is about to check the Workplace info ===");
        System.out.println("=== Method: " + joinPoint.getSignature().getName());
    }

    // Advice 2 (@AfterReturning): Логування після успішного виконання геттерів
    // Використовуємо Pointcut 3
    @AfterReturning(pointcut = "getterMethods()", returning = "result")
    public void logAfterGetters(Object result) {
        // Цей код спрацює, якщо ви викличете будь-який get-метод (наприклад, getComputer)
        // System.out.println("=== AOP LOG: A getter was called. Result: " + result);
    }

    // Advice 3 (@Around): Вимірювання часу виконання. Найпотужніший вид advice.
    // Використовуємо Pointcut 4 (всі методи процесора, крім init)
    @Around("processorMethodsWithoutInit()")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        System.out.println("=== AOP TIMER: Start measuring for " + joinPoint.getSignature().getName());

        // Виконуємо реальний метод
        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;

        System.out.println("=== AOP TIMER: " + joinPoint.getSignature().getName() + " took " + executionTime + "ms");

        return proceed;
    }
}