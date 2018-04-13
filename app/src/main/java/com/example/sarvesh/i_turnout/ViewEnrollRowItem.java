package com.example.sarvesh.i_turnout;

/**
 * Created by sarvesh on 1/4/18.
 */

public class ViewEnrollRowItem {
    private String subjectname;
    private String subjectid;

    public ViewEnrollRowItem(String subjectname,String subjectid) {

        this.subjectname= subjectname;
        this.subjectid=subjectid;

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
