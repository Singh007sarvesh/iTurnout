package com.example.sarvesh.i_turnout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Student extends AppCompatActivity implements  View.OnClickListener {

    private CardView getNotification,viewCourse, checkAttendance, changePassword,getMessage;
    private String mCartItemCount ="";
    private TextView studentName;
    private ImageButton logout;
    private SharedPrefManager sharedPrefManager;
    private String userId="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!(new SharedPrefManager(this).isLoggedIn()))
        {
            Intent intent = new Intent(this, Home.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK |
                    Intent.FLAG_ACTIVITY_NEW_TASK);

            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
        setContentView(R.layout.activity_student);
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        HashMap<String,String> userDetails = sharedPrefManager.getUserDetails();
        userId = userDetails.get(SharedPrefManager.KEY_Id);
        studentName = findViewById(R.id.studentName);
        studentName.setText(userDetails.get(SharedPrefManager.KEY_NAME));
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
       // TextView textView=  findViewById(R.id.getnotification1);
        getTotalNotification();

    }

    public void getTotalNotification()
    {
       final TextView textView1=  findViewById(R.id.getnotification1);
       // textView1.setText(mCartItemCount);
        StringRequest request=new StringRequest(Request.Method.POST,
                defConstant.URl_GetTotal,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Toast.makeText(getApplicationContext(),userid,Toast.LENGTH_LONG).show();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            mCartItemCount=jsonObject.getString("message");
                            textView1.setText(mCartItemCount);

                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("studentId", userId);
                return params;
            }

        };
        RequestHandler.getInstance(this).addToRequestQueue(request);
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
