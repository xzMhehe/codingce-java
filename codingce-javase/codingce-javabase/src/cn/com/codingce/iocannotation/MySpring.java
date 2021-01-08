package cn.com.codingce.iocannotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/7 10:15
 */
public class MySpring {
    /**
     * 设计一个方法 给一个类名字 返回一个对象 对象内的属性值存在着
     * 1 通过传递的className来获取对应的类class
     * 2 通过clazz 创建 一个空值对象
     * obj = clazz.newInstance
     * 找到的是无参构造方法
     * 3 创建对象以后 将对象内的属性自动赋值 依赖注入 DI
     * 值存入--》文件 好处在于代码包装(.jar)起来不能修改 文件还能修改 不好的是 在于开发的时候 源代码和配置文件不在一起 读取/修改 比较麻烦
     * 之前是.xml 文件     单数项目上线 就不怎么修改配置文件  甚至是不修改配置文件   后逐渐以注解开发
     * 值--》注解  编写开发的时候方便 源代码和注解在一起 读取/修改比较容易  不好在于 代码包装后 注解携带的信息不能修改
     * 4 获取属性的值 --》类无参数构造方法之上
     * 5 获取a注解对象内携带的信息--》person】对象所有的属性值
     * 6 将values中每一个值对应的付给属性
     * 找寻属性对应的set方法
     */

    public Object getBean(String className) {
        Object obj = null;
        try {
            Class clazz = Class.forName(className);
            Constructor con = clazz.getConstructor();
            obj = con.newInstance();
            Annotation a = con.getAnnotation(MyAnnotation.class);
            Class aclazz = a.getClass();
            Method amethod = aclazz.getMethod("value");
            String[] values = (String[]) amethod.invoke(a);
            System.out.println("注解内获取的属性值》》》》》》》" + values.length);
            Field[] fields = clazz.getDeclaredFields();
            System.out.println("从类中获取的全部属性包括私有》》》》》" + fields.length);
            for(int i = 0;i<fields.length;i++){
                /**
                 * 获取属性名字
                 * 处理set方法字符串
                 * 第一个字母大写
                 * 拼接字符串  StringBuilder
                 * 通过处理好的set方法名字找到对应的set方法
                 * 执行找到的set方法
                 * 需要将注解读取的值转换成属性类型对应的值 属性类型的构造方法  (Integer  String)
                 */
                String fieldName = fields[i].getName();
                String firstLetter = fieldName.substring(0,1).toUpperCase();
                String otherLetter = fieldName.substring(1);
                StringBuilder setMethodName = new StringBuilder("set");
                setMethodName.append(firstLetter);
                setMethodName.append(otherLetter);
                Class fieldType = fields[i].getType();
                Method setMethod = clazz.getMethod(setMethodName.toString(),fieldType);
                setMethod.invoke(obj,fieldType.getConstructor(String.class).newInstance(values[i]));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
