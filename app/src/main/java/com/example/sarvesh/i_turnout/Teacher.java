package com.example.sarvesh.i_turnout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class Teacher extends AppCompatActivity implements View.OnClickListener {

    private CardView tgetnotification, respondQuery, tviewcourses, tcheckattendance, tchangepassword,viewenrollstudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);


        tviewcourses = (CardView) findViewById(R.id.viewcourses);
        respondQuery =(CardView) findViewById(R.id.responedquery);
        tchangepassword = (CardView) findViewById(R.id.tchange);
        tcheckattendance = (CardView) findViewById(R.id.checkatten);
        viewenrollstudent = (CardView) findViewById(R.id.viewenrollstudent);


        tviewcourses.setOnClickListener(this);
        respondQuery.setOnClickListener(this);
        tchangepassword.setOnClickListener(this);
        tcheckattendance.setOnClickListener(this);
        viewenrollstudent.setOnClickListener(this);
    }
    public void onClick(View v) {
        Intent in;
        switch (v.getId()) {
           case R.id.responedquery: in = new Intent(Teacher.this, Query.class);
               startActivity(in);
               break;
            case R.id.viewcourses : in=new Intent(Teacher.this,AssignedCourses.class);
                startActivity(in);
                break;
            case R.id.checkatten : in=new Intent(Teacher.this,TCheckAttendance.class);
                startActivity(in);
                break;
           case R.id.viewenrollstudent : in=new Intent(Teacher.this,ViewEnrollStudent.class);
                startActivity(in);
                break;
            case R.id.tchange:
                in = new Intent(Teacher.this, ChangePassword.class);
                startActivity(in);
                break;
        }
    }
}
