package com.example.sarvesh.i_turnout;

public class DeleteTeacherItem {

    private String teacherName;
    private String teacherId;
    private String date;

    public DeleteTeacherItem(String teacherName,String teacherId,String date) {

        this.teacherName= teacherName;
        this.teacherId=teacherId;
        this.date=date;
    }

    public String getTeacherName() {
        teacherName=teacherName+"\n\n"+teacherId+"\n\n"+date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
