package com.example.sarvesh.i_turnout;

public class TViewNotificationItem {
    private String name;
    private int picture;

    public TViewNotificationItem(String name, int picture) {
        this.name = name;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public int getPicture() {
        return picture;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }
}