package com.example.sarvesh.i_turnout;

public class DeleteItemforStudent {
    private String studentName;
    private String studentId;
    private String date;

    public DeleteItemforStudent(String studentName,String studentId,String date) {

        this.studentName= studentName;
        this.studentId=studentId;
        this.date=date;
    }

    public String getStudentName() {
        studentName=studentName+"\n\n"+studentId+"\n\n"+date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
