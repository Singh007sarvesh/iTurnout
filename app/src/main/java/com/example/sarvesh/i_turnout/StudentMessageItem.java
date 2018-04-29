package com.example.sarvesh.i_turnout;

public class StudentMessageItem {
    private String userName;
    private String userId;
    private int picture;
    private String date;
    private String qId;


    public StudentMessageItem(String userName,String userId, int picture,String date,String qId) {
        this.userName=userName;
        this.userId=userId;
        this.picture = picture;
        this.date=date;
        this.qId=qId;

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public String getqId() {
        return qId;
    }

    public void setqId(String qId) {
        this.qId = qId;
    }

}
