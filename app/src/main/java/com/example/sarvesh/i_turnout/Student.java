package com.example.sarvesh.i_turnout;

import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.HashMap;

public class Student extends AppCompatActivity implements  View.OnClickListener {
    private TextView textCartItemCount;
    private int curNotificationId=0;
    private CardView getNotification, makeQuery, viewCourse, checkAttendance, changePassword,getMessage;
    private String mCartItemCount = "10";
    private TextView studentName;
    private ImageButton logout;
    SharedPrefManager sharedPrefManager;


    private Bitmap icon;
    private String notificationTitle;
    private String notificationText;
    private int combineNotificationCounter;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!(new SharedPrefManager(this).isLoggedIn()))
        {
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        setContentView(R.layout.activity_student);
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        HashMap<String,String> userdetails = sharedPrefManager.getUserDetails();

        studentName = findViewById(R.id.studentName);
        studentName.setText(userdetails.get(SharedPrefManager.KEY_NAME));

         getNotification= findViewById(R.id.getnotification);

        viewCourse=findViewById(R.id.viewcourses);
         checkAttendance=findViewById(R.id.checkattendance);
        changePassword = findViewById(R.id.change);
        logout=findViewById(R.id.logout);
        getMessage=findViewById(R.id.sGetMessage);
        //add click listener to the class;
        logout.setOnClickListener(this);
         getNotification.setOnClickListener(this);
          checkAttendance.setOnClickListener(this);
        viewCourse.setOnClickListener(this);
        changePassword.setOnClickListener(this);

        getMessage.setOnClickListener(this);
        //setContentView(R.layout.activity_student);
        TextView textView=  findViewById(R.id.getnotification1);
        textView.setText(mCartItemCount);


    }


    @Override
    public void onClick(View v) {
        Intent in;
        switch (v.getId()) {
            case R.id.sGetMessage:
                in=new Intent(Student.this,MessageForStudent.class);
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
