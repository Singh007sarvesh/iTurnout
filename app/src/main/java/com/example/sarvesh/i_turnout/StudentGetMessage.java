package com.example.sarvesh.i_turnout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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

public class StudentGetMessage extends AppCompatActivity implements View.OnClickListener {

    private static String contentId="";
    private TextView textView;
    private EditText editText;
    private static String cid="";
    private static String teacherId="";
    private SharedPrefManager sharedPrefManager;
    private static String userId="";
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_get_message);
        Intent in=getIntent();
        contentId=in.getStringExtra("contentId");
        teacherId=in.getStringExtra("teacherId");
        Toast.makeText(getApplicationContext(),teacherId,Toast.LENGTH_SHORT).show();
        editText=findViewById(R.id.studentSendMessage);
        textView=findViewById(R.id.studentGetMessage);
        floatingActionButton=findViewById(R.id.studentFloatingButton1);
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        HashMap<String,String> userDetails = sharedPrefManager.getUserDetails();
        userId = userDetails.get(SharedPrefManager.KEY_Id);

        loadData();
        if(userId.equalsIgnoreCase(teacherId))
        {
            floatingActionButton.setVisibility(View.GONE);
            //  floatingActionButton.setEnabled(false);
        }
        else
            floatingActionButton.setOnClickListener(this);
        sendData();
    }
    public void loadData()
    {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();
        StringRequest request=new StringRequest(Request.Method.POST, defConstant.URL_FMSG,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            textView.setText(jsonObject.getString("content"));
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("contentId", contentId);
                return params;
            }

        };
        RequestHandler.getInstance(this).addToRequestQueue(request);
    }
    public void sendData()
    {

        StringRequest stringRequest=new StringRequest(Request.Method.POST,
                defConstant.URL_Query,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            String Response=jsonObject.getString("message");
                            Toast.makeText(StudentGetMessage.this,Response,Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(),MessageForStudent.class);
                            startActivity(i);
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
               // params.put("content",qContent.getText().toString().trim());
             //   params.put("cid",cid);
                params.put("studentId",userId);
                params.put("teacherId",teacherId);
                params.put("content",editText.getText().toString().trim());
                return params;
            }
        };
        MySingleton.getmInstance(StudentGetMessage.this).addToRequestQue(stringRequest);
    }
    @Override
    public void onClick(View v) {
        if(v==floatingActionButton)
        {
            sendData();
        }
    }
}
