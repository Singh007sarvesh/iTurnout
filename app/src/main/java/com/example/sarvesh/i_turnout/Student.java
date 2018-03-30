package com.example.sarvesh.i_turnout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class Student extends AppCompatActivity implements  View.OnClickListener {

    private CardView getnotification, makeQuery, viewcourses, checkattendance, changepassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);


        // getnotification=(CardView) findViewById(R.id.u_student);
         makeQuery=(CardView)findViewById(R.id.makequery);
        // viewcourses=(CardView)findViewById(R.id.u_course);
        //  checkattendance=(CardView)findViewById(R.id.u_enroll);
        changepassword = (CardView) findViewById(R.id.change);
        //add click listener to the class;
        // getnotification.setOnClickListener(this);
        //  makequery.setOnClickListener(this);
        // viewcourses.setOnClickListener(this);
        changepassword.setOnClickListener(this);
        makeQuery.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent in;
        switch (v.getId()) {
            case R.id.makequery: in = new Intent(Student.this, Query.class);
                startActivity(in);
                break;
           /* case R.id.u_teacher : in=new Intent(Admin.this,TeacherDetails.class);
                startActivity(in);
                break;
            case R.id.u_course : in=new Intent(Admin.this,CourseDetails.class);
                startActivity(in);
                break;
            case R.id.u_enroll : in=new Intent(Admin.this,StudentEnrollment.class);
                startActivity(in);
                break;*/
            case R.id.change:
                in = new Intent(Student.this, ChangePassword.class);
                startActivity(in);
                break;
        }
    }
}
