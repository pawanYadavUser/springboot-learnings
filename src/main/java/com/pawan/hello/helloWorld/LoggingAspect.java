package com.pawan.hello.helloWorld;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    // Pointcut: all methods inside controller package
    @Pointcut("execution(* com.pawan.hello.helloWorld.*.*(..))")
    public void controllerMethods(){}

    @Before("controllerMethods()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("➡️ Entering: " + joinPoint.getSignature());
    }

    @AfterReturning(pointcut="controllerMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result){
        System.out.println("✅ Completed: "+joinPoint.getSignature()+" | Returned: "+result);
    }

    @AfterThrowing(pointcut="controllerMethods()", throwing="ex")
    public void logAfterThrowing(JoinPoint joinPoint, Exception ex){
        System.out.println("❌ Exception in: "+joinPoint.getSignature()+" | exception: "+ex.getMessage());
    }

    @Around("controllerMethods()")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
//        System.out.println("Before proceeding: "+proceedingJoinPoint.getSignature());
//        Object result = proceedingJoinPoint.proceed() ;//call the method
//        System.out.println("After execution: "+proceedingJoinPoint.getSignature());
//        return result;
        long start = System.currentTimeMillis();

        Object result = proceedingJoinPoint.proceed();  // execute the method

        long elapsedTime = System.currentTimeMillis() - start;
        System.out.println(proceedingJoinPoint.getSignature() + " executed in " + elapsedTime + "ms");

        return result;
    }

}
