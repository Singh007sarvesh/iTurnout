package com.example.sarvesh.i_turnout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class StudentEnrollment extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextsid, editTextcid;


    private Button esubmit;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_enrollment);
        editTextsid = (EditText)findViewById(R.id.studentenrollid);
        editTextcid = (EditText)findViewById(R.id.courseenrollid);
        esubmit = (Button)findViewById(R.id.studentenrollsubmit);

        progressDialog = new ProgressDialog(this);

        esubmit.setOnClickListener(this);


    }

    public void registerUser(){

        final String cid = editTextcid.getText().toString().trim();
        final String sid = editTextsid.getText().toString().trim();



        progressDialog.setMessage("Registering user...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                defConstant.URL_ENROLL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.hide();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                            Intent in=new Intent(getApplicationContext(),Admin.class);
                            startActivity(in);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("cid",cid);
                params.put("sid",sid);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View view){
        if(view == esubmit){
            registerUser();
        }
    }
    public void back(View v)
    {
        Intent in=new Intent(StudentEnrollment.this,Admin.class);
        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(in);
        finish();
    }

}