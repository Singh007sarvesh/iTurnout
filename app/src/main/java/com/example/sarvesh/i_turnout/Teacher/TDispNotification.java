package com.example.sarvesh.i_turnout.Teacher;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.sarvesh.i_turnout.R;
import com.example.sarvesh.i_turnout.RequestHandler;
import com.example.sarvesh.i_turnout.TeacherQuery;
import com.example.sarvesh.i_turnout.defConstant;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TDispNotification extends AppCompatActivity implements View.OnClickListener{

    private static String contentId="";
    private ImageView imageView;
    private TextView textView;
    private static String cid="";
    private static String studentId="";
    private FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tdisp_notification);
        Intent in=getIntent();
        contentId=in.getStringExtra("contentId");
        studentId=in.getStringExtra("studentId");
      // Toast.makeText(getApplicationContext(),cid,Toast.LENGTH_SHORT).show();
        textView=findViewById(R.id.DNotification);
        floatingActionButton=findViewById(R.id.TDispQuery);
        imageView=findViewById(R.id.imageDisp);

        loadData();
        floatingActionButton.setOnClickListener(this);
    }
    public void loadData()
    {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();

        StringRequest request=new StringRequest(Request.Method.POST, defConstant.URL_FMSG,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Toast.makeText(getApplicationContext(),userid,Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();

                        try {
                           // JSONObject jsonObject=new JSONObject(response.toString());
                            //JSONArray array=jsonObject.getJSONArray("flag");
                            JSONObject jsonObject = new JSONObject(response);
                            textView.setText(jsonObject.getString("content"));
                            Picasso.with(getApplicationContext()).load(jsonObject.getString("image")).into(imageView);

                           // Toast.makeText(getApplicationContext(), jsonObject.getString("content"), Toast.LENGTH_LONG).show();

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
                params.put("contentId", contentId);
                return params;
            }

        };
        RequestHandler.getInstance(this).addToRequestQueue(request);
    }

    public void sendDate(){
        Intent in=new Intent(TDispNotification.this,TeacherQuery.class);
       // in.putExtra("contentId",rowIt);
        in.putExtra("studentId",studentId);
        startActivity(in);

    }

    @Override
    public void onClick(View v) {
        if(v==floatingActionButton)
            sendDate();

    }
}
