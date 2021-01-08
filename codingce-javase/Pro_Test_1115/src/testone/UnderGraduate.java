package testone;

public class UnderGraduate extends Student {
    private String degree;

    public UnderGraduate() {
    }

    public UnderGraduate(String degree) {
        this.degree = degree;
    }

    public UnderGraduate(String name, int age, String degree) {
        super(name, age);
        this.degree = degree;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    @Override
    public String show() {
        return "Student{" +
                "name='" + super.getName() + '\'' +
                ", age=" + super.getAge() +
                ", degree='" + degree + '\'' +
                '}';
    }
}
