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

public class ChangePassword extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextold, editTextnew,editTextconfirm;

    private Button changesubmit;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        editTextold = (EditText)findViewById(R.id.changepassword);
        editTextnew = (EditText)findViewById(R.id.changepasswordnew);
        editTextconfirm = (EditText)findViewById(R.id.changepasswordconfirm);
        changesubmit = (Button)findViewById(R.id.changepasswordsubmit);

        progressDialog = new ProgressDialog(this);

        changesubmit.setOnClickListener(this);


    }

    public void registerUser(){

        final String changeold = editTextold.getText().toString().trim();
        final String changenew = editTextnew.getText().toString().trim();
        final String changeconfirm = editTextconfirm.getText().toString().trim();



        progressDialog.setMessage("Registering user...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                defConstant.URL_CHANGE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.hide();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"),Toast.LENGTH_LONG).show();
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
                params.put("changeold",changeold);
                params.put("changenew",changenew);
                params.put("changeconfirm",changeconfirm);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View view){
        if(view == changesubmit){
            registerUser();
        }
    }
    public void back(View v)
    {
        Intent in=new Intent(ChangePassword.this,Admin.class);
        startActivity(in);
    }

}