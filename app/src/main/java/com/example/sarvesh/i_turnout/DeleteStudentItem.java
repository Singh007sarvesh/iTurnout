package com.example.sarvesh.i_turnout;

public class DeleteStudentItem {


    private String studentName;
    private String studentId;
    private String sDate;

    public DeleteStudentItem(String studentName,String studentId,String sDate) {

        this.studentName=studentName;
        this.studentId=studentId;
        this.sDate=sDate;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getsDate() {
        return sDate;
    }

    public void setsDate(String sDate) {
        this.sDate = sDate;
    }
}
