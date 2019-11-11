package cn.com.codingce.entity;

/**
 * @author 2460798168@qq.com
 * @date 2019/11/11 12:58
 */
public class Bar {
    private String name;

    public Bar() {
    }

    public Bar(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Bar{" +
                "name='" + name + '\'' +
                '}';
    }
}
