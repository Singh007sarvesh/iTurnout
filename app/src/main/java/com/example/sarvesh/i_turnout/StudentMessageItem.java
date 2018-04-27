package com.example.sarvesh.i_turnout;

public class StudentMessageItem {
    private String userName;
    private String userId;
    private String date;
    private int picture;
    private String qId;
    private String cid;

    public StudentMessageItem(String userName,String userId,String date, int picture,String qId,String cid) {
        this.userName=userName;
        this.userId=userId;
        this.date=date;
        this.picture = picture;
        this.qId=qId;
        this.cid=cid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        userId=userId+"\t\t"+date;
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

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}
