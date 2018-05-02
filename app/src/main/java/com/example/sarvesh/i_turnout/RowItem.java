package com.example.sarvesh.i_turnout;

/**
 * Created by sarvesh on 31/3/18.
 */

public class RowItem {

    private String subjectName;
    private String subjectId;
    public RowItem(String subjectName,String subjectId) {

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
