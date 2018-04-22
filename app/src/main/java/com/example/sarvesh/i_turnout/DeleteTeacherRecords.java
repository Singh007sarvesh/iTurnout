package com.example.sarvesh.i_turnout;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DeleteTeacherRecords extends AppCompatActivity {
    List<DeleteTeacherItem> rowItems;
    ListView mylistview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_teacher_records);
        rowItems = new ArrayList<>();

        /*for (int i = 0; i < 30; i++) {
            rowItems.add(new DeleteItemforStudent("Sarvesh Singh"));
        }*/

        mylistview = findViewById(R.id.delteacherlist1);
        loadListData();
        //DeleteAdapterforStudent adapter = new DeleteAdapterforStudent(getApplicationContext(), rowItems);
        // mylistview.setTextFilterEnabled(true);
        // mylistview.setAdapter(adapter);
    }
    public void loadListData()
    {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();

        StringRequest request=new StringRequest(Request.Method.GET,
                defConstant.URL_DELETETEACHER,
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
                                DeleteTeacherItem item=new DeleteTeacherItem(
                                        o.getString("teacherName"),
                                        o.getString("teacherId"),
                                        o.getString("date")
                                );
                                rowItems.add(item);
                            }
                            DeleteTeacherAdapter adapter=new DeleteTeacherAdapter(getApplicationContext(),rowItems);
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
                });
        RequestHandler.getInstance(this).addToRequestQueue(request);
    }
}
