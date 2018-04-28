package com.example.sarvesh.i_turnout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Query extends AppCompatActivity implements View.OnClickListener{
    private EditText qContent;
    private final int IMG_REQUEST=1024;
    private Button qButton;
    private ImageButton attach;
    private Bitmap bitmap;
    private TextView textView,textSize;
    private static String teacherId="";
    private SharedPrefManager sharedPrefManager;
    private static String userId="";
    private static String courseId="";
    private static String status="0";
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        Intent in=getIntent();
        teacherId=in.getStringExtra("teacherId");
        progressDialog = new ProgressDialog(this);
        qContent =  findViewById(R.id.Qcontent);
        attach =  findViewById(R.id.attach);
        qButton =  findViewById(R.id.Qbutton);
        textView=findViewById(R.id.disptext);
        textSize=findViewById(R.id.dispsize);
        attach.setOnClickListener(this);
        qButton.setOnClickListener(this);
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        HashMap<String,String> userDetails = sharedPrefManager.getUserDetails();
        userId = userDetails.get(SharedPrefManager.KEY_Id);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.attach:
                selectImage();
                status="1";
                break;

            case R.id.Qbutton:
                uploadImage();
                break;
        }
    }
    private void selectImage()
    {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"select JPEG"),IMG_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==IMG_REQUEST && resultCode==RESULT_OK && data!=null)
        {
            Uri path=data.getData();
            Cursor filePath=
                    getContentResolver().query(path,null,null,null,null);
            int nameIndex=filePath.getColumnIndex(OpenableColumns.DISPLAY_NAME);
            int sizeIndex=filePath.getColumnIndex(OpenableColumns.SIZE);
            filePath.moveToFirst();
            textView.setText(filePath.getString(nameIndex));
            textSize.setText(Long.toString(filePath.getLong(sizeIndex)));
            try
            {


                bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),path);

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    private void uploadImage()
    {
        progressDialog.setMessage("Wait...");
        progressDialog.show();
      //  Toast.makeText(getApplicationContext(),"hey",Toast.LENGTH_LONG).show();
        StringRequest stringRequest=new StringRequest(Request.Method.POST,
                defConstant.URL_Query,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.hide();
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            String Response=jsonObject.getString("message");
                            Toast.makeText(Query.this,Response,Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getApplicationContext(), TeacherDetail.class);
                            startActivity(i);
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();

                //params.put("name",textView.getText().toString().trim());

                params.put("teacherId",teacherId);
                params.put("content",qContent.getText().toString().trim());
                params.put("studentId",userId);
             //   params.put("image",imageToString(bitmap));
                if(status.equals("1"))
                {
                    params.put("name",textView.getText().toString().trim());
                    params.put("image",imageToString(bitmap));
                    params.put("status",status);
                }
                else
                    status="0";
                return params;
            }
        };
        MySingleton.getmInstance(Query.this).addToRequestQue(stringRequest);
    }
    private String imageToString(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
       // bitmap.compress(Bitmap.CompressFormat.JPEG,30,byteArrayOutputStream);
        byte[] imgBytes=byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes,Base64.DEFAULT);
    }
   /* public void sendImage()
    {
        StringRequest stringRequest=new StringRequest(Request.Method.POST,
                defConstant.URL_Query,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.hide();
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            String Response=jsonObject.getString("message");
                            Toast.makeText(Query.this,Response,Toast.LENGTH_LONG).show();
                           // Intent i = new Intent(getApplicationContext(), TeacherDetail.class);
                           // startActivity(i);
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
               // params.put("content",qContent.getText().toString().trim());
                //params.put("name",textView.getText().toString().trim());
              //  params.put("studentId",userId);
              //  params.put("teacherId",teacherId);
                String status="1";
                params.put("name",textView.getText().toString().trim());
                params.put("image",imageToString(bitmap));
                params.put("status",status);
                return params;
            }
        };
        MySingleton.getmInstance(Query.this).addToRequestQue(stringRequest);
    }*/
}