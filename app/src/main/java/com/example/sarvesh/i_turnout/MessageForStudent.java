package com.example.sarvesh.i_turnout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
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

public class MessageForStudent extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private String[] name;
    private TypedArray setIcon;
    private SharedPrefManager sharedPrefManager;
    private static String userId="";
    private List<StudentMessageItem> rowItems;
    private ListView myListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_for_student);
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        HashMap<String,String> userDetails = sharedPrefManager.getUserDetails();
        userId = userDetails.get(SharedPrefManager.KEY_Id);
        rowItems = new ArrayList< StudentMessageItem>();
        myListView =  findViewById(R.id.messageStudent);
        StudentMessageAdapter adapter = new StudentMessageAdapter(this, rowItems);
        myListView.setAdapter(adapter);
        myListView.setOnItemClickListener(this);
        loadData();
    }

    public void loadData()
    {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();

        StringRequest request=new StringRequest(Request.Method.POST, defConstant.URL_StudentMessage,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Toast.makeText(getApplicationContext(),userid,Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject=new JSONObject(response.toString());
                            JSONArray array=jsonObject.getJSONArray("flag");
                            for(int i=0;i<array.length();i++)
                            {
                                JSONObject o= array.getJSONObject(i);
                                StudentMessageItem item=new StudentMessageItem(
                                        o.getString("teacherName").substring(0).toUpperCase(),
                                        o.getString("teacherId").substring(0).toUpperCase(),
                                        o.getString("date"),
                                        R.drawable.message,
                                        o.getString("id"),
                                        o.getString("cid")
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
        in.putExtra("cid",rowItems.get(position).getCid());
        in.putExtra("teacherId",rowItems.get(position).getUserId());
        startActivity(in);

    }
}
