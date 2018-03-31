package com.example.sarvesh.i_turnout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class Teacher extends AppCompatActivity implements View.OnClickListener {

    private CardView tgetnotification, respondQuery, tviewcourses, tcheckattendance, tchangepassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);


        tchangepassword = (CardView) findViewById(R.id.tchange);



        tchangepassword.setOnClickListener(this);
    }
    public void onClick(View v) {
        Intent in;
        switch (v.getId()) {
          //  case R.id.makequery: in = new Intent(Student.this, Query.class);
            //    startActivity(in);
            //    break;
           /* case R.id.u_teacher : in=new Intent(Admin.this,TeacherDetails.class);
                startActivity(in);
                break;
            case R.id.u_course : in=new Intent(Admin.this,CourseDetails.class);
                startActivity(in);
                break;
            case R.id.u_enroll : in=new Intent(Admin.this,StudentEnrollment.class);
                startActivity(in);
                break;*/
            case R.id.tchange:
                in = new Intent(Teacher.this, ChangePassword.class);
                startActivity(in);
                break;
        }
    }
}
