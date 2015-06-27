package com.rory.bookstore.web.controller;

import com.rory.bookstore.domain.User;
import com.rory.bookstore.service.IUserService;
import com.rory.bookstore.service.impl.UserServiceImpl;
import com.rory.bookstore.utils.BeanFactory;
import com.rory.bookstore.utils.SendMail;
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

    private IUserService service = BeanFactory.getInstance(UserServiceImpl.class);

    public String login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        User user = service.findUser(userName, password);

        if (user != null) {
            request.getSession().setAttribute("user", user);
            return "redirect:index.jsp";
        } else {
            request.setAttribute("errorMsg", "登录失败");
            return "result/error.jsp";
        }
    }

    public String logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("user");
        return "redirect:index.jsp";
    }

    public String registerUser(HttpServletRequest request, HttpServletResponse response) throws IOException, MessagingException {
        String userName = request.getParameter("userName");
        String userPassword = request.getParameter("passwd");
        String userPhoneNum = request.getParameter("phoneNum");
        String emailAddress = request.getParameter("email");
        String userAddress = request.getParameter("address");
        String verifyCode = SignatureUtils.generateSignature(userName, emailAddress);

        User user = new User(userName, userPassword, userPhoneNum, userAddress, emailAddress, verifyCode);
        service.addUser(user);

        //send mail
        SignatureVo signatureVo = new SignatureVo(userName, verifyCode, emailAddress);
        new Thread(new SendMail(signatureVo)).start();

        return "redirect:index.jsp";
    }

    public String activeUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = request.getParameter("userName");
        String verifyCode = request.getParameter("verifyCode");

        User user = service.findByCode(userName, verifyCode);

        if (user != null && !user.isActive()) {
            user.setIsActive(true);
            int result = service.updateUser(user);
            if (result != -1) {
                request.setAttribute("msg", "激活成功");
                return "result/success.jsp";
            } else {
                return "result/error.jsp";
            }
        } else if (user != null && user.isActive()) {
            request.setAttribute("msg", "账户已激活");
            return "result/success.jsp";
        } else {
            return "result/error.jsp";
        }

    }
}
