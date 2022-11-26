# 注解说明
- @Component: 组件放在类名上, 说明这个类被Spring管理了, 就是Bean
- @Value: 相当于等价<property name="name" value="掌上编程" />
# 使用注解开发
- bean
```java
注解说明
@Component: 组件放在类名上, 说明这个类被Spring管理了, 就是Bean
@Value: 相当于等价<property name="name" value="掌上编程" />
```
- 属性如何注入
```java
@Component
public class User {

    @Value("掌上编程")
    public String name;

}
```
- 衍生的注解
@Componment有几个衍生的注解, 我们在Web开发中, 会按照mvc三层架构分层
    - dao【@Repository】
    - service【@Service】
    - controller【@Controller】
    这四个注解功能都是一样的, 都是代表将某个类注册到Spring, 装配Bean

- 作用域
```java
@Component
@Scope("prototype")
public class User {
    @Value("掌上编程")
    public String name;

    @Value("掌上编程")
    public void setName(String name) {
        this.name = name;
    }
}
```

小结:
xml与注解
- xml更加万能, 适用于任何场合! 维护简单方便
- 注解不是自己的类使用不了, 维护相对复杂！
xml与注解最佳实践
- xml用来管理bean;
- 注解只负责完成属性的注入.
- 我们在使用过程中, 只需要注意一个问题: 必须让注解生效, 就需要开启注解的支持
```xml
    <!--指定要扫描的宝, 这个包下的注解就会生效-->
    <context:component-scan base-package="cn.com.codingce"/>
    <!--开启注解的支持-->
    <context:annotation-config/>
```