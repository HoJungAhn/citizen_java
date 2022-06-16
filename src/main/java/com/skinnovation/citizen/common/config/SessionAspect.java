package com.skinnovation.citizen.common.config;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.skinnovation.citizen.common.exception.exceptions.NotLoginException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class SessionAspect {
    @Pointcut("within(@org.springframework.stereotype.Controller *) || within(@org.springframework.web.bind.annotation.RestController *)")
    void servicePointCut() {}

    
    @Around("servicePointCut()")
    public Object Around(ProceedingJoinPoint joinPoint) throws Throwable {
    	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    	HttpSession session = request.getSession();
    	    	
    	Object object = joinPoint.proceed();
    	
    	return object;
    }
    
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *) "
    		+ "&&!within(*..LoginController) "
    		+ "&&!within(springfox.documentation..*) ")
    void loginPointCut() {}
    
    @Around("loginPointCut()")
    public Object loginAround(ProceedingJoinPoint joinPoint) throws Throwable {
    	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    	HttpSession session = request.getSession();
    	
    	Optional.ofNullable(session.getAttribute("login_user")).orElseThrow(() -> new NotLoginException("not login user"));
    	System.out.println("=====> " + session.getAttribute("login_user"));
    	Object object = joinPoint.proceed();
    	
    	return object;
    }
}
