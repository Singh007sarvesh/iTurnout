package com.example.sarvesh.i_turnout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class Admin extends AppCompatActivity implements View.OnClickListener {
    SharedPrefManager sharedPrefManager;
    private TextView AdminName;
    private CardView ustudent,uteacher,ucourse,uenroll,udelete;
    private Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        HashMap<String,String> userdetails = sharedPrefManager.getUserDetails();
        //USER LOGIN



        AdminName = findViewById(R.id.adminname);
        AdminName.setText(userdetails.get(SharedPrefManager.KEY_NAME));

        //defining cards
        ustudent=(CardView) findViewById(R.id.u_student);
        uteacher=(CardView)findViewById(R.id.u_teacher);
        ucourse=(CardView)findViewById(R.id.u_course);
        uenroll=(CardView)findViewById(R.id.u_enroll);
        udelete=(CardView) findViewById(R.id.del);
        logout=findViewById(R.id.adminlogout);
        //add click listener to the class;
        logout.setOnClickListener(this);
        //add click listener to the class;
        ustudent.setOnClickListener(this);
        uteacher.setOnClickListener(this);
        ucourse.setOnClickListener(this);
        uenroll.setOnClickListener(this);
        udelete.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent in;
        switch (v.getId()) {
            case R.id.u_student: in = new Intent(Admin.this, StudentDetails.class);
                startActivity(in);
                finish();
                break;
            case R.id.u_teacher : in=new Intent(Admin.this,TeacherDetails.class);
                startActivity(in);
                finish();
                break;
            case R.id.u_course : in=new Intent(Admin.this,CourseDetails.class);
                startActivity(in);
                finish();
                break;
            case R.id.u_enroll : in=new Intent(Admin.this,StudentEnrollment.class);
                startActivity(in);
                finish();
                break;
            case R.id.del : in =new Intent(Admin.this,DeleteUser.class);
                startActivity(in);
                finish();
               break;
            case R.id.adminlogout:
                sharedPrefManager.logoutUser();
                finish();
                in = new Intent(Admin.this, Home.class);
                startActivity(in);
                break;
        }

    }
}
