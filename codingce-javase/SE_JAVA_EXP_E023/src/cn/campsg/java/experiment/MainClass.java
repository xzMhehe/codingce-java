package cn.campsg.java.experiment;

/**
 * @author xzMa
 */
public class MainClass {
    public static void main(String[] args) {
        Score score = new Score(60.0f, 70.0f);
        Student student = new Student("黄世仁", score);
        System.out.println(student.getName() + "的实验成绩" + student.getScore().getExperiment() + "，项目成绩：" + score.getProject());
    }
}
