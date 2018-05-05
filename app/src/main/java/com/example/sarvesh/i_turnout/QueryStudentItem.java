package com.example.sarvesh.i_turnout;

public class QueryStudentItem {
    private String  studentId;
    private String studentName;
    public QueryStudentItem(String studentId,String studentName)
    {
        this.studentId=studentId;
        this.studentName=studentName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
