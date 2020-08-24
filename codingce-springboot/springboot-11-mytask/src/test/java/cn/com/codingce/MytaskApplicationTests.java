package cn.com.codingce;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class MytaskApplicationTests {

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Test
    void contextLoads() {
        //一个简单的邮件
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("xzMhehe你好啊");
        simpleMailMessage.setText("谢谢你");
        simpleMailMessage.setTo("2606704712@qq.com");
        simpleMailMessage.setFrom("2460798168@qq.com");
        mailSender.send(simpleMailMessage);
    }

    @Test
    void contextLoads1() throws MessagingException {
        //一个复杂的邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        //组装
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        mimeMessageHelper.setSubject("xzMhehe你好啊");
        mimeMessageHelper.setText("<p style = 'color:red'>谢谢你</p>", true);

        //附件
        mimeMessageHelper.addAttachment("myqr.png", new File("E:\\myqr.png"));
//        mimeMessageHelper.addAttachment("myqr.png", new File("E:\\myqr.png"));
        mimeMessageHelper.setTo("2606704712@qq.com");
        mimeMessageHelper.setFrom("2460798168@qq.com");
        mailSender.send(mimeMessage);
    }

    @Test
    public void sendMail(Boolean html, String subject, String text) throws MessagingException {
        //一个复杂的邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        //组装
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        mimeMessageHelper.setSubject("xzMhehe你好啊");
        mimeMessageHelper.setText("<p style = 'color:red'>谢谢你</p>", true);
        //附件
        mimeMessageHelper.addAttachment("myqr.png", new File("E:\\myqr.png"));
//        mimeMessageHelper.addAttachment("myqr.png", new File("E:\\myqr.png"));
        mimeMessageHelper.setTo("2606704712@qq.com");
        mimeMessageHelper.setFrom("2460798168@qq.com");
        mailSender.send(mimeMessage);
    }

}
