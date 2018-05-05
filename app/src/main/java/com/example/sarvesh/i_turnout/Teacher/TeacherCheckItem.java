package com.example.sarvesh.i_turnout.Teacher;

public class TeacherCheckItem {
    private String studentName;
    private String studentId;
    private String totalAttendance;
    public TeacherCheckItem(String studentName,String studentId,String totalAttendance)
    {
        this.studentName=studentName;
        this.studentId=studentId;
        this.totalAttendance=totalAttendance;
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

    public String getTotalAttendance() {
        return totalAttendance;
    }

    public void setTotalAttendance(String totalAttendance) {
        this.totalAttendance = totalAttendance;
    }
}
