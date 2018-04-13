package com.example.sarvesh.i_turnout;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
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

public class CheckEnrollStudentInParticulerSubject extends Activity {

    private ProgressDialog progressDialog;
    SharedPrefManager sharedPrefManager;
    private static String courseid="";
    List<CheckEnStudInPerSubRowItem> rowItem;
    ListView mylistview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_enroll_student_in_particuler_subject);
        Intent in=getIntent();
        //Toast.makeText(getApplicationContext(),in.getStringExtra("subjectid"),Toast.LENGTH_LONG).show();
        courseid=in.getStringExtra("subjectid");

       /* sharedPrefManager = new SharedPrefManager(getApplicationContext());
        HashMap<String,String> userDetails = sharedPrefManager.getUserDetails();
        userid = userDetails.get(SharedPrefManager.KEY_Id);*/
        rowItem = new ArrayList<CheckEnStudInPerSubRowItem>();

        mylistview = (ListView) findViewById(R.id.tenrollid);
        loadListViewData();
    }
    public void loadListViewData()
    {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();

        StringRequest request=new StringRequest(Request.Method.POST, defConstant.URL_VESINPERBYTEACHER,
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

                                CheckEnStudInPerSubRowItem item=new  CheckEnStudInPerSubRowItem(
                                       o.getString("data")
                                        // o.getString("id")
                                );
                                rowItem.add(item);
                            }
                            CheckEnStudInPerSubCustomAdapter adapter=new CheckEnStudInPerSubCustomAdapter(getApplicationContext(),rowItem);
                            mylistview.setAdapter(adapter);
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
                params.put("courseid", courseid);
                return params;
            }

        };
        RequestHandler.getInstance(this).addToRequestQueue(request);

    }
}
