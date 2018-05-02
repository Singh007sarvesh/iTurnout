package com.example.sarvesh.i_turnout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class updateEnrollment extends AppCompatActivity implements View.OnClickListener {

    private EditText editText,editText1,editText2;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_enrollment);
        editText=findViewById(R.id.studentIdEnrollment);
        editText1=findViewById(R.id.oldEnrollment);
        editText2=findViewById(R.id.newEnrollment);
        button=findViewById(R.id.updateEnroll);
        button.setOnClickListener(this);

    }

    public void update()
    {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Updating...");
        progressDialog.show();

        final String sid = editText.getText().toString().trim().toUpperCase();
        final String oldCid = editText1.getText().toString().trim().toUpperCase();
        final String newCid = editText2.getText().toString().trim().toUpperCase();
        StringRequest stringRequest=new StringRequest(Request.Method.POST,defConstant.URL_UpdateEnrollment,
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
                params.put("studentId", sid.toUpperCase());
                params.put("oldCourseId", oldCid.toUpperCase());
                params.put("newCourseId", newCid.toUpperCase());
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
    @Override
    public void onClick(View v) {
        if(v==button)
        {
            update();
        }
    }
}
