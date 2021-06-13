package com.example.miaosha.limit;

import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: YHM
 * @Date: 2021/6/9 20:10
 */
@Aspect
@Component
public class RateLimiterAspect {
    private ConcurrentHashMap<String, RateLimiter> RateLimiters = new ConcurrentHashMap<>();
    private RateLimiter limiter;
    private Logger logger = LoggerFactory.getLogger(RateLimiterAspect.class);

    @Pointcut("@annotation(com.example.miaosha.limit.Limit)")
    public void service() {}

    @Around("service()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Object target = point.getTarget();
        Method method = target.getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
        Limit limit = method.getAnnotation(Limit.class);
        double limitNum = limit.limitNum();
        String name = methodSignature.getName();
        if (!RateLimiters.containsKey(name)) {
            RateLimiters.put(name, RateLimiter.create(limitNum));
        }
        limiter = RateLimiters.get(name);
        if (limiter.tryAcquire()) {
            logger.info(method+" execute success!");
            return point.proceed();
        }
        else {
            throw new RuntimeException("服务器繁忙，请稍后再试！");
        }
    }
}
