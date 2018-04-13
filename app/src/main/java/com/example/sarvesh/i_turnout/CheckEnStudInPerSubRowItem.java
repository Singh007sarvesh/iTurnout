package com.example.sarvesh.i_turnout;

public class CheckEnStudInPerSubRowItem {


    private String studentname;
   // private String studentid;
    //  private String subjectid;


    public CheckEnStudInPerSubRowItem(String studentname) {

        this.studentname= studentname;
        // this.subjectid = subjectid;

    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }
}
