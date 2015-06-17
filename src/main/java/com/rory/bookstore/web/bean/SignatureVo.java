package com.rory.bookstore.web.bean;

/**
 * Created by RoryGao on 15/6/17.
 */
public class SignatureVo {

    private String userName;
    private String signature;
    private String receiver;

    public SignatureVo(String userName, String signature, String receiver) {
        this.userName = userName;
        this.signature = signature;
        this.receiver = receiver;
    }

    public String getUserName() {
        return userName;
    }

    public String getSignature() {
        return signature;
    }


    public String getReceiver() {
        return receiver;
    }

}
