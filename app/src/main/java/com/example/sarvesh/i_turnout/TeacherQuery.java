package com.example.sarvesh.i_turnout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.sarvesh.i_turnout.Teacher.TViewNotification;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TeacherQuery extends AppCompatActivity implements View.OnClickListener{
    private static String userId="";
    private SharedPrefManager sharedPrefManager;
    private static String cid="";
    private static String studentId="";
    private EditText editText;
    private FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_query);
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        HashMap<String,String> userDetails = sharedPrefManager.getUserDetails();
        userId = userDetails.get(SharedPrefManager.KEY_Id);
        Intent in=getIntent();
        studentId=in.getStringExtra("studentId");
        editText=findViewById(R.id.QTeacher);
        floatingActionButton=findViewById(R.id.TQButton);
        floatingActionButton.setOnClickListener(this);
    }
    private void sendData()
    {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Wait...");
        progressDialog.show();
        StringRequest stringRequest=new StringRequest(Request.Method.POST,
                defConstant.URL_TeacherQuery,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.hide();
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            String Response=jsonObject.getString("message");
                            Toast.makeText(getApplicationContext(),Response,Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), TViewNotification.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                    Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                    Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(i);
                            finish();
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
               params.put("content",editText.getText().toString().trim());
                params.put("receiverId",studentId);
                params.put("senderId",userId);
                return params;
            }
        };
        MySingleton.getmInstance(TeacherQuery.this).addToRequestQue(stringRequest);
    }

    @Override
    public void onClick(View v) {
        if(v==floatingActionButton)
            sendData();
    }
}
