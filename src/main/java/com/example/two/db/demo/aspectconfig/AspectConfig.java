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
public class AspectConfig {

    Logger logger = LoggerFactory.getLogger(AspectConfig.class);

    @Before(value = "execution(* com.example.two.db.demo.*.service.db1.*.*(..))")
    public void before(JoinPoint joinPoint) {
        logger.info("Before Execution");

        try {
            HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

            Enumeration<String> header = httpServletRequest.getHeaderNames();

            logger.info("Allowing Execution for {},header{},parameter{}", joinPoint, header, joinPoint.getArgs());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @AfterReturning(value = "execution(* com.example.two.db.demo.*.service.db1.*.*(..))", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {

        logger.info("{} returning with value {}", joinPoint, result != null ? result.toString() : result);

    }

    @After(value = "execution(* com.example.two.db.demo.*.service.db1.*.*(..))")
    public void after(JoinPoint joinPoint) {
        logger.info("after execution of {}", joinPoint);
    }


}
