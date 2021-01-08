package cn.com.codingce.testannotation;

import java.io.Serializable;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/4 15:11
 */
public class Person extends Animal implements Serializable {

    private static final long serialVersionID = 1L;

    @MyAnnotation("abc")
    private String name;
    @Override
    public void eat(){
        System.out.println("Person类中重写的吃饭方法");
    }

}
