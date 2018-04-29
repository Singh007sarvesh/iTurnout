package com.example.sarvesh.i_turnout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageForStudent extends AppCompatActivity implements AdapterView.OnItemClickListener,View.OnClickListener {

    private String[] name;
    private TypedArray setIcon;
    private SharedPrefManager sharedPrefManager;
    private static String userId="";
    private List<StudentMessageItem> rowItems;
    private ListView myListView;
    private String str="";
    private String id2="";
    private FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_for_student);
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        HashMap<String,String> userDetails = sharedPrefManager.getUserDetails();
        userId = userDetails.get(SharedPrefManager.KEY_Id);
        rowItems = new ArrayList< StudentMessageItem>();
        myListView =  findViewById(R.id.messageStudent);

        loadData();
        floatingActionButton=findViewById(R.id.sendFromStudent);
        floatingActionButton.setOnClickListener(this);
        myListView.setOnItemClickListener(this);
    }

    public void loadData()
    {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();

        StringRequest request=new StringRequest(Request.Method.POST,
                defConstant.URL_StudentMessage,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray array=jsonObject.getJSONArray("flag");
                            for(int i=0;i<array.length();i++)
                            {

                                JSONObject o= array.getJSONObject(i);
                                if(userId.equalsIgnoreCase(o.getString("senderId")))
                                {
                                    str="Me";
                                    id2=o.getString("senderId").toUpperCase();
                                }
                                else {
                                    str=o.getString("senderName").toUpperCase();
                                    id2=o.getString("senderId").toUpperCase();
                                }
                                StudentMessageItem item=new StudentMessageItem(

                                       str,
                                        id2,
                                        R.drawable.message,
                                        o.getString("date"),
                                        o.getString("id")
                                );
                                rowItems.add(item);
                            }
                            StudentMessageAdapter adapter=new StudentMessageAdapter(getApplicationContext(),rowItems);
                            myListView.setAdapter(adapter);
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
                params.put("studentId", userId);
                return params;
            }

        };
        RequestHandler.getInstance(this).addToRequestQueue(request);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent in=new Intent(MessageForStudent.this,StudentGetMessage.class);
       in.putExtra("contentId",rowItems.get(position).getqId());
       in.putExtra("teacherId",rowItems.get(position).getUserId());
        startActivity(in);

    }

    @Override
    public void onClick(View v) {
        if(v==floatingActionButton)
        {
            Intent intent=new Intent(MessageForStudent.this,TeacherDetail.class);
            startActivity(intent);
        }
    }
}
