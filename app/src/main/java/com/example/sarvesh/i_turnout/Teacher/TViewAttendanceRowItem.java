package com.example.sarvesh.i_turnout.Teacher;

public class TViewAttendanceRowItem {


    private String subjectName;
    private String subjectId;


    public TViewAttendanceRowItem (String subjectName,String subjectId) {

        this.subjectName= subjectName;
        this.subjectId= subjectId;

    }

    public String getSubjectname() {
        return subjectName;
    }

    public void setSubjectname(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectid() {
        return subjectId;
    }

    public void setSubjectid(String subjectId) {
        this.subjectId = subjectId;
    }
}
