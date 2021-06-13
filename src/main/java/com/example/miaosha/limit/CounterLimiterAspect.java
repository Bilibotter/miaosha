package com.example.miaosha.limit;

import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class CounterLimiterAspect {

    @Autowired
    private LimitStream limiter;

    @Pointcut("@annotation(com.example.miaosha.limit.Limited)")
    public void service() {}

    @Around("service()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Object target = point.getTarget();
        Method method = target.getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
        Limited limited = method.getAnnotation(Limited.class);
        double limitNum = limited.limitNum();
        if (limiter.limit()) {
            return point.proceed();
        }
        else {
            throw new RuntimeException("服务器繁忙，请稍后再试！");
        }
    }
}
