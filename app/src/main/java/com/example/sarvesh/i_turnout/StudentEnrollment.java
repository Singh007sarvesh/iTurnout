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
import com.example.sarvesh.i_turnout.Moderator.Admin;


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
        editTextsid = findViewById(R.id.studentenrollid);
        editTextcid = findViewById(R.id.courseenrollid);
        esubmit = findViewById(R.id.studentenrollsubmit);

        progressDialog = new ProgressDialog(this);

        esubmit.setOnClickListener(this);


    }

    public void registerUser(){

        final String cid = editTextcid.getText().toString().trim();
        final String sid = editTextsid.getText().toString().trim();


        if(sid.length()==9 && sid.matches("[a-zA-Z0-9]+") && (sid.charAt(0)=='m' || sid.charAt(0)=='M' || sid.charAt(0)=='b' || sid.charAt(0)=='B'))
        {
            if(cid.length()>5  && cid.matches("[a-zA-Z0-9]+"));
            else
            {
                Toast.makeText(getApplicationContext(),"Plz fill course id in a proper way",Toast.LENGTH_LONG).show();
                return;
            }
            progressDialog.setMessage("Enrolling user...");
            progressDialog.show();

            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    defConstant.URL_ENROLL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.hide();
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                               Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                                Intent in = new Intent(getApplicationContext(), Admin.class);
                                startActivity(in);
                                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.hide();
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("cid", cid.toUpperCase());
                    params.put("sid", sid.toUpperCase());
                    return params;
                }
            };
            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Plz fill Student id in a proper way", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onClick(View view){
        if(view == esubmit){
            registerUser();
        }
    }


}