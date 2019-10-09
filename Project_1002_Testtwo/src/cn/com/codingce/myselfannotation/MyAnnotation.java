package cn.com.codingce.myselfannotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/7 9:12
 */
@Target({ANNOTATION_TYPE, ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String[] value();            //默认方法  --》属性
}
