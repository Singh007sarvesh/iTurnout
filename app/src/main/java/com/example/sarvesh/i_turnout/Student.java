package com.example.sarvesh.i_turnout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class Student extends AppCompatActivity implements  View.OnClickListener {
    public TextView textCartItemCount;
    private CardView getnotification, makeQuery, viewcourse, checkattendance, changepassword;
    public String mCartItemCount = "10";
    private TextView studentName;
    private ImageButton logout;
    SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);


        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        HashMap<String,String> userdetails = sharedPrefManager.getUserDetails();
        //USER LOGIN



        studentName = findViewById(R.id.studentName);
        studentName.setText(userdetails.get(SharedPrefManager.KEY_NAME));

         getnotification=(CardView) findViewById(R.id.getnotification);
         makeQuery=(CardView)findViewById(R.id.makequery);
        viewcourse=(CardView)findViewById(R.id.viewcourses);
         checkattendance=(CardView)findViewById(R.id.checkattendance);
        changepassword = (CardView) findViewById(R.id.change);
        logout=findViewById(R.id.logout);
        //add click listener to the class;
        logout.setOnClickListener(this);
         getnotification.setOnClickListener(this);
          checkattendance.setOnClickListener(this);
        viewcourse.setOnClickListener(this);
        changepassword.setOnClickListener(this);
        makeQuery.setOnClickListener(this);
        //setContentView(R.layout.activity_student);
        TextView textView= (TextView) findViewById(R.id.getnotification1);
        textView.setText(mCartItemCount);
    }

    @Override
    public void onClick(View v) {
        Intent in;
        switch (v.getId()) {
            case R.id.makequery: in = new Intent(Student.this, Query.class);
                startActivity(in);
                break;
            case R.id.getnotification : in=new Intent(Student.this,ViewNotification.class);
                startActivity(in);
                break;
            case R.id.viewcourses:
                in=new Intent(Student.this,CheckAttByStudent.class);
                startActivity(in);
                break;
           case R.id.checkattendance :
               in=new Intent(Student.this,CheckAttendance.class);
                startActivity(in);
                break;
            case R.id.change:
                in = new Intent(Student.this, ChangePassword.class);

                startActivity(in);
                break;
            case R.id.logout:
                sharedPrefManager.logoutUser();
                finish();
                in = new Intent(Student.this, Home.class);
                startActivity(in);
                break;
        }
    }


}
