package com.example.sarvesh.i_turnout;

/**
 * Created by sarvesh on 31/3/18.
 */

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

public class ViewNotification extends AppCompatActivity implements OnItemClickListener {

    //private String[] name;
   // private TypedArray setIcon;
    private List<ViewNotificationItem> NotificationRowItems;
    private ListView myListView;
    private SharedPrefManager sharedPrefManager;
    private static String userId="";

  //  private NotificationCompat.Builder notificationBuilder;
  //  private NotificationManager notificationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notification);
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        HashMap<String,String> userDetails = sharedPrefManager.getUserDetails();
        userId = userDetails.get(SharedPrefManager.KEY_Id);
        NotificationRowItems = new ArrayList<ViewNotificationItem>();
        myListView = findViewById(R.id.viewNotification);
        myListView.setOnItemClickListener(this);
        loadListViewData();


    }


/*

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void setNotificationData()
    {
        notificationBuilder=new NotificationCompat.Builder(this);
        notificationBuilder.setSmallIcon(R.drawable.ic_notifications_active_black_24dp);
        notificationBuilder.setContentTitle("MyNotification");
        notificationBuilder.setContentText("hello...");
        Intent intent=new Intent(this,ViewNotification.class);
        TaskStackBuilder stackBuilder=TaskStackBuilder.create(this);
        stackBuilder.addParentStack(ViewNotification.class);
        stackBuilder.addNextIntent(intent);
        PendingIntent pendingIntent=stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        notificationBuilder.setContentIntent(pendingIntent);
        notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notificationBuilder.build());


    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    */
    public  void loadListViewData()
    {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();

        StringRequest request=new StringRequest(Request.Method.POST,
                defConstant.URL_Notification,
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
                                ViewNotificationItem item=new ViewNotificationItem(
                                        o.getString("subjectName"),
                                        o.getString("nid"),
                                        o.getString("date"),
                                        R.drawable.ic_notifications_active_black_24dp
                                );
                                NotificationRowItems.add(item);
                            }
                            ViewNotificationCustomAdapter adapter=new ViewNotificationCustomAdapter(getApplicationContext(),NotificationRowItems);
                            myListView.setAdapter(adapter);

                            /*if(rowItems.size()>0)
                            {
                                setNotificationData();
                            }*/
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
                params.put("studentId", userId);
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
      Intent in=new Intent(ViewNotification.this,DispNotification.class);
      in.putExtra("nId",NotificationRowItems.get(position).getNid());
      startActivity(in);
    }


}