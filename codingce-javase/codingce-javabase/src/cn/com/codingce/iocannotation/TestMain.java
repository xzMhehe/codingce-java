package cn.com.codingce.iocannotation;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/7 10:52
 */
public class TestMain {
    public static void main(String[] args) {
        /**
         * 获取Person对象
         * 之前 Person p = new Person(); p.setName("掌上编程");
         * 改变处理  不用自己处理 跟被人要 MySpring
         * 对象创建权力反转（IOC） 管理 赋值（自动） 别人处理
         * ms 管理者 帮助我们创建对象 自动赋值
         */
        MySpring ms = new MySpring();
        Person p = (Person) ms.getBean("cn.com.codingce.iocannotation.Person");
        System.out.println(p.toString());

        User u = (User) ms.getBean("cn.com.codingce.iocannotation.User");
        System.out.println(u.toString());

        Question q = (Question) ms.getBean("cn.com.codingce.iocannotation.Question");
        System.out.println(q.toString());
    }
}
