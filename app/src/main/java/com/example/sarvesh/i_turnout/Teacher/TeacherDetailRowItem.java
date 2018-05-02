package com.example.sarvesh.i_turnout.Teacher;

public class TeacherDetailRowItem {

    private String teacherName;
    private String teacherId;

    public TeacherDetailRowItem(String teacherName, String teacherId) {
        this.teacherName = teacherName;
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }
}
