package cn.com.codingce.ioc;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/4 13:13
 */
public class Question {

    /**
     * 题干  答案
     */
    private String title;
    private String answer;

    public Question() {
    }

    public Question(String title, String answer) {
        this.title = title;
        this.answer = answer;
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
