package com.example.sarvesh.i_turnout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AttendanceTeacher extends AppCompatActivity {
    private static String studentId="";
    private static String subjectId="";
    private static int totalClass=0,totalClass3=0,totalClass2=0;
    private static int pre=0;
    SharedPrefManager sharedPrefManager;
    private static String userId="";
    private TextView totalClass1,presence,absent,percentage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_teacher);
        Intent intent=getIntent();
        studentId=intent.getStringExtra("studentId");
        subjectId=intent.getStringExtra("courseId");
        totalClass1=findViewById(R.id.ttotalClass);
        presence=findViewById(R.id.tpresence);
        absent=findViewById(R.id.tabsent);
        percentage=findViewById(R.id.tpercetage);
        getAttendanceDetails();

    }
    public void getAttendanceDetails()
    {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                defConstant.URL_STUDENTATTENDANCE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray array=jsonObject.getJSONArray("flag");

                            JSONObject o= array.getJSONObject(0);
                            JSONObject o1= array.getJSONObject(0);
                            JSONObject o2= array.getJSONObject(0);
                            String res=o.getString("message");
                            String res1=o1.getString("message1");
                            String res2=o2.getString("message2");
                            totalClass=Integer.parseInt(res);
                            totalClass3=Integer.parseInt(res1);
                            totalClass2=Integer.parseInt(res2);
                            pre=(totalClass3*100);
                            try
                            {
                                pre=(pre/totalClass);
                            }
                            catch(ArithmeticException e)
                            {
                                e.printStackTrace();
                            }
                            String s=String.valueOf(totalClass);
                            String s1=String.valueOf(totalClass3);
                            String s2=String.valueOf(totalClass2);
                            String s3=String.valueOf(pre+" %");
                            totalClass1.setText(s);
                            presence.setText(s1);
                            absent.setText(s2);
                            percentage.setText(s3);
                            // absent.setText(Response2);
                            //Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
                            // Toast.makeText(AttendanceInPerticuSubject.this,Response, Toast.LENGTH_LONG).show();
                            // Intent i = new Intent(getApplicationContext(), AttendanceInPerticuSubject.class);
                            // startActivity(i);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("userId", studentId);
                params.put("subjectId",subjectId);
                // Toast.makeText(getApplicationContext(),"hello---", Toast.LENGTH_LONG).show();
                return params;
            }

        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
}
