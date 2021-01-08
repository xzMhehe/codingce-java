package cn.com.codingce.ioc;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/4 13:07
 */
public class MySpring {
    /**
     * 背景 设计一个小工具 替我们创建对象的过程
     * 传递字符串 帮我们创建对象 同时还能将对象内所有属性赋值
     * Spring开源框架 IOC控制饭庄 DI（依赖注入）
     * Inversion Of Control 对象的控制反转
     * Dependency Injection 对象的控制权是别人的 别人创建对象的同时帮我们自动注入属性值
     *
     *
     * 设计一个方法 帮助我们控制对象创建
     * 是否需要参数？ String 类全名
     * 是否需要返回值 ？ 对象Object
     * 获取方法传递进来的参数对应的类
     * 通过clazz创建对象
     *
     * 在这里自动的DI注入 对象中的所有属性值
     * 找到每一个不同对象所有set方法  给属性赋值
     * 自己通过拼接字符串的方式处理名字
     * 1 通过clazz寻找类中的所有私有属性--》获取每一个属性名字--》set属性     getName()获取属性名
     * 2 手动拼的字符串儿 拼接属性对应的set方法名
     *
     * 3 获取field对应的属性类型 --》找寻set方法时候传递参数用
     * 4 通过处理好的set方法名找寻类中的set方法
     * 5 找到stMethod --执行 属性就赋值成功
     *
     * 注意：
     * 属性的值现在接受过来（Scanner 文件内读取）全是String类型 Integer Float...属性类型的值
     * 如何将所有的String类型的值--》转换成属性类型的值
     * Integer包装类 八个包装类有七个都含有有带String的构造方法(唯独char不可以)
     * new Integer(String) new Float(String) new String(String)
     * 可以利用包装类带String的构造方法处理 属性类型对应的带String参数的构造方法
     */
    public Object getBean(String className){

        Object obj = null;
        Scanner input = new Scanner(System.in);
        System.out.println("请给" + className + "赋值");
        try {
            Class clazz = Class.forName(className);
            obj = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field:fields){
                String fieldName = field.getName();
                //属性首字母大写
                String firstLetter =  fieldName.substring(0,1).toUpperCase();
                //属性除了首字母之外的其他字母
                String otherLetters = fieldName.substring(1);
                StringBuilder setMethodName = new StringBuilder("set");
                setMethodName.append(firstLetter);
                setMethodName.append(otherLetters);
                Class fieldClass = field.getType();
                Method setMethod = clazz.getMethod(setMethodName.toString(),fieldClass);
                System.out.println("请给" + fieldName + "赋值");
                String value = input.nextLine();
                Constructor con = fieldClass.getConstructor(String.class);
                setMethod.invoke(obj,con.newInstance(value));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;

    }
}
