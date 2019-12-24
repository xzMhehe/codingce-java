package cn.com.codingce.testmethod;

import java.lang.reflect.Method;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/4 10:34
 */
public class TestMethod {
    public static void main(String[] args) {
        /**
         * Method类中常用
         * 通过Person对应的Class
         * 通过clazz获取其中的方法
         * 通过方法名定位方法 通过方法参数类型对应的Class来找寻
         * mm 获取方法的修饰符（权限+特征）
         * mrt获取返回值数据类型
         * mn获取方法的名字
         * mpts 获取方法参数列表的类型
         * mets 获取方法抛异常的类型
         *
         * invoke(对象,执行方法需要传递的所有参数...)
         *
         * Class类中的方法
         * Method[] = getMethods()    获取所有共有的方法（自己类+父类）
         * Method = getMethod(方法名,Class类型参数)    获取公有的方法（自己类+父类）
         * Method = getDeclaredMethod("方法名",参数类型的class)
         * Method[] = getDeclaredMethods();
         *
         * Method类中常用
         * int mm = m.getModifiers();
         * Class mrt = m.getReturnType();
         * String mn = m.getName();
         * Class[] mpts =m.getParameterTypes();
         * Class[] mets = m.getExceptionTypes();
         */
        try {
            Class clazz = Person.class;
            Person p = (Person)clazz.newInstance();

            Method m = clazz.getMethod("eat",String.class);
            Method mtwo = clazz.getMethod("sleep");
            String result = (String) m.invoke(p,"掌上编程测试参数");
            System.out.println(result);
            String resulttwo = (String) mtwo.invoke(p);

            Method[] ms = clazz.getMethods();

            Method mprivate = clazz.getDeclaredMethod("testPrivate");
            System.out.println(mprivate.getName());
            mprivate.setAccessible(true);
            mprivate.invoke(p);

            int mm = m.getModifiers();
            Class mrt = m.getReturnType();
            String mn = m.getName();
            Class[] mpts =m.getParameterTypes();
            Class[] mets = m.getExceptionTypes();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
