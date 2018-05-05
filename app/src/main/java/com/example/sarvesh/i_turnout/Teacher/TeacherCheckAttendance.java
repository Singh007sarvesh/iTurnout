package com.example.sarvesh.i_turnout.Teacher;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.example.sarvesh.i_turnout.AttendanceTeacher;
import com.example.sarvesh.i_turnout.R;
import com.example.sarvesh.i_turnout.RequestHandler;
import com.example.sarvesh.i_turnout.defConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeacherCheckAttendance extends AppCompatActivity implements AdapterView.OnItemClickListener{


    private static String courseId="";
    private TeacherCheckAdapter adapter;
    private List<TeacherCheckItem> rowItems;
    private ListView myListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_check_attendance);

        Intent intent=getIntent();
        courseId=intent.getStringExtra("courseId");
        rowItems = new ArrayList<TeacherCheckItem>();
        myListView = findViewById(R.id.checkAttendance1);
       /* for (int i=0;i<20;i++)
        {
            rowItems.add(new TeacherCheckItem("sar","m150054ca" ));
        }
        adapter=new TeacherCheckAdapter(getApplicationContext(),rowItems);
        myListView.setAdapter(adapter);*/
        myListView.setOnItemClickListener(this);
        loadData();
    }
    public void loadData()
    {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("loading...");
        progressDialog.show();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, defConstant.URL_CHECKENROLLTEACHER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("flag");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                TeacherCheckItem teacherCheckItem = new TeacherCheckItem(
                                        object.getString("data"),
                                        object.getString("data1"),
                                        object.getString("total")
                                );
                                rowItems.add(teacherCheckItem);
                            }
                            adapter = new TeacherCheckAdapter(getApplicationContext(), rowItems);
                            myListView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("courseId", courseId);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(TeacherCheckAttendance.this, AttendanceTeacher.class);
        intent.putExtra("courseId",courseId);
        intent.putExtra("studentId",rowItems.get(position).getStudentId());
        startActivity(intent);
       // Toast.makeText(getApplicationContext(),rowItems.get(position).getStudentId(),Toast.LENGTH_SHORT).show();
    }
}
