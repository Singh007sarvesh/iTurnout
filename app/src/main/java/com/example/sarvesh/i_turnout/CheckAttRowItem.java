package com.example.sarvesh.i_turnout;

/**
 * Created by sarvesh on 31/3/18.
 */

public class CheckAttRowItem {

    private String subjectcheck;
  //  private String subjectid;


    public CheckAttRowItem(String subjectcheck) {

        this.subjectcheck= subjectcheck;
       // this.subjectid = subjectid;

    }
    public String getSubject() {
        return subjectcheck;
    }

   /* public String getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(String subjectid) {
        this.subjectid = subjectid;
    }*/

    public void setSubjectCheck(String subjectcheck) {
        this.subjectcheck = subjectcheck;
    }


}
