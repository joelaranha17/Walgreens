package com.firstapp.joel.walgreens.util.model;

/**
 * Created by joel on 11/25/2017.
 */

public class LoginDetails {
    public  String msg;
    public  String UserID;
    public  String UserName;
    public  String UserEmail;
    public  String UserMobile;
    public  String AppApiKey;

    public LoginDetails(String msg, String userID, String userName, String userEmail, String userMobile, String appApiKey) {
        this.msg = msg;
        this.UserID = userID;
        this.UserName = userName;
        this.UserEmail = userEmail;
        this.UserMobile = userMobile;
        this.AppApiKey = appApiKey;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public void setUserMobile(String userMobile) {
        UserMobile = userMobile;
    }

    public void setAppApiKey(String appApiKey) {
        AppApiKey = appApiKey;
    }

    public String getMsg() {
        return msg;
    }

    public String getUserID() {
        return UserID;
    }

    public String getUserName() {
        return UserName;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public String getUserMobile() {
        return UserMobile;
    }

    public String getAppApiKey() {
        return AppApiKey;
    }
}
