package com.myproject.faculte.advice;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;



@Component
@Aspect
public class LogAspectGet {
	Logger logger=Logger.getLogger(LogAspectGet.class.getName());
	//Logger logger=LoggerFactory.getLogger(LogAspectGet.class);

    public LogAspectGet() throws IOException {
        logger.addHandler(new FileHandler("myLogGet.xml"));  //creer un fic log
        logger.setUseParentHandlers(false);  //pour lui dire de ne plus logger dans la console
    }
    
    @Pointcut(value="execution(* com.myproject.faculte.*.*..get*(..))")
    public void myPointCut() {
    	
    }
    
   // @Around("execution(* com.myproject.faculte..*.get*(..))")
    @Around("myPointCut()")
    public Object log(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Long t1=System.currentTimeMillis();
        
       
        logger.info("Method Invoked before proceddind "+proceedingJoinPoint.getSignature());
        logger.info("Class Invoked before proceddind "+proceedingJoinPoint.getTarget().getClass().toString());
        Object result=proceedingJoinPoint.proceed();
        logger.info("Method Invoked after proceddind "+proceedingJoinPoint.getSignature());
        logger.info("Method Invoked after proceddind "+proceedingJoinPoint.getTarget().getClass().toString());
        Long t2=System.currentTimeMillis();
        logger.info("execution time for the method: "+proceedingJoinPoint.getSignature()+" ="+(t2-t1)+" ms");
        logger.info("Method "+proceedingJoinPoint.toShortString()+" Invoked whith "+proceedingJoinPoint.getArgs().length+" Argument(s)");
        return result;
    }
}
