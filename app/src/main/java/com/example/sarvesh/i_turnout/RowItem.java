package com.example.sarvesh.i_turnout;

/**
 * Created by sarvesh on 31/3/18.
 */

public class RowItem {

    private String subjectname;
    private String subjectid;


    public RowItem(String subjectname,String subjectid) {

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
