package com.example.sarvesh.i_turnout;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DeleteUser extends AppCompatActivity {


    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);

        progressDialog = new ProgressDialog(this);

        Button mShowDialog =(Button) findViewById(R.id.delallrecord);

        mShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder=new AlertDialog.Builder(DeleteUser.this);
                mBuilder.setTitle(R.string.dialog_title);
                mBuilder.setMessage(R.string.dialog_msg);
                mBuilder.setPositiveButton(R.string.bconfirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        delete();
                    }
                });
                mBuilder.setNegativeButton(R.string.bback, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alterdialog=mBuilder.create();
                alterdialog.show();

            }
        });

    }
    public void deleteUser()
    {
       // final String did = delrecords.getText().toString().trim();
        progressDialog.setMessage(getString(R.string.deleting_msg));
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                defConstant.URL_DELETERECORDS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.hide();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();

                        }catch(JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }){
         /*  @Override
           protected Map<String, String> getParams() throws AuthFailureError {
              Map<String, String> params = new HashMap<>();
                params.put("did", did);
               // params.put("dname", dname);
               // params.put("dpassword", dpassword);
               return params;
            }*/
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
    public void delete(){

            deleteUser();

    }
    public void back(View v)
    {
        Intent in=new Intent(DeleteUser.this,Admin.class);
        in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(in);
        finish();
    }
}
