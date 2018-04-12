package com.example.sarvesh.i_turnout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class Teacher extends AppCompatActivity implements View.OnClickListener {

    private CardView tgetnotification, respondQuery, tviewcourses, tcheckattendance, tchangepassword,viewenrollstudent;
    private TextView teachernameforlogin;
    SharedPrefManager sharedPrefManager;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        HashMap<String,String> userdetails = sharedPrefManager.getUserDetails();
      //  Toast.makeText(getApplicationContext(),userdetails.get(SharedPrefManager.KEY_Login),Toast.LENGTH_LONG).show();

        teachernameforlogin = findViewById(R.id.teachernameforlogin);
        teachernameforlogin.setText(userdetails.get(SharedPrefManager.KEY_NAME));


        tgetnotification=(CardView)findViewById(R.id.tgetnotification);
        tviewcourses = (CardView) findViewById(R.id.viewcourses);
        respondQuery =(CardView) findViewById(R.id.responedquery);
        tchangepassword = (CardView) findViewById(R.id.tchange);
        tcheckattendance = (CardView) findViewById(R.id.checkatten);
        viewenrollstudent = (CardView) findViewById(R.id.viewenrollstudent);
        logout=findViewById(R.id.teacherlogout);
        //add click listener to the class;
        logout.setOnClickListener(this);
        tgetnotification.setOnClickListener(this);
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
            case R.id.tgetnotification: in = new Intent(Teacher.this, TViewNotification.class);
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
            case R.id.teacherlogout:
                sharedPrefManager.logoutUser();
                finish();
                in = new Intent(Teacher.this, Home.class);
                startActivity(in);
                break;
        }
    }
}
