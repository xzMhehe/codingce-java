import cn.com.codingce.codespring.entity.HelloWorld;
import cn.com.codingce.codespring.entity.Inherit;
import cn.com.codingce.codespring.entity.InjectTextEditor;
import cn.com.codingce.codespring.entity.TextEditor;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author 2460798168@qq.com
 * @date 2019/12/25 10:29
 */
public class Synthesize {

    /**
     * Spring Bean 定义继承
     * 在这里你可以观察到，我们创建 “helloIndia” bean 的同时并没有传递 message2，
     * 但是由于 Bean 定义的继承，所以它传递了 message2。
     * 父 bean 自身不能被实例化，因为它是不完整的，而且它也被明确地标记为抽象的。
     * 当一个定义是抽象的，它仅仅作为一个纯粹的模板 bean 定义来使用的，充当子定义的父定义使用。
     */
    @Test
    public void inherit() {
        ApplicationContext context = new FileSystemXmlApplicationContext("F:\\AfterEnd\\codingce-spring\\src\\main\\java\\cn\\com\\codingce\\codespring\\bean\\Beans.xml");
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
        ApplicationContext context = new FileSystemXmlApplicationContext("F:\\AfterEnd\\codingce-spring\\src\\main\\java\\cn\\com\\codingce\\codespring\\bean\\Beans.xml");
        TextEditor textEditor = (TextEditor) context.getBean("textEditor");
        textEditor.spellCheck();
    }
    /**
     * Spring 基于设值函数的依赖注入
     */
    @Test
    public void valueDi() {
        ApplicationContext context = new FileSystemXmlApplicationContext("F:\\AfterEnd\\codingce-spring\\src\\main\\java\\cn\\com\\codingce\\codespring\\bean\\Beans.xml");
        TextEditor textEditor = (TextEditor) context.getBean("textEditor");
        textEditor.spellCheck();
    }

    /**
     * Spring 注入内部 Beans
     */
    @Test
    public void InjectDi() {
        ApplicationContext context = new FileSystemXmlApplicationContext("F:\\AfterEnd\\codingce-spring\\src\\main\\java\\cn\\com\\codingce\\codespring\\bean\\Beans.xml");
        InjectTextEditor injectTextEditor = (InjectTextEditor) context.getBean("injectTextEditor");
        injectTextEditor.injectSpellChecker();
    }

}
