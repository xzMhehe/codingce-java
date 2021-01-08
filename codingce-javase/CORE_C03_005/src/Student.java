/**
 * 记得改改, 仅作参考, 自己学习
 * 本实验用于构建一个描述学生信息的实体类
 * @author xzMa
 */
public class Student {

    private String name;
    private int age;
    private boolean sex;
    private int score;

    public Student() {
    }

    public Student(String name, int age, boolean sex, int score) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        if (sex)
            return "男";
        else
            return "女";
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public static void main(String[] args) {
        Student stu = new Student("黄世仁", 24, true, 99);
        System.out.println(stu.getName());
        System.out.println(stu.getAge());
        System.out.println(stu.getSex());
        System.out.println(stu.getScore());
    }
}
