package cn.com.codingce.codespring.entity;

/**
 * Spring @Qualifier 注释
 *
 * @author 2460798168@qq.com
 * @date 2019/12/26 15:29
 */
public class QualifierTest {

    private Integer age;
    private String name;
    public void setAge(Integer age) {
        this.age = age;
    }
    public Integer getAge() {
        return age;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "QualifierTest{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
