package cn.campsg.java.experiment.entity;

/**
 * @author xzMa
 */
public class SendMails extends Thread {
    private Postman post;

    public Postman getPost() {
        return post;
    }

    public void setPost(Postman post) {
        this.post = post;
    }

    public SendMails() {
    }

    @Override
    public void run() {
        int count = 0;
        while (post.getMailCount() > 0) {
            System.out.println(post.getPostName() + " 还有" + post.getMailCount() + "封信。开始送第 " + (++count) + "封信！");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            post.setMailCount(post.getMailCount() - 1);
        }
        System.out.println(post.getPostName() + " 已完成所有邮件派送！。");
    }
}

