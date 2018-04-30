package com.example.sarvesh.i_turnout;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DeleteStudentRecord extends AppCompatActivity {

    private List<DeleteStudentItem> rowItems = new ArrayList<>();
    private ListView myListView;
    private DeleteStudentAdapter adapter;
    private FloatingActionButton floatingActionButton;
    private SearchView searchView;
    private List<String> deleteStudent;
   // private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_student_record);

        myListView=findViewById(R.id.delStudentList5);
      //  floatingActionButton=findViewById(R.id.delStudentRecords5);
        deleteStudent=new ArrayList<>();
        loadDataStudent();
       // floatingActionButton.setOnClickListener(this);
    }
    public void loadDataStudent()
    {
       final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        StringRequest stringRequest=new StringRequest(Request.Method.GET, defConstant.URL_DeleteStudent,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try
                        {
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray jsonArray=jsonObject.getJSONArray("flag");
                            for(int x=0;x<jsonArray.length();x++)
                            {
                                JSONObject jsonObject1=jsonArray.getJSONObject(x);
                                DeleteStudentItem deleteStudentItem=new DeleteStudentItem(
                                        jsonObject1.getString("studentName"),
                                        jsonObject1.getString("studentId"),
                                        jsonObject1.getString("sDate")
                                );
                                rowItems.add(deleteStudentItem);
                            }
                            adapter=new DeleteStudentAdapter(getApplicationContext(),rowItems,deleteStudent);
                            myListView.setAdapter(adapter);
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
/*
    @Override
    public void onClick(View v) {
        if(v==floatingActionButton)
        {
           Toast.makeText(getApplicationContext(),"hello..",Toast.LENGTH_SHORT).show();
        }
    }*/
}
