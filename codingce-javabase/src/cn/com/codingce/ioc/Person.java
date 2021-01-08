package cn.com.codingce.ioc;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/4 13:18
 */
public class Person {
    private String name;
    private Integer age;
    private String sex;

    public Person(String name, Integer age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
