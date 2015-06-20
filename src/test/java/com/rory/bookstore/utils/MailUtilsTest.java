package com.rory.bookstore.utils;

import com.rory.bookstore.web.bean.SignatureVo;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by RoryGao on 15/6/17.
 */
public class MailUtilsTest {
    private SignatureVo mailVo = null;

    @Before
    public void setUp() throws Exception {
        mailVo = new SignatureVo("d123123123123", SignatureUtils.generateSignature("Rory", "roryblucky@gmail.com"), "roryblucky@gmail.com");
    }

    @Test
    public void testSendVerifyEmail() throws Exception {
        new Thread(new SendMail(mailVo)).start();
    }
}