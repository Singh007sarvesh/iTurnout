package com.example.sarvesh.i_turnout;

public class ViewNotificationItem {
    private String subjectName;
    private String nid;
    private String date;
    private int picture;



    public ViewNotificationItem(String subjectName,String nid,String date, int picture) {
        this.subjectName=subjectName;
        this.nid=nid;
        this.date=date;
        this.picture = picture;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
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
}
