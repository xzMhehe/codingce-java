package cn.com.codingce.ioc;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/4 13:14
 */
public class TestMain {
    public static void main(String[] args) {
        /**
         * 创建Question对象 对象的控制权交给别人处理 MySpring 类处理 IOC
         */
        MySpring spring = new MySpring();
        Question question = (Question) spring.getBean("cn.com.codingce.ioc.Question");
        System.out.println(question);

    }
}
