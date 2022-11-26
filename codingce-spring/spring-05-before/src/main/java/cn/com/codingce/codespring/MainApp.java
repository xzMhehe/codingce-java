package cn.com.codingce.codespring;

import cn.com.codingce.codespring.entity.HelloWorld;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Spring IoC 容器
 * Spring 容器是 Spring 框架的核心。容器将创建对象，把它们连接在一起，配置它们，
 * 并管理他们的整个生命周期从创建到销毁。Spring 容器使用依赖注入（DI）来管理组成一个应用程序的组件。这些对象被称为 Spring Beans
 *
 * 第一步生成工厂对象。加载完指定路径下 bean 配置文件后，利用框架提供的 FileSystemXmlApplicationContext API 去生成工厂 bean。
 * FileSystemXmlApplicationContext 负责生成和初始化所有的对象，比如，所有在 XML bean 配置文件中的 bean。
 * 第二步利用第一步生成的上下文中的 getBean() 方法得到所需要的 bean。 这个方法通过配置文件中的 bean ID 来返回一个真正的对象。
 * 一旦得到这个对象，就可以利用这个对象来调用任何方法。
 *
 * IOC 容器具有依赖注入功能的容器，它可以创建对象，IOC 容器负责实例化、定位、配置应用程序中的对象及建立这些对象间的依赖。
 * 通常new一个实例，控制权由程序员控制，而"控制反转"是指new实例工作不由程序员来做而是交给Spring容器来做。在Spring中BeanFactory是IOC容器的实际代表者。
 *
 * @author 2460798168@qq.com
 * @date 2019/12/24 11:57
 */
public class MainApp {
    public static void main(String[] args) {
        // 创建 Spring 的 IOC 容器  用ClassPathXmlApplicationContext老是报错,干脆就用FileSystemXmlApplicationContext绝对路径
        ApplicationContext context =
                new FileSystemXmlApplicationContext("Beans.xml");
        // 从 IOC 容器中获取 bean 的实例
        HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
        obj.setMessage1("掌上编程公众号");
        obj.getMessage1();
        //        HelloWorld helloWorld = (HelloWorld) context.getBean("helloWorld");
        //        helloWorld.getMessage();
        System.out.println("==========================");
        ApplicationContext contextB =
                new FileSystemXmlApplicationContext("Beans.xml");
        HelloWorld objB = (HelloWorld) contextB.getBean("helloWorld");
        objB.getMessage1();

    }
}
