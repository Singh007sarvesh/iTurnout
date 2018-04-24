package com.example.sarvesh.i_turnout.Moderator;

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
import com.example.sarvesh.i_turnout.R;
import com.example.sarvesh.i_turnout.RequestHandler;
import com.example.sarvesh.i_turnout.defConstant;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CourseDetails extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextcid, editTextcname,editTextctid;


    private Button csubmit;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);
        editTextcid = (EditText)findViewById(R.id.cid);
        editTextcname = (EditText)findViewById(R.id.cname);
        editTextctid =(EditText) findViewById(R.id.ctid);
        csubmit = (Button)findViewById(R.id.csubmit);

        progressDialog = new ProgressDialog(this);

        csubmit.setOnClickListener(this);


    }

    public void registerUser(){


        final String cid = editTextcid.getText().toString().trim();
        final String cname = editTextcname.getText().toString().trim();
        final String ctid = editTextctid.getText().toString().trim();
        if(cid.length()>5 && cid.matches("[a-zA-Z0-9]+") )
        {
            if(cname.length()>4 && cname.matches("[a-zA-Z ]+"))
            {
                if(ctid.length()==9 && ctid.matches("[a-zA-Z0-9]+"));
                else
                {
                    Toast.makeText(getApplicationContext(), "Plz fill teacher id in a proper way", Toast.LENGTH_LONG).show();

                }
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Plz fill Course name in a proper way", Toast.LENGTH_LONG).show();
                return;
            }
            progressDialog.setMessage("Registering Courses...");
            progressDialog.show();

            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    defConstant.URL_COURSEDETAILS,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.hide();
                            //  Toast.makeText(getApplicationContext(), "in response",Toast.LENGTH_LONG).show();
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                                Intent i = new Intent(getApplicationContext(), Admin.class);
                                startActivity(i);
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
                    params.put("cid", cid);
                    params.put("cname", cname);
                    params.put("ctid", ctid);
                    return params;
                }
            };
            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Plz Enter Course id in a proper way ", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onClick(View view){
        if(view == csubmit){
            registerUser();
        }
    }

}