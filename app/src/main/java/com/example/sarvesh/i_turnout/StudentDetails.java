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

public class StudentDetails extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextdid, editTextdname, editTextdpassword;

    private Button dsubmit;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
        editTextdid = (EditText)findViewById(R.id.studentdid);
        editTextdname = (EditText)findViewById(R.id.studentdname);

        editTextdpassword = (EditText)findViewById(R.id.studentdpassword);

        dsubmit = (Button)findViewById(R.id.StudentDSubmit);

        progressDialog = new ProgressDialog(this);

        dsubmit.setOnClickListener(this);


    }

    public void registerUser(){


        final String did = editTextdid.getText().toString().trim();
        final String dname = editTextdname.getText().toString().trim();

        final String dpassword = editTextdpassword.getText().toString().trim();

        progressDialog.setMessage("Registering user...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                defConstant.URL_STUDENTDETAILS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.hide();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getApplicationContext(),Admin.class);
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
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("did",did);
                params.put("dname",dname);
                params.put("dpassword",dpassword);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View view){
        if(view == dsubmit){
            registerUser();
        }
    }
    public void back(View v)
    {
        Intent in=new Intent(StudentDetails.this,Admin.class);
        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(in);

        finish();
    }
}