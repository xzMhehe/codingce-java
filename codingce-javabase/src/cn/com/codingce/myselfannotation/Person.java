package cn.com.codingce.myselfannotation;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/7 9:19
 */
public class Person {

    /**
     * 在写实体类的时候  建议大家用包装类 写属性  避免出现空值的安全隐患
     */
    @MyAnnotation("掌上编程")
    private String name;

    @MyAnnotation({"掌上编程呀","12","测试"})
    public void eat(){

    }
}
