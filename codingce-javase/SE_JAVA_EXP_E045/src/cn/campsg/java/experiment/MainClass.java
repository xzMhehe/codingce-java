package cn.campsg.java.experiment;

import cn.campsg.java.experiment.entity.Postman;
import cn.campsg.java.experiment.entity.SendMails;

public class MainClass {
    public static void main(String[] args) {
        System.out.println("邮递员接收派发任务后开始送信：");
        for (int i = 0; i < 5; i++) {
            int mCount = (int) (1 + Math.random() * 10);
            Postman postman = new Postman("邮递员" + i, mCount / 2);
            SendMails sendMails = new SendMails();
            sendMails.setPost(postman);
            new Thread(sendMails).start();
        }
    }
}
