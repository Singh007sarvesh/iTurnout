package com.example.sarvesh.i_turnout;

/**
 * Created by sarvesh on 31/3/18.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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

public class CheckAttendance extends AppCompatActivity implements OnItemClickListener {
    private ProgressDialog progressDialog;
    SharedPrefManager sharedPrefManager;
    private static String userId="";
    List<CheckAttRowItem> rowItem;
    ListView mylistview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_attendance);
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        HashMap<String,String> userDetails = sharedPrefManager.getUserDetails();
        userId = userDetails.get(SharedPrefManager.KEY_Id);
        rowItem = new ArrayList<CheckAttRowItem>();

       /* for (int i = 0; i < 15; i++) {
            rowItem.add(new CheckAttRowItem("Database Management System"));
        }*/

        mylistview =  findViewById(R.id.list1);
        loadListViewData();
    }
    public void loadListViewData()
    {

        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();

        StringRequest request=new StringRequest(Request.Method.POST, defConstant.URl_CHECKATTBYSTUDENT,
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
                                CheckAttRowItem item=new CheckAttRowItem(
                                        o.getString("subjectName"),
                                       o.getString("subjectId")
                                );
                                rowItem.add(item);
                            }
                            SubjectCheckAdapter adapter=new SubjectCheckAdapter(getApplicationContext(),rowItem);
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
                params.put("userid", userId);
                return params;
            }

        };
        RequestHandler.getInstance(this).addToRequestQueue(request);

        mylistview.setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

       /* String subjectcheck = rowItem.get(position).getSubject();
        Toast.makeText(getApplicationContext(), "" + subjectcheck,
                Toast.LENGTH_SHORT).show();*/

       Intent in = new Intent(CheckAttendance.this,AttendanceInPerticuSubject.class);
       in.putExtra("subjectId",rowItem.get(position).getSubjectId()) ;
       startActivity(in);
    }

}