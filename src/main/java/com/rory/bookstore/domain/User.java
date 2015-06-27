package com.rory.bookstore.domain;

/**
 * Created by RoryGao on 15/6/13.
 */
public class User {
    private String id;
    private String name;
    private String pwd;
    private String phoneNum;
    private String emailAddress;
    private String address;
    private String verifyCode;
    private boolean isActive;


    public User() {
    }

    public User(String id, String name, String pwd, String phoneNum, String address, String emailAddress, String verifyCode, boolean isActive) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.phoneNum = phoneNum;
        this.address = address;
        this.emailAddress = emailAddress;
        this.verifyCode = verifyCode;
        this.isActive = isActive;
    }

    public User(String name, String pwd, String phoneNum, String address, String emailAddress, String verifyCode) {
        this.name = name;
        this.pwd = pwd;
        this.phoneNum = phoneNum;
        this.address = address;
        this.emailAddress = emailAddress;
        this.verifyCode = verifyCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }


    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

}
