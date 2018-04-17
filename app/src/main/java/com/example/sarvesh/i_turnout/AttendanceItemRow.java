package com.example.sarvesh.i_turnout;

import android.widget.CheckBox;

public class AttendanceItemRow {

    private String rollNumber;


    public AttendanceItemRow(String rollNumber) {
        this.rollNumber=rollNumber;

    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

}
