package com.example.two.db.demo.aspectconfig;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Aspect
@Configuration
public class AspectConfigController {

    Logger logger = LoggerFactory.getLogger(AspectConfigController.class);

    @Before(value = "execution(* com.example.two.db.demo.*.controller.*.*(..))")
    public void before(JoinPoint joinPoint){

        logger.info("Before Execution");

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        Enumeration<String> header = request.getHeaderNames();

        logger.info("Allowing Execution for {},header{},parameter{}",joinPoint,header,joinPoint.getArgs());

    }


    @AfterReturning(value = "execution(* com.example.two.db.demo.*.controller.*.*(..))",returning = "result")
    public void afterReturning(JoinPoint joinPoint,Object result){

        logger.info("{} returning with value{}",joinPoint,result != null ? result.toString():result);

    }

    @After(value = "execution(* com.example.two.db.demo.*.controller.*.*(..))")
    public void after(JoinPoint joinPoint){
        logger.info("after execution of {}", joinPoint);
    }
}
