package com.example.sarvesh.i_turnout;

/**
 * Created by sarvesh on 31/3/18.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
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

public class AssignedCourses extends Activity implements OnItemClickListener {


    SharedPrefManager sharedPrefManager;
    private static String userid="";

    List<RowItem> rowItems;
    ListView mylistview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assigned_courses);
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        HashMap<String,String> userDetails = sharedPrefManager.getUserDetails();
        userid = userDetails.get(SharedPrefManager.KEY_Id);
        rowItems = new ArrayList<RowItem>();

     /*   for (int i = 0; i < 5; i++) {
            rowItems.add(new RowItem("Database Management System"));
        }*/

        mylistview = (ListView) findViewById(R.id.list);
      //  CustomAdapter adapter = new CustomAdapter(this, rowItems);
      //  mylistview.setAdapter(adapter);
        loadListViewData();

        mylistview.setOnItemClickListener(this);

    }
    public  void loadListViewData()
    {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();

        StringRequest request=new StringRequest(Request.Method.POST, defConstant.URL_ASSIGNEDCOURSE,
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
                                RowItem item=new RowItem(
                                        o.getString("data"),
                                        o.getString("data1")
                                );
                                rowItems.add(item);
                            }
                            CustomAdapter adapter=new CustomAdapter(getApplicationContext(),rowItems);
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
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("userid", userid);
                return params;
            }

        };
        RequestHandler.getInstance(this).addToRequestQueue(request);

    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

       /* String subjectname = rowItems.get(position).getSubjectname();
        Toast.makeText(getApplicationContext(), "" + subjectname,
                Toast.LENGTH_SHORT).show();*/
    //  Intent in=new Intent(AssignedCourses.this,MakeAttByTeacher.class);
       // in.putExtra("subjectid",rowItems.get(position).getSubjectid()) ;
     //  startActivity(in);
    }

}