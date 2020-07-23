# Bean的自动装配
- 自动装配是Spring满足Bean依赖的一种方式
- Spring会在上下文自动寻找, 并且自动给bean装配属性

在Spring中有三种装配的方式
- 在xml中显示的配置
- 在Java中显示配置
- **隐式的自动配置bean【重要】**

## 测试
- 搭建环境成功 ： 一个人有两个宠物
### ByName自动装配
```xml
    <bean id="cat" class="cn.com.codingce.pojo.Cat"/>
    <bean id="dog" class="cn.com.codingce.pojo.Dog"/>
<!--
        byName：会自动在容器上下文查找, 和自己对象set方法后面的值对应的beanid
        byType：会自动在容器上下文查找, 和自己对象属性相同的beanid
    -->
    <bean id="people" class="cn.com.codingce.pojo.People" autowire="byName">
        <property name="name" value="掌上编程"/>
<!--        <property name="dog" ref="dog"/>-->
<!--        <property name="cat" ref="cat"/>-->
     </bean>
```
### ByType自动装配
```xml
    <bean id="cat" class="cn.com.codingce.pojo.Cat"/>
    <bean id="dog" class="cn.com.codingce.pojo.Dog"/>
<!--
        byName：会自动在容器上下文查找, 和自己对象set方法后面的值对应的beanid
        byType：会自动在容器上下文查找, 和自己对象属性相同的beanid
    -->
    <bean id="people" class="cn.com.codingce.pojo.People" autowire="byType">
        <property name="name" value="掌上编程"/>
<!--        <property name="dog" ref="dog"/>-->
<!--        <property name="cat" ref="cat"/>-->
     </bean>
```

### 小结 
- byName的时候, 需要保证所有bean的id唯一, 并且这个bean需要和自动注入的属性的set方法的值一致
- byType的时候, 需要保证所有class的id唯一, 并且这个bean需要和自动注入的属性的类型一样

## 使用注解实现自动装配
JDK1.5支持注解，Spring2.5就支持注解了
The introduction of annotation-based configuration raised the question of whether this approach is "better" than XML

要用注解须知
- 导入约束 context  
- 配置注解的支持<context:annotation-config/>
```xml
<?xml version="1.0" encoding="UTF-8"?>

<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd">

        <context:annoation-config/>
</beans>
```

### @Autowired
直接使用在属性上即可! 也可以在set方式上的使用
使用Autowired我们就可以不用使用Set方法了, 前提是你这个自动装配属性在IOC(Spring)容器中存在, 且符合名字byname
科普:
```java
@Nullable: 字段标记了这个注解, 说明这个字段可以为null；

```
```java

public @interface Autowired {
    boolean required() default true;
}
```

```java

public class People {

    //如果显示的定义了Autowried的required属性为false, 说明这个对象可以为Null 否则不许为空
    @Autowired
    private Cat cat;
    @Autowired
    private Dog dog;
    private String name;
}
```

如果@Autowired自动装配的环境比较复杂, 自动装配无法通过一个注解【@Autowired】完成的时候，我们可以使用@Qualifier(value="dog22")
去配合@Autowired的使用

### Resource注解
```java
public class People {
    @Resource
    private Cat cat;
    @Resource
    private Dog dog;
    private String name;
```

小结: 
@Resource与@Autowired的区别
- 都是用来自动装配, 都可以放在属性字段上
- @Autowired是通过byType的方式实现, 而且必须要求这个对象存在!
- @Resource默认通过byName的方式实现, 如果找不到名字, 则通过byType实现, 如果两个都找不到的情况下就报错
- 执行顺序不同:  @Autowired通过btType的方式实现。@Resource默认通过byName的方式来实现