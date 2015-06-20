package com.rory.bookstore.utils;

import com.rory.bookstore.web.bean.SignatureVo;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Properties;

/**
 * Created by RoryGao on 15/6/17.
 */
public class SendMail implements Runnable {

    private final static String USER_NAME = "312247435";
    private final static String PASSWORD = "gyh926555";
    private SignatureVo signatureVo = null;

    public SendMail(SignatureVo signatureVo) {
        this.signatureVo = signatureVo;
    }

    @Override
    public void run() {
        try {
            Properties pro = new Properties();
            pro.load(SendMail.class.getClassLoader().getResourceAsStream("mail.properties"));

            Session session = Session.getInstance(pro, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(USER_NAME, PASSWORD);
                }
            });

            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(USER_NAME + "@qq.com", MimeUtility.encodeWord("XXXXX书城")));
            msg.setRecipients(MimeMessage.RecipientType.TO, signatureVo.getReceiver());
            msg.setSubject("欢迎来到XXXXX书城");

            String url = "http://localhost:8080/bookstore/user_activeUser.action?userName="
                    + URLEncoder.encode(signatureVo.getUserName(), "UTF-8")
                    + "&verifyCode=" + signatureVo.getSignature();

            String htmlText = "<html>" +
                    "<body><p>您好，" + signatureVo.getUserName() + "：<br/>" +
                    "<p>请点击以下连接激活账户</p>" +
                    "<a href='" + url + "' target='_blank'>点我激活</a>" +
                    "<p>Regards,<br/>Rory</p><body></html>";
            msg.setContent(htmlText, "text/html;charset=UTF-8");
            Transport.send(msg);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
