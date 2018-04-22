package com.example.sarvesh.i_turnout;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
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
import com.example.sarvesh.i_turnout.Moderator.Admin;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Query extends AppCompatActivity implements View.OnClickListener{
    private EditText qContent;
    private final int IMG_REQUEST=1;
    private Button qButton;
    private ImageButton attach;
    private Bitmap bitmap;
    private TextView textView,textSize;
    public static String teacherId="";
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        Intent in=getIntent();
        teacherId=in.getStringExtra("teacherId");
      //  Toast.makeText(getApplicationContext(),teacherId,Toast.LENGTH_LONG).show();
        progressDialog = new ProgressDialog(this);
        qContent =  findViewById(R.id.Qcontent);
        attach =  findViewById(R.id.attach);
        qButton =  findViewById(R.id.Qbutton);
        textView=findViewById(R.id.disptext);
        textSize=findViewById(R.id.dispsize);
        attach.setOnClickListener(this);
        qButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.attach:
                selectImage();
                break;

            case R.id.Qbutton:
                uploadImage();
                break;
        }
    }
    private void selectImage()
    {
        Intent intent=new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMG_REQUEST);
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
                params.put("content",qContent.getText().toString().trim());
                //params.put("name",textView.getText().toString().trim());
               //params.put("image",imageToString(bitmap));
                return params;
            }
        };
        MySingleton.getmInstance(Query.this).addToRequestQue(stringRequest);
    }
    private String imageToString(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgBytes=byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes,Base64.DEFAULT);
    }
}