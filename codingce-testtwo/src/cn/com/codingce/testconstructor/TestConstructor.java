package cn.com.codingce.testconstructor;

import java.lang.reflect.Constructor;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/4 11:25
 */
public class TestConstructor {
    public static void main(String[] args) {
        /**
         * 找到Person对应的class
         * 找寻无参数构造方法
         * 执行构造方法
         *
         * 获取构造方法
         * Constructor = clazz.getConstructor(Class...类型构造方法)
         *
         * Contructor常用方法
         * con.getModifiers();
         * con.getName();
         * con.getParameterTypes();
         * con.getExceptionTypes();
         * 如何创建构造方法
         * 执行一次 创建对象
         * Object = new Instance(执行构造方法时的所有参数)
         *
         *
         * Constructor[] cons = clazz.getConstructors();
         * clazz.getDeclaredConstructor();
         * clazz.getDeclaredConstructors();
         */
        try {
            Class clazz = Person.class;
            Constructor con = clazz.getConstructor(String.class);
            Person p = (Person) con.newInstance("掌上编程");
            System.out.println(p);
            Constructor[] cons = clazz.getConstructors();
            clazz.getDeclaredConstructor();
            clazz.getDeclaredConstructors();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
