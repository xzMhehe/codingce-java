package cn.com.codingce.testconstructor;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/4 11:29
 */
public class Person {

    public Person(){
        System.out.println("无参数构造方法");
    }

    public Person(String s){
        System.out.println("我是有参数构造方法");
    }
}
