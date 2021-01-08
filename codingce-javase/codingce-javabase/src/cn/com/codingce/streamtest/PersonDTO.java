package cn.com.codingce.streamtest;

import java.util.List;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/21 9:12
 */
public class PersonDTO {

    private String name;
    private Integer age;
    private String gender;
    private List<String> hobby;

    public PersonDTO() {
    }

    public PersonDTO(String name, Integer age, String gender, List<String> hobby) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.hobby = hobby;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<String> getHobby() {
        return hobby;
    }

    public void setHobby(List<String> hobby) {
        this.hobby = hobby;
    }

}
