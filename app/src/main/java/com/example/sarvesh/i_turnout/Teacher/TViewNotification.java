package com.example.sarvesh.i_turnout.Teacher;

/**
 * Created by sarvesh on 31/3/18.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.TypedArray;
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
import com.example.sarvesh.i_turnout.R;
import com.example.sarvesh.i_turnout.RequestHandler;
import com.example.sarvesh.i_turnout.SharedPrefManager;
import com.example.sarvesh.i_turnout.defConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TViewNotification extends AppCompatActivity implements OnItemClickListener {

    private String[] name;
    private TypedArray setIcon;
    private SharedPrefManager sharedPrefManager;
    private static String userId="";
    private List<TViewNotificationItem> rowItems;
    private ListView myListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notification);
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        HashMap<String,String> userDetails = sharedPrefManager.getUserDetails();
        userId = userDetails.get(SharedPrefManager.KEY_Id);

        rowItems = new ArrayList< TViewNotificationItem >();
        myListView = (ListView) findViewById(R.id.viewNotification);
        TViewNotificationCusAdapter adapter = new TViewNotificationCusAdapter(this, rowItems);
        myListView.setAdapter(adapter);

        myListView.setOnItemClickListener(this);
        loadData();

    }

    public void loadData()
    {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();

        StringRequest request=new StringRequest(Request.Method.POST, defConstant.URL_MSG,
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
                                TViewNotificationItem item=new TViewNotificationItem(
                                       o.getString("studentName").substring(0).toUpperCase(),
                                        o.getString("studentId").substring(0).toUpperCase(),
                                        R.drawable.message,
                                        o.getString("date"),
                                        o.getString("id")
                                );
                                rowItems.add(item);
                            }
                            TViewNotificationCusAdapter adapter=new TViewNotificationCusAdapter(getApplicationContext(),rowItems);
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
                params.put("teacherId", userId);
                return params;
            }

        };
        RequestHandler.getInstance(this).addToRequestQueue(request);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

        Intent in=new Intent(TViewNotification.this,TDispNotification.class);
        in.putExtra("contentId",rowItems.get(position).getNId());
        in.putExtra("studentId",rowItems.get(position).getId());
        startActivity(in);

    }

}