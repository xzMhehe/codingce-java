import cn.com.codingce.codespring.entity.Address;
import cn.com.codingce.codespring.entity.Auto;
import cn.com.codingce.codespring.entity.AutowiredTest;
import cn.com.codingce.codespring.entity.HelloWorld;
import cn.com.codingce.codespring.entity.Inherit;
import cn.com.codingce.codespring.entity.InjectTextEditor;
import cn.com.codingce.codespring.entity.QualifierTest;
import cn.com.codingce.codespring.entity.Student;
import cn.com.codingce.codespring.entity.TextEditor;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author 2460798168@qq.com
 * @date 2019/12/25 10:29
 */
public class Synthesize {

    //定义一个ApplicationContext
    ApplicationContext context = new FileSystemXmlApplicationContext("F:\\AfterEnd\\codingce-spring\\src\\main\\java\\cn\\com\\codingce\\codespring\\bean\\Beans.xml");

    /**
     * Spring Bean 定义继承
     * 在这里你可以观察到，我们创建 “helloIndia” bean 的同时并没有传递 message2，
     * 但是由于 Bean 定义的继承，所以它传递了 message2。
     * 父 bean 自身不能被实例化，因为它是不完整的，而且它也被明确地标记为抽象的。
     * 当一个定义是抽象的，它仅仅作为一个纯粹的模板 bean 定义来使用的，充当子定义的父定义使用。
     */
    @Test
    public void inherit() {
        HelloWorld helloWorld = (HelloWorld) context.getBean("helloWorld");
        helloWorld.getMessage1();
        helloWorld.getMessage2();

        Inherit inherit = (Inherit) context.getBean("inherit");
        inherit.getMessage1();
        inherit.getMessage2();
        inherit.getMessage3();
    }

    /**
     * Spring 基于构造函数的依赖注入
     */
    @Test
    public void constructionDi() {
        TextEditor textEditor = (TextEditor) context.getBean("textEditor");
        textEditor.spellCheck();
    }

    /**
     * Spring 基于设值函数的依赖注入
     */
    @Test
    public void valueDi() {
        TextEditor textEditor = (TextEditor) context.getBean("textEditor");
        textEditor.spellCheck();
    }

    /**
     * Spring 注入内部 Beans
     */
    @Test
    public void injectDi() {
        InjectTextEditor injectTextEditor = (InjectTextEditor) context.getBean("injectTextEditor");
        injectTextEditor.injectSpellChecker();
    }

    /**
     * Spring 注入集合
     */
    @Test
    public void gatherDi() {
        Address address = (Address) context.getBean("address");
        address.getAddressList();
        address.getAddressSet();
        address.getAddressMap();
        address.getAddressProp();
    }
    /**
     * Spring 自动装配 `byName`   Spring 自动装配 `byType`
     */
    @Test
    public void autoDi() {
        Auto auto = (Auto) context.getBean("auto");
        auto.checkSpelling();
    }
    /**
     * Spring 自动装配 `byName`   Spring 自动装配 `byType`  Spring 由构造函数自动装配
     */
    @Test
    public void autoConstructor() {
        Auto auto = (Auto) context.getBean("auto");
        auto.checkSpelling();
    }

    /**
     * Spring @Required 注释
     */
    @Test
    public void student() {
        Student student = (Student) context.getBean("student");
        System.out.println(student.toString());
    }

    /**
     * Spring @Autowired 注释
     */
    @Test
    public void autowiredTest() {
        AutowiredTest autowiredTest = (AutowiredTest) context.getBean("autowiredTest");
        autowiredTest.funAutowiredTest();
    }

    /**
     * Spring @Qualifier 注释
     */
    @Test
    public void qualifierTest() {
        QualifierTest qualifierTest = (QualifierTest) context.getBean("qualifierTest1");
        System.out.println(qualifierTest.toString());
    }

}
