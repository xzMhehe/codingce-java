package testone;

public class MainClass {
    public static void main(String[] args) {
        Student student = new Student("张三", 18);
        System.out.println(student.show());
        UnderGraduate underGraduate = new UnderGraduate("李四", 21, "学士学位");
        System.out.println(underGraduate.show());
    }
}
