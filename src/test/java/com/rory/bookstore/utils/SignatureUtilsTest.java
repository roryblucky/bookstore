package com.rory.bookstore.utils;

import org.junit.Test;

/**
 * Created by RoryGao on 15/6/17.
 */
public class SignatureUtilsTest {

    @Test
    public void testGenerateSignature() throws Exception {
        String userName = "Rory";
        String emailAddress = "rorblucky@gmail.com";
        System.out.println(SignatureUtils.generateSignature(userName, emailAddress));
    }
}