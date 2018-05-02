package com.example.sarvesh.i_turnout;

public class ViewStudentDetailsRowItem {
    private String studentName;
    private String studentId;

    public ViewStudentDetailsRowItem(String studentName,String studentId) {

        this.studentName= studentName;
        this.studentId=studentId;

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
}
