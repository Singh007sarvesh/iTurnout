package com.example.sarvesh.i_turnout.Teacher;

public class TViewAttendanceRowItem {


    private String subjectname;
    private String subjectid;


    public TViewAttendanceRowItem (String subjectname,String subjectid) {

        this.subjectname= subjectname;
        this.subjectid= subjectid;

    }

    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    public String getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(String subjectid) {
        this.subjectid = subjectid;
    }
}
