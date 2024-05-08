package com.example.myapplication.quizAndUsers;

public class Users {
    String uid;
    String userType;

    public Users(String uid, String userType) {
        this.uid = uid;
        this.userType = userType;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
