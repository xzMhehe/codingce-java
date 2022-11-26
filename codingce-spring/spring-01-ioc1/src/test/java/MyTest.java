import cn.com.codingce.service.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
//    public static void main(String[] args) {
//        //用户实际调用的是业务层, dao层他们不需要接触
//        UserService userService = new UserServiceImpl();
//        ((UserServiceImpl)userService).setUserDao(new UserDaoMysqlImpl());
//        userService.getUser();
//    }

    public static void main(String[] args) {
        //获取ApplicationContext  拿到Spring的容器
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        //容器在手 需要什么就直接get
        UserServiceImpl userServiceImpl = (UserServiceImpl) context.getBean("UserServiceImpl");

        userServiceImpl.getUser();

    }
}
