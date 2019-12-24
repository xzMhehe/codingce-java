package cn.com.codingce.testmethod;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/4 10:35
 */
public class Person extends Animal{

    public void eat(){
        System.out.println("Person吃饭方法");
    }

    public String eat(String s){
        System.out.println("Person带参数的吃饭方法");
        return s;
    }

    private void testPrivate(){
        System.out.println("我是私有的,你需要设置setAccessible(true)");
    }

}
