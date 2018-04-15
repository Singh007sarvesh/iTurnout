package com.example.sarvesh.i_turnout.Teacher;

public class SearchRowItem {

   private String studentname;
   private String studentid;
  public SearchRowItem(String studentname,String studentid)
   {
       this.studentname=studentname;
       this.studentid=studentid;
   }

    public String getStudentname() {
      studentname=studentname+"\n\n"+studentid;
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }
}
