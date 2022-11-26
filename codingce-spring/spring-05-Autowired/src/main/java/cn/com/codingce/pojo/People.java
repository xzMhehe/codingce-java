package cn.com.codingce.pojo;

import javax.annotation.Resource;

public class People {

//    //如果显示的定义了Autowried的required属性为false, 说明这个对象可以为Null 否则不许为空
//    @Autowired
//    private Cat cat;
//    @Autowired
//    private Dog dog;

    //如果显示的定义了Autowried的required属性为false, 说明这个对象可以为Null 否则不许为空
    @Resource
    private Cat cat;
    @Resource
    private Dog dog;
    private String name;

    public People() {
    }

    public People(Cat cat, Dog dog, String name) {
        this.cat = cat;
        this.dog = dog;
        this.name = name;
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "People{" +
                "cat=" + cat +
                ", dog=" + dog +
                ", name='" + name + '\'' +
                '}';
    }
}
