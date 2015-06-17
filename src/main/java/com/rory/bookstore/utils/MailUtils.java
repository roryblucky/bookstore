package com.rory.bookstore.utils;

import com.rory.bookstore.web.bean.SignatureVo;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by RoryGao on 15/6/17.
 */
public class MailUtils {

    private final static String USER_NAME = "312247435";
    private final static String PASSWORD = "gyh926555";

    public static void sendVerifyEmail(SignatureVo signatureVo) throws IOException, MessagingException {
        Properties pro = new Properties();
        pro.load(MailUtils.class.getClassLoader().getResourceAsStream("mail.properties"));

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

        String url = "http://localhost:8080/bookstore/user_activeUser.action?verifyCode=" + signatureVo.getSignature();
        String htmlText = "<html>" +
                "<body><p>您好，" + signatureVo.getUserName() + "：<br/>" +
                "<p>请点击以下连接激活账户（30分钟有效）</p>" +
                "<a href='" + url + "'>点我激活</a>" +
                "<p>Regards,<br/>Rory</p><body></html>";
        msg.setContent(htmlText, "text/html;charset=UTF-8");
        Transport.send(msg);
    }
}
