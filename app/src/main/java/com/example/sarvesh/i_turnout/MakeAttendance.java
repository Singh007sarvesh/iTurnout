package com.example.sarvesh.i_turnout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.sarvesh.i_turnout.Moderator.Admin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MakeAttendance extends AppCompatActivity implements TextWatcher,View.OnClickListener{
    ListView listview;
    List<AttendanceItemRow> rowItems;
    AttendanceCustomAdapter adapter;
    List<String> absent,present;
    private static String subjectId;
    private EditText etSearchField;
    private TextView tvTitle;
    private ImageButton bBack, bSearch;
    private boolean isSearching = false;
    private FloatingActionButton floatingActionButton;
    private CheckBox checkBox;
    static String presence="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_attendance);
        Intent in=getIntent();
        subjectId=in.getStringExtra("subjectId");
        initViews();
        bBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSearching) {
                    etSearchField.setVisibility(View.GONE);
                    tvTitle.setVisibility(View.VISIBLE);
                } else {
                    tvTitle.setVisibility(View.GONE);
                    etSearchField.setVisibility(View.VISIBLE);
                }
                isSearching = !isSearching;
            }
        });
        rowItems = new ArrayList<>();
        listview = findViewById(R.id.mattendance);
        floatingActionButton=findViewById(R.id.attendance2);
        checkBox=findViewById(R.id.check1);
        absent = new ArrayList<>();
        present = new ArrayList<>();
        adapter = new AttendanceCustomAdapter(getApplicationContext(), rowItems , absent);
        listview.setAdapter(adapter);
        etSearchField.addTextChangedListener(this);
        loadData();
        floatingActionButton.setOnClickListener(this);



    }
    private void initViews() {
        tvTitle = findViewById(R.id.title_text);
        etSearchField = findViewById(R.id.search_field);
        bBack = findViewById(R.id.back_button);
        bSearch = findViewById(R.id.search_button);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if(!TextUtils.isEmpty(s)) {
            String newText = s.toString().toLowerCase();
            //Log.d("Query", newText);

            try {
                ArrayList<AttendanceItemRow> searchRows = new ArrayList<>();
                for (AttendanceItemRow attendanceItemRow : rowItems) {
                    String name = attendanceItemRow.getRollNumber().toLowerCase();
                    if (name.contains(newText))
                        searchRows.add(attendanceItemRow);
                }
                adapter.setFilter(searchRows);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            adapter.setFilter(rowItems);
        }
    }
    public void loadData()
    {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();

        StringRequest request=new StringRequest(Request.Method.POST,
                defConstant.URL_ATTENDANCE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject=new JSONObject(response.toString());
                            JSONArray array=jsonObject.getJSONArray("flag");
                            for(int i=0;i<array.length();i++)
                            {
                                JSONObject o= array.getJSONObject(i);
                                AttendanceItemRow item=new AttendanceItemRow(
                                        o.getString("studentName"),
                                        o.getString("studentId")

                                );
                                rowItems.add(item);
                            }
                            AttendanceCustomAdapter adapter=new AttendanceCustomAdapter(getApplicationContext(),rowItems, absent);
                            listview.setAdapter(adapter);
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
                }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("subjectId",subjectId);
                    return params;
                }
        };
        RequestHandler.getInstance(this).addToRequestQueue(request);
    }
    public void sendData() {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("wait...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                defConstant.URL_SEND,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.hide();
                       // Toast.makeText(getApplicationContext(),String.valueOf(rowItems.get(0)),Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getApplicationContext(), AssignedCourses.class);
                            startActivity(i);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
              //  params.put("presence",presence);
                params.put("size",String.valueOf(rowItems.size()));
                params.put("subjectId", subjectId);
                params.put("abSize",String.valueOf(absent.size()));
               for (int i=0;i<rowItems.size();i++) {
                   params.put("studentId"+i,String.valueOf(rowItems.get(i).getRollNumber()));
              }
                for (int i=0;i<absent.size();i++) {
                   params.put("stuAbsent"+i,absent.get(i).toString());
              }
              return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View v) {
        if(v== floatingActionButton){
            sendData();

        }
    }
}

