package cn.com.codingce.diy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

//使用注解方式实现AOP
@Aspect //标注这个类是一个切面
public class AnnotationPointCut {

    @Before("execution(* cn.com.codingce.service.UserServiceImpl.*(..))")
    public void before() {
        System.out.println("==============方法执行前===============");
    }

    @After("execution(* cn.com.codingce.service.UserServiceImpl.*(..))")
    public void after() {
        System.out.println("===============方法执行后===============");
    }

    //在环绕增强中，我们可以定一个参数, 代表我们要处理切入的点
    @Around("execution(* cn.com.codingce.service.UserServiceImpl.*(..))")
    public void around(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("环绕前");

        //执行方法
        Object proceed = jp.proceed();
        System.out.println("环绕后");

        Signature signature = jp.getSignature();//获得签名
        System.out.println("signature" + signature);


    }


}
