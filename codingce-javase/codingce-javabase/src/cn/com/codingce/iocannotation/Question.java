package cn.com.codingce.iocannotation;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/7 11:33
 */
public class Question {

    /**
     * 实体对象 只有属性 共有的方法 无参数构造方法
     */
    private String title;
    private String answer;

    @MyAnnotation({"1+1=？ A.2 B.3 C4 D.5","A"})
    public Question() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "title='" + title + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
