package cn.com.codingce.codespring.entity;

import org.springframework.beans.factory.annotation.Required;

/**
 * Spring @Required 注释
 *
 * @author 2460798168@qq.com
 * @date 2019/12/26 9:17
 */
public class Student {

    private Integer age;
    private String name;
    @Required
    public void setAge(Integer age) {
        this.age = age;
    }
    public Integer getAge() {
        return age;
    }
    @Required
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
