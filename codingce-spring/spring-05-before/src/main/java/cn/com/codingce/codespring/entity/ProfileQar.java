package cn.com.codingce.codespring.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Spring @Qualifier 注释
 * 可能会有这样一种情况，当你创建多个具有相同类型的 bean 时，并且想要用一个属性只为它们其中的一个进行装配，
 * 在这种情况下，你可以使用 @Qualifier 注释和 @Autowired 注释通过指定哪一个真正的 bean 将会被装配来消除混乱。
 *
 * @author 2460798168@qq.com
 * @date 2019/12/26 15:29
 */
public class ProfileQar {

    @Autowired
    @Qualifier("qualifierTest1")
    private QualifierTest qualifierTest;

    public ProfileQar(){
        System.out.println("ProfileQar 无参构造方法" );
    }

    public void printAge() {
        System.out.println("Age : " + qualifierTest.getAge() );
    }

    public void printName() {
        System.out.println("Name : " + qualifierTest.getName() );
    }

}
