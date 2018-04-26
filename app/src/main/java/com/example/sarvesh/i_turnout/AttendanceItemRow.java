package com.example.sarvesh.i_turnout;


public class AttendanceItemRow {


     String studentName;
    String rollNumber;


    public AttendanceItemRow(String studentName,String rollNumber) {

        this.studentName=studentName;
        this.rollNumber=rollNumber;

    }
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public String getRollNumber() {

        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }


}
