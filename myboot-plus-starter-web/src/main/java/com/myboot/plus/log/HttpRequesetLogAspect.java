package com.myboot.plus.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @auth zyf
 * @date 2021/11/26
 * @desc http请求日志切面
 */
@Slf4j
@Aspect
@Component
public class HttpRequesetLogAspect {

//    @Pointcut("execution(public * *..controller..*(..))")
//    public void logAspect(){};
//
//    @Before(value = "logAspect()")
//    public void doBefore(JoinPoint joinPoint){
//        // 开始打印请求日志
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        log.info("接口路径：{}" , request.getRequestURL().toString());
//        log.info("IP : {}" , request.getRemoteAddr());
//        log.info("请求类型：{}", request.getMethod());
//        log.info("类方法 : {}" ,joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//        log.info("请求参数 : {} " ,Arrays.toString(joinPoint.getArgs()));
//
//    }
}
