package com.example.sarvesh.i_turnout;

/**
 * Created by sarvesh on 31/3/18.
 */

public class CheckAttRowItem {

    private String subjectName;
    private String subjectId;


    public CheckAttRowItem(String subjectName,String subjectId) {

        this.subjectName= subjectName;
        this.subjectId = subjectId;

    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }
}
