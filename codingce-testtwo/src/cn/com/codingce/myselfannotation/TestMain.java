package cn.com.codingce.myselfannotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/7 9:20
 */
public class TestMain {
    public static void main(String[] args) {
        /**
         * 解析Person类中name属性 上面的注解解析
         * 1 获取Person对应类的class
         * 2 通过clazz 获取里面的属性
         * 3 通过field获取上面注解对象
         * 4 利用a对象执行一下value方法 帮助我们搬运过来        正常对象调用操作
         *
         * 4 反射操作  利用反射执行过程
         * 5 通过bclazz 获取里面的value方法（属性   叫法不一）
         * 6 执行方value方法 获取传递信息   bmethod.invoke(所属的对象, 方法执行时需要的参数);
         *
         */
        try {
            Class clazz = Person.class;
            Field field = clazz.getDeclaredField("name");
            MyAnnotation a = field.getAnnotation(MyAnnotation.class);
           /* String[] values = a.value();
            System.out.println(values[0]);*/
           Annotation b = field.getAnnotation(MyAnnotation.class);
           Class blcazz = b.getClass();
           Method bmethod = blcazz.getMethod("value");
           String[] bvalues = (String[]) bmethod.invoke(a);
            System.out.println(bvalues[0]);
            getMethod();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void getMethod(){
        try {
            Class aclazz = Class.forName("cn.com.codingce.myselfannotation.Person");
            Method method = aclazz.getDeclaredMethod("eat");
            Annotation a = method.getAnnotation(MyAnnotation.class);
            Class bclazz = a.getClass();
            Method  bmethod= bclazz.getMethod("value");
            String[] values = (String[]) bmethod.invoke(a);
            for (String fa:values
                 ) {
                System.out.println(fa);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
