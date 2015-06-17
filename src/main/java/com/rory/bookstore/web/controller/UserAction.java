package com.rory.bookstore.web.controller;

import com.rory.bookstore.domain.User;
import com.rory.bookstore.service.IUserService;
import com.rory.bookstore.service.impl.UserService;
import com.rory.bookstore.utils.BeanFactory;
import com.rory.bookstore.utils.MailUtils;
import com.rory.bookstore.utils.SignatureUtils;
import com.rory.bookstore.web.bean.SignatureVo;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by RoryGao on 15/6/13.
 */

public class UserAction {

    private IUserService service = BeanFactory.getInstance(UserService.class);

    public String login(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("hah");
        return "index.jsp";
    }

    public String registerUser(HttpServletRequest request, HttpServletResponse response) throws IOException, MessagingException {
        String userName = request.getParameter("userName");
        String userPassword = request.getParameter("passwd");
        String userPhoneNum = request.getParameter("phoneNum");
        String emailAddress = request.getParameter("email");
        String verifyCode = SignatureUtils.generateSignature(userName, emailAddress);

        User user = new User(userName, userPassword, userPhoneNum, emailAddress, verifyCode);
        service.addUser(user);

        //send mail
        SignatureVo signatureVo = new SignatureVo(userName, verifyCode, emailAddress);
        new Thread(new SendMail(signatureVo)).start();

        return "index.jsp";
    }

    public String activeUser(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    private class SendMail implements Runnable {
        private SignatureVo signatureVo = null;

        public SendMail(SignatureVo signatureVo) {
            this.signatureVo = signatureVo;
        }

        @Override
        public void run() {
            try {
                MailUtils.sendVerifyEmail(signatureVo);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }


}
