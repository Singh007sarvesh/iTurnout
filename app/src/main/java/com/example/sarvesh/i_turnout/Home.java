package com.example.sarvesh.i_turnout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.sarvesh.i_turnout.Moderator.Admin;
import com.example.sarvesh.i_turnout.Teacher.Teacher;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Home extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextUserid, editTextPassword;
    private Button signinButton;
    private ProgressDialog progressDialog;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
      //  getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        HashMap<String,String> userDetails = sharedPrefManager.getUserDetails();
        if (sharedPrefManager.isLoggedIn()) {
            String last_login = userDetails.get(SharedPrefManager.KEY_Login);
            //finish();
            if(last_login.equals("student"))
                startActivity(new Intent(this, Student.class));
            else if(last_login.equals("teacher"))
                startActivity(new Intent(this, Teacher.class));
            else if(last_login.equals("admin"))
                startActivity(new Intent(this, Admin.class));

            finish();
        }
        editTextUserid = findViewById(R.id.userId);
        editTextPassword = findViewById(R.id.password);
        signinButton =  findViewById(R.id.userLoginButton);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        signinButton.setOnClickListener(this);
    }
    public void signIn()
    {
        //Intent in;
        EditText user=findViewById(R.id.userId);
        EditText pass=findViewById(R.id.password);
        String user1=user.getText().toString();
        String pass1=pass.getText().toString();
        if(user1.length()>8)
        {
            if(pass1.length()<5)
            {
                Toast.makeText(getApplicationContext(),"Plz fill proper Password....",Toast.LENGTH_SHORT).show();
                return;
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Plz fill proper id....",Toast.LENGTH_SHORT).show();
            return;
        }
       /* else if(s.charAt(0)=='a'){
            Intent in = new Intent(Home.this, Admin.class);
            startActivity(in);
            finish();
        }
        else if (s.charAt(0)=='t' && (s.length()>1 && s.length()<10)){
          Intent  in = new Intent(Home.this, Teacher.class);
            startActivity(in);
            finish();
        }
        else if((s.charAt(0)=='B' || s.charAt(0)=='b' || s.charAt(0)=='M' || s.charAt(0)=='m') && (s.length()>1 && s.length()<10) ){
          Intent  in = new Intent(Home.this, Student.class);
            startActivity(in);
            finish();
        }
        else
        {

            Toast.makeText(getApplicationContext(),"Plz fill proper id...",Toast.LENGTH_LONG).show();
        }*/

        final String userid = editTextUserid.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                defConstant.URL_USERLOGIN,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        progressDialog.dismiss();
                        try{
                            JSONObject obj = new JSONObject(response);
                            if(!obj.getBoolean("error")){

                                String id = obj.getString("userid");
                                String name = obj.getString("name");
                                if((userid.charAt(0)=='a' ||  userid.charAt(0)=='A'))
                                {
                                    sharedPrefManager.createLoginSession(name,id,"admin");
                                    Intent in=new Intent(Home.this,Admin.class);
                                    in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(in);
                                    finish();
                                }
                                else if((userid.charAt(0)=='t' ||  userid.charAt(0)=='T'))
                                {
                                    sharedPrefManager.createLoginSession(name,id,"teacher");
                                    Intent in=new Intent(Home.this,Teacher.class);
                                    in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(in);
                                    finish();
                                }
                                else
                                {
                                    sharedPrefManager.createLoginSession(name,id,"student");
                                    Intent in=new Intent(Home.this,Student.class);
                                    in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(in);
                                    finish();
                                }
                            }else{
                                Toast.makeText(
                                        getApplicationContext(),
                                        obj.getString("message"),
                                        Toast.LENGTH_LONG
                                ).show();
                            }

                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        progressDialog.dismiss();
                        Toast.makeText(
                                getApplicationContext(),
                                error.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map <String, String> params = new HashMap<>();
                params.put("userid",userid.toUpperCase());
                params.put("password",password.toUpperCase());
                return params;
            }

        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);


    }
    @Override
    public void onClick(View view){
        if(view == signinButton){
            signIn();
        }
    }



}
