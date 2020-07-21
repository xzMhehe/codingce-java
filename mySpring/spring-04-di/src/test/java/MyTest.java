import cn.com.codingce.pojo.Student;
import cn.com.codingce.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    @Test
    public void Test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student student = (Student) context.getBean("student");
        System.out.println(student.getName());

        System.out.println("=================================");
        System.out.println(student.toString());

        /**
         *Student{name='掌上编程', address=Address{address='天津市西青区天津理工大学'},
         * books=[红楼梦, 西游记, 水浒传, 三国演义], hobbys=[听音乐, 敲代码, 看电影],
         * card={身份证=1111111111111112, 银行卡=11111111111, 信用卡=11111111111111},
         * games=[GTA5, 彩虹6号, LOL], wife='null',
         * info={学号=20200521, 性别=男, 姓名=掌上编程}}
         **/
    }

    @Test
    public void Test2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //这样就不用强转
        User user = context.getBean("user", User.class);
        User user2 = context.getBean("user2", User.class);
        System.out.println(user.toString());
    }

}
