package com.example.sarvesh.i_turnout;

public class CheckEnStudInPerSubRowItem {


    private String studentName;

    public CheckEnStudInPerSubRowItem(String studentName) {

        this.studentName= studentName;
        // this.subjectid = subjectid;

    }

    public String getStudentname() {
        return studentName;
    }

    public void setStudentname(String studentName) {
        this.studentName = studentName;
    }
}
