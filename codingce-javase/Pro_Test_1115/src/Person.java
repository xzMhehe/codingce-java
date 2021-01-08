import java.io.Serializable;

/**
 * @describe 标识一个类的对象可以被序列化。
 */
public class Person implements Serializable {

    /**
     * @describe 定义程序序列化ID
     */
    private static final long serialVersionUID = 1L;
    /**
     * @describe 姓名
     */
    private String name;
    /**
     * @describe 年龄
     */
    private int age;


    /**
     * @param name 姓名
     * @param age  年龄
     * @describe 两个参数的构造方法
     */
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * @describe 格式化输出
     */
    @Override
    public String toString() {
        return "Person{" + "name='" + name + '\'' + ", age=" + age + '}';
    }
}
