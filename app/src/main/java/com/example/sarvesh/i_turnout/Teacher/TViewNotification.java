package com.example.sarvesh.i_turnout.Teacher;

/**
 * Created by sarvesh on 31/3/18.
 */

import android.app.Activity;
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
import com.example.sarvesh.i_turnout.CustomAdapter;
import com.example.sarvesh.i_turnout.R;
import com.example.sarvesh.i_turnout.RequestHandler;
import com.example.sarvesh.i_turnout.RowItem;
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

    String[] name;
    TypedArray seticon;
    private SharedPrefManager sharedPrefManager;
    private static String userId="";
    private List<TViewNotificationItem> rowItems;
    private ListView mylistview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notification);
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        HashMap<String,String> userDetails = sharedPrefManager.getUserDetails();
        userId = userDetails.get(SharedPrefManager.KEY_Id);

        rowItems = new ArrayList< TViewNotificationItem >();
        // rowItems.add(new ViewNotificationItem("Kunj Nivas Flat-2, Indira Nagar, Lucknow", R.drawable.ic_notifications_active_black_24dp));
       // for (int i = 0; i < 15; i++) {
         //   rowItems.add(new TViewNotificationItem("m150056ca",R.drawable.ic_message_black_24dp));
      //  }

        mylistview = (ListView) findViewById(R.id.viewnotification);
        TViewNotificationCusAdapter adapter = new TViewNotificationCusAdapter(this, rowItems);
        mylistview.setAdapter(adapter);

        mylistview.setOnItemClickListener(this);
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
                                       o.getString("studentName"),
                                        o.getString("studentId"),
                                        R.drawable.ic_message_black_24dp,
                                        o.getString("date"),
                                        o.getString("id"),
                                        o.getString("cid")

                                );
                                rowItems.add(item);
                            }
                            TViewNotificationCusAdapter adapter=new TViewNotificationCusAdapter(getApplicationContext(),rowItems);
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
        in.putExtra("cid",rowItems.get(position).getCid());
        in.putExtra("studentId",rowItems.get(position).getId());
        startActivity(in);

    }

}