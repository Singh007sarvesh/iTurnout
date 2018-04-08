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

public class TeacherDetails extends AppCompatActivity implements View.OnClickListener{

    private EditText editTexttid, editTexttname, editTexttpassword;

    private Button tsubmit;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_details);
        editTexttid = (EditText)findViewById(R.id.tid);
        editTexttname = (EditText)findViewById(R.id.tname);

        editTexttpassword = (EditText)findViewById(R.id.tpassword);

        tsubmit = (Button)findViewById(R.id.tsubmit);

        progressDialog = new ProgressDialog(this);

        tsubmit.setOnClickListener(this);


    }

    public void registerUser() {


        final String tid = editTexttid.getText().toString().trim();
        final String tname = editTexttname.getText().toString().trim();
        final String password = editTexttpassword.getText().toString().trim();
        if (tid.length() == 9 && (tid.charAt(0) == 't' || tid.charAt(0)=='T') && tid.matches("[a-zA-Z0-9]+"))
        {
            if(tname.matches("[a-zA-Z ]+")  && tname.length()>3)
            {
                if(password.length()<5)
                {
                    Toast.makeText(getApplicationContext(), "Plz fill password in a proper way", Toast.LENGTH_LONG).show();
                    return;
                }
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Plz fill name in a proper way", Toast.LENGTH_LONG).show();
                return;
            }
            progressDialog.setMessage("Registering user...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                defConstant.URL_SIGNUP,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.hide();
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
                params.put("tid", tid);
                params.put("tname", tname);
                params.put("password", password);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
    else
        {
            Toast.makeText(getApplicationContext(), "Please fill id in a proper way", Toast.LENGTH_LONG).show();
            return;
        }
    }

    @Override
    public void onClick(View view){
        if(view == tsubmit){
            registerUser();
        }
    }
    public void back(View v)
    {
        Intent in=new Intent(TeacherDetails.this,Admin.class);
        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(in);
        finish();
    }
}