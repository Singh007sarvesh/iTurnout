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

public class ViewEnrollStudent extends Activity implements OnItemClickListener {

    private ProgressDialog progressDialog;
    SharedPrefManager sharedPrefManager;
    private static String userid="";

    List<ViewEnrollRowItem> rowItems;
    ListView mylistview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_enroll_student);
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        HashMap<String,String> userDetails = sharedPrefManager.getUserDetails();
        userid = userDetails.get(SharedPrefManager.KEY_Id);
        rowItems = new ArrayList<ViewEnrollRowItem>();

      /*  for (int i = 0; i < 15; i++) {
            rowItems.add(new ViewEnrollRowItem("Operating System"));
        }*/

        mylistview =  findViewById(R.id.list4);
        //ViewEnrollCustomAdapter adapter = new ViewEnrollCustomAdapter(this, rowItems);
     //   mylistview.setAdapter(adapter);
        loadListViewData();
      //  mylistview.setOnItemClickListener(this);

    }
    public void  loadListViewData()
    {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();

        StringRequest request=new StringRequest(Request.Method.POST, defConstant.URl_VIEWENROLLSTUDENTBYTEACHER,
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
                                ViewEnrollRowItem item=new ViewEnrollRowItem(
                                        o.getString("data"),
                                        o.getString("data1")
                                        // o.getString("id")
                                );
                                rowItems.add(item);
                            }
                            ViewEnrollCustomAdapter adapter=new ViewEnrollCustomAdapter(getApplicationContext(),rowItems);
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
                params.put("userid", userid);
                return params;
            }

        };
        RequestHandler.getInstance(this).addToRequestQueue(request);

        mylistview.setOnItemClickListener(this);

    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

        /*String studentid = rowItems.get(position).getStudentId();
        Toast.makeText(getApplicationContext(), "" + studentid,
                Toast.LENGTH_SHORT).show();*/
        Intent in=new Intent(ViewEnrollStudent.this,CheckEnrollStudentInParticulerSubject.class);
        in.putExtra("subjectid",rowItems.get(position).getSubjectid()) ;

        startActivity(in);
    }

}