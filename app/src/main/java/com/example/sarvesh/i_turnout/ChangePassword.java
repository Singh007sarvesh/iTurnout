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

    SharedPrefManager sharedPrefManager;
    private static String userid="";
    private EditText editTextold, editTextnew,editTextconfirm;

    private Button changesubmit;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        HashMap<String,String> userDetails = sharedPrefManager.getUserDetails();
        userid = userDetails.get(SharedPrefManager.KEY_Id);
        editTextold = findViewById(R.id.changepassword);
        editTextnew = findViewById(R.id.changepasswordnew);
        editTextconfirm = findViewById(R.id.changepasswordconfirm);
        changesubmit = findViewById(R.id.changepasswordsubmit);

        progressDialog = new ProgressDialog(this);

        changesubmit.setOnClickListener(this);
       // Toast.makeText(getApplicationContext(),userid,Toast.LENGTH_LONG).show();


    }

    public void registerUser(){

        final String changeOld = editTextold.getText().toString().trim();
        final String changeNew = editTextnew.getText().toString().trim();
        final String changeConfirm = editTextconfirm.getText().toString().trim();

        if (changeOld.length()>4 && changeNew.length()>4 && changeConfirm.length()>4 ) {
            progressDialog.setMessage("Changing...");
            progressDialog.show();

            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    defConstant.URL_CHANGE,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.hide();
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                                Intent i = new Intent(getApplicationContext(), ChangePassword.class);
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
                    params.put("userid", userid);
                    params.put("changeold", changeOld);
                    params.put("changenew", changeNew);
                    params.put("changeconfirm", changeConfirm);
                    return params;
                }
            };
            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "plz fill  in a proper way", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onClick(View view){
        if(view == changesubmit){
            registerUser();
        }
    }


}