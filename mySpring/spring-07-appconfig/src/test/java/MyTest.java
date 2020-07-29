import cn.com.codingce.config.ZeConfig;
import cn.com.codingce.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {

    @Test
    public void Test1() {
        //如果完全使用了配置类方式去做, 我们只能通过AnnotationConfig上下文来获取容器, 通过类的class对象加载!
        ApplicationContext context = new AnnotationConfigApplicationContext(ZeConfig.class);
        User user = context.getBean("getUser", User.class);
        System.out.println(user.getName());
    }

}
