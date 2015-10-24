package com.gooki.aspect;

import com.gooki.model.User;
import com.gooki.service.UserManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by iecanfly on 5/14/14.
 */
@Aspect
@Component
public class AccessAspect {

    @Autowired
    private UserManager userManager;

    @Pointcut("within(@org.springframework.stereotype.Controller *)")
    public void controllerBean() {}

    @Pointcut("execution(* *(..))")
    public void methodPointcut() {}

    @Before("controllerBean() && methodPointcut() && @annotation(com.gooki.annotation.BuyerOnly)")
    public void adviceBuyerOnly(JoinPoint joinPoint) throws Exception {
        HttpServletRequest request = getHttpServletRequest(joinPoint);

        User currentUser = userManager.getUserByUsername(request.getRemoteUser());
        if(!currentUser.getIsBuyer()) {
            throw new AccessDeniedException("You are not a buyer");
        }

    }

    protected HttpServletRequest getHttpServletRequest(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        HttpServletRequest request = null;
        for(int i = 0; i < args.length; i++) {
            if(args[i] instanceof SecurityContextHolderAwareRequestWrapper) {
                request = (HttpServletRequest)args[i];
            }
        }
        return request;
    }

    @Before("controllerBean() && methodPointcut()  && @annotation(com.gooki.annotation.SellerOnly)")
    public void adviceSellerOnly(JoinPoint joinPoint) throws Exception {
        HttpServletRequest request = getHttpServletRequest(joinPoint);

        User currentUser = userManager.getUserByUsername(request.getRemoteUser());
        if(currentUser.getIsBuyer()) {
            throw new AccessDeniedException("You are not a seller");
        }
    }
}
