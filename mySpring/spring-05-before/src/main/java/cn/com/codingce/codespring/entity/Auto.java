package cn.com.codingce.codespring.entity;

/**
 * Beans 自动装配
 * 你已经学会如何使用<bean>元素来声明 bean 和通过使用 XML 配置文件中的<constructor-arg>和<property>元素来注入 。
 * Spring 容器可以在不使用<constructor-arg>和<property> 元素的情况下自动装配相互协作的 bean 之间的关系，
 * 这有助于减少编写一个大的基于 Spring 的应用程序的 XML 配置的数量。
 *
 * 自动装配模式
 * 下列自动装配模式，它们可用于指示 Spring 容器为来使用自动装配进行依赖注入。你可以使用<bean>元素的 autowire 属性为一个 bean 定义指定自动装配模式。
 *     no   这是默认的设置，它意味着没有自动装配，你应该使用显式的bean引用来连线。你不用为了连线做特殊的事。在依赖注入章节你已经看到这个了。
 *     byName   由属性名自动装配。Spring 容器看到在 XML 配置文件中 bean 的自动装配的属性设置为 byName。然后尝试匹配，并且将它的属性与在配置文件中被定义为相同名称的 beans 的属性进行连接。
 *     constructor  类似于 byType，但该类型适用于构造函数参数类型。如果在容器中没有一个构造函数参数类型的 bean，则一个致命错误将会发生。
 *     autodetect Spring首先尝试通过 constructor 使用自动装配来连接，如果它不执行，Spring 尝试通过 byType 来自动装配。
 *
 *
 * Spring 自动装配 `byName`
 *
 * @author 2460798168@qq.com
 * @date 2019/12/25 14:53
 */
public class Auto {

    private AutoSpellChecker autoSpellChecker;
    private String name;

    public Auto(AutoSpellChecker autoSpellChecker) {
        System.out.println("一个参数的");
//        this.autoSpellChecker = autoSpellChecker;
    }

    public Auto(AutoSpellChecker autoSpellChecker, String name) {
        System.out.println("两个参数的");
        this.autoSpellChecker = autoSpellChecker;
        this.name = name;
    }

    public AutoSpellChecker getAutoSpellChecker() {
        return autoSpellChecker;
    }

    public void setAutoSpellChecker(AutoSpellChecker autoSpellChecker) {
        this.autoSpellChecker = autoSpellChecker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void checkSpelling() {
        System.out.println("Auto类的name：" + this.name);
        this.autoSpellChecker.checkSpelling();
    }
}
