package com.ailois.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MethodTimeAspect {

    private static final Logger logger = LoggerFactory.getLogger(MethodTimeAspect.class);

    @Around("@annotation(methodTime)")
    public Object doAround(ProceedingJoinPoint pjp, MethodTime methodTime) throws Throwable {
        long begin = System.currentTimeMillis();
        Object obj = pjp.proceed();
        String methodName = pjp.getSignature().getName();
        String className = pjp.getSignature().getDeclaringTypeName();
        logger.info("{}.{} 方法消耗时间：{} ms", className, methodName, System.currentTimeMillis() - begin);
        return obj;
    }

}
