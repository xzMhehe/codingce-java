package cn.com.codingce.testone;

import java.lang.reflect.Field;


public class StringDemo {
    public static void main(String[] args) throws Exception {
        String str = "abcd";
        System.out.println(str);
        /**
         * 反射技术可以获取私有属性 可以操作私有属性 虽然很不合理
         * 1 获取 String类对应得那个class
         * 2 通过clazz获取类中的value值
         * 3 直接操作属性值不可以设置属性可以被操作
         * 4 获取value属性里面的值（内存地址）
         * 通过temp（临时变量）内存地址引用，找到真实String对象数组 修改数组中的每一个元素
         * 最终输出的str值不相同
         */
        Class clazz = str.getClass();
        Field field = clazz.getDeclaredField("value");
        field.setAccessible(true);
        char[] temp = (char[])field.get(str);
        temp[0] = '掌';
        temp[1] = '上';
        temp[2] = '编';
        temp[3] = '程';
        System.out.println(str);
    }
}
