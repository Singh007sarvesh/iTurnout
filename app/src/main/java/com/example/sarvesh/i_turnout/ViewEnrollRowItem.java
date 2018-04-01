package com.example.sarvesh.i_turnout;

/**
 * Created by sarvesh on 1/4/18.
 */

public class ViewEnrollRowItem {
    private String studentid;

    public ViewEnrollRowItem(String studentid) {

        this.studentid= studentid;

    }
    public String getStudentId() {
        return studentid;
    }

    public void setStudentId(String studentid) {
        this.studentid = studentid;
    }
}
