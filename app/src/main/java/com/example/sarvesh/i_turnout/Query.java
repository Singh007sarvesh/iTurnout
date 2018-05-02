package com.example.sarvesh.i_turnout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
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
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Query extends AppCompatActivity implements View.OnClickListener{
    private EditText qContent;
    private final int IMG_REQUEST=999;
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
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"select image"),IMG_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==IMG_REQUEST && resultCode==RESULT_OK && data!=null && data.getData()!=null)
        {
           Uri  path=data.getData();
            Cursor filePath=
                    getContentResolver().query(path,null,null,null,null);
            int nameIndex=filePath.getColumnIndex(OpenableColumns.DISPLAY_NAME);
            int sizeIndex=filePath.getColumnIndex(OpenableColumns.SIZE);
            filePath.moveToFirst();
            textView.setText(filePath.getString(nameIndex));
            textSize.setText(Long.toString(filePath.getLong(sizeIndex)));
            try
            {
                InputStream inputStream=getContentResolver().openInputStream(path);
                bitmap= BitmapFactory.decodeStream(inputStream);

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    private void uploadImage()
    {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Wait...");
        progressDialog.show();
        StringRequest stringRequest=new StringRequest(Request.Method.POST,
                defConstant.URL_Query,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            String Response= jsonObject.getString("message");
                            Toast.makeText(getApplicationContext(),Response,Toast.LENGTH_SHORT).show();
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
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("teacherId",teacherId);
                params.put("content",qContent.getText().toString().trim());
                params.put("studentId",userId);
                params.put("name",textView.getText().toString().trim());
                String imageData=imageToString(bitmap);
                params.put("image",imageData);
                params.put("status",status);
                return params;
            }
        };
        MySingleton.getmInstance(Query.this).addToRequestQue(stringRequest);
    }

    private String imageToString(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] imgBytes = byteArrayOutputStream.toByteArray();
            String encodedImage= Base64.encodeToString(imgBytes, Base64.DEFAULT);
            return encodedImage;

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