package com.example.demo.aspect;

import com.example.demo.utils.CommonUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 切面日志
 */
@Aspect
@Component
public class RequestLog {
    private static Logger log = LoggerFactory.getLogger(RequestLog.class);

    @Pointcut("execution( public * com.example.demo.controller.*.*(..))")
    public void requestLog() {
    }

    @Before("requestLog()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("---------------request------------------");
        log.info("URL : " + request.getRequestURI());
        log.info("HTTP_METHOD: " + request.getMethod());
        log.info("IP:" + CommonUtil.getIpAddress(request));
        // 打印类名和方法名
        log.info("METHOD_NAME: " + joinPoint.getTarget().getClass() + "." + joinPoint.getSignature().getName());
        log.info("session_id:" + request.getSession().getId());
        Enumeration<String> names = request.getParameterNames();
        String name;
        while (names.hasMoreElements()) {
            name = names.nextElement();
            log.info("PARAM: " + name + ", value: " + request.getParameter(name));

        }
    }
}
