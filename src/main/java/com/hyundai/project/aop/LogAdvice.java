package com.hyundai.project.aop;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Aspect
@Log4j2
@Component
public class LogAdvice {
	@AfterThrowing(
		pointcut="execution(* com.hyundai.project.service.*.*(..))",
		throwing = "exception")
	public void logException(Exception exception) throws Exception{
		log.info(exception.getMessage());
		throw exception;
	}
	
	@AfterThrowing(
		pointcut="execution(* com.hyundai.project.security.service.*.*(..))",
		throwing = "exception")
	public void logSecurityException(Exception exception) throws Exception{
		log.info(exception.getMessage());
		throw exception;
	}
}
