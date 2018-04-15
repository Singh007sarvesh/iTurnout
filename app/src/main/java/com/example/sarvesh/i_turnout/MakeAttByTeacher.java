package com.example.sarvesh.i_turnout;

import android.app.Activity;
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

import com.example.sarvesh.i_turnout.Teacher.SearchCustomAdapter;
import com.example.sarvesh.i_turnout.Teacher.SearchRowItem;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class MakeAttByTeacher extends AppCompatActivity {

    public static String courseid="";
    List<SearchRowItem> rowItems;
    ListView mylistview;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_att_by_teacher);
        Intent in=getIntent();
        courseid=in.getStringExtra("courseId");
        mylistview =  findViewById(R.id.msearchresult);

       // mylistview.setOnItemClickListener(this);
        Toast.makeText(getApplicationContext(),courseid,Toast.LENGTH_LONG).show();
        loadListView();

    }
    public void loadListView()
    {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();
        Toast.makeText(getApplicationContext(),"hey",Toast.LENGTH_LONG).show();

        StringRequest request=new StringRequest(Request.Method.POST,
                defConstant.URL_CHECKENROLLTEACHER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Toast.makeText(getApplicationContext(),userid,Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject;
                            jsonObject = new JSONObject(response);
                            JSONArray array=jsonObject.getJSONArray("flag");
                            for(int i=0;i<array.length();i++)
                            {
                                JSONObject o= array.getJSONObject(i);
                                SearchRowItem item=new SearchRowItem(
                                        o.getString("data"),
                                        o.getString("data1")
                                );
                                rowItems.add(item);
                            }
                            SearchCustomAdapter adapter=new SearchCustomAdapter(getApplicationContext(),rowItems);
                            mylistview.setAdapter(adapter);
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
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("courseid", courseid);
                return params;
            }

        };
        RequestHandler.getInstance(this).addToRequestQueue(request);
    }


    //here adding search menu

}
