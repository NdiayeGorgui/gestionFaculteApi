package com.myproject.faculte.advice;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;



@Component
@Aspect
public class LogAspectExeptions {
	Logger logger=Logger.getLogger(LogAspectExeptions.class.getName());

	 public  LogAspectExeptions() throws  IOException {
		
	
	        logger.addHandler(new FileHandler("myLogExep.xml"));  //creer un fic log
	        logger.setUseParentHandlers(false);  //pour lui dire de ne plus logger dans la console
	    }
	    
	    @Pointcut(value="execution(* com.myproject.faculte.*.*.*(..))")
	    public void myPointCut() {
	    	
	    }
 
	    @AfterThrowing(pointcut = "myPointCut()", throwing = "e")
	    public void log(JoinPoint joinPoint, Throwable e) throws Throwable {
	       
	       
	        logger.info("Method Invoked after throwing "+joinPoint.getSignature());
	        logger.info("Class Invoked after throwing "+joinPoint.getTarget().getClass().toString());
	        logger.info("Return whith Exeption "+joinPoint.toShortString()+" "+e.getClass().getSimpleName());
	      
	    }
}
