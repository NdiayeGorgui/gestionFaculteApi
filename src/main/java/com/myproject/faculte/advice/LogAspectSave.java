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
public class LogAspectSave {
	Logger logger=Logger.getLogger(LogAspectSave.class.getName());
	

    public LogAspectSave() throws IOException {
        logger.addHandler(new FileHandler("myLogSave.xml"));  //creer un fic log
        logger.setUseParentHandlers(false);  //pour lui dire de ne plus logger dans la console
    }
    
    @Pointcut(value="execution(* com.myproject.faculte.*.*..create*(..))")
    public void myPointCut() {
    	
    }
    
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
        return result;
    }
}
