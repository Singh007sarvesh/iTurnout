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

        if ((did.length() == 9 ) && (did.charAt(0) == 'M' || did.charAt(0) == 'm' || did.charAt(0)=='b' ||  did.charAt(0)=='B') && did.matches("[a-zA-Z0-9]+"))
        {
            if(dname.matches("[a-zA-Z ]+")  && dname.length()>4 )
            {
                if(dpassword.length()<5)
                {
                    Toast.makeText(getApplicationContext(), "Password length should be greater then 4", Toast.LENGTH_LONG).show();
                    return;
                }
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Plz fill user name in a proper way", Toast.LENGTH_LONG).show();
                return;
            }
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
                    params.put("did", did);
                    params.put("dname", dname);
                    params.put("dpassword", dpassword);
                    return params;
                }
            };
            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Plz fill id in a proper way", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onClick(View view){
        if(view == dsubmit){
            registerUser();
        }
    }

}