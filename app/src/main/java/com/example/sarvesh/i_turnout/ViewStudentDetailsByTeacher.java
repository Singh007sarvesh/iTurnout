package com.example.sarvesh.i_turnout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class ViewStudentDetailsByTeacher extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private static String courseId="";
    List<ViewStudentDetailsRowItem> rowItem;
    ListView myListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student_details_by_teacher);
        Intent in=getIntent();
        //Toast.makeText(getApplicationContext(),in.getStringExtra("subjectid"),Toast.LENGTH_LONG).show();
        courseId=in.getStringExtra("courseId");
        rowItem = new ArrayList<ViewStudentDetailsRowItem>();

        myListView =  findViewById(R.id.studentdetailslist);
        loadListViewData();
    }
    public void loadListViewData()
    {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();

        StringRequest request=new StringRequest(Request.Method.POST,
                defConstant.URL_CHECKENROLLTEACHER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Toast.makeText(getApplicationContext(),userid,Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject=new JSONObject(response.toString());
                            JSONArray array=jsonObject.getJSONArray("flag");
                            //  JSONArray array1=jsonObject.getJSONArray("flagid");
                            //JSONObject o1= array.getJSONObject(0);
                            //Toast.makeText(getApplicationContext(),o1.getString("data1"),Toast.LENGTH_LONG).show();
                            for(int i=0;i<array.length();i++)
                            {
                                JSONObject o= array.getJSONObject(i);
                                //  Toast.makeText(getApplicationContext(),o.getString("data"),Toast.LENGTH_LONG).show();

                               ViewStudentDetailsRowItem item=new ViewStudentDetailsRowItem(
                                        o.getString("data"),
                                        o.getString("data1")
                                );
                                rowItem.add(item);
                            }
                            ViewStudentDetailsAdapter adapter=new ViewStudentDetailsAdapter(getApplicationContext(),rowItem);
                            myListView.setAdapter(adapter);
                            //Toast.makeText(getApplicationContext(),o1.getString("d"),Toast.LENGTH_LONG).show();
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
                params.put("courseid", courseId);
                return params;
            }

        };
        RequestHandler.getInstance(this).addToRequestQueue(request);

    }

}
