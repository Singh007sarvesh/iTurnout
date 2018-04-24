package com.example.sarvesh.i_turnout;

import android.app.ProgressDialog;
import android.content.Intent;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MakeAttendance extends AppCompatActivity implements TextWatcher {
    ListView listview;
    List<AttendanceItemRow> rowItems;
    AttendanceCustomAdapter adapter;
    private static String subjectId;
    private EditText etSearchField;
    private TextView tvTitle;
    private ImageButton bBack, bSearch;
    private boolean isSearching = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_attendance);
        Intent in=getIntent();
        subjectId=in.getStringExtra("subjectId");
        //Toast.makeText(getApplicationContext(),subjectId,Toast.LENGTH_LONG).show();
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
        // rowItems.add(new ViewNotificationItem("Kunj Nivas Flat-2, Indira Nagar, Lucknow", R.drawable.ic_notifications_active_black_24dp));
       /* for (int i = 0; i < 30; i++) {
            rowItems.add(new AttendanceItemRow("M150054CA"));
        }*/

        listview = findViewById(R.id.mattendance);
        adapter = new AttendanceCustomAdapter(getApplicationContext(), rowItems);
        // mylistview.setTextFilterEnabled(true);
        listview.setAdapter(adapter);

        etSearchField.addTextChangedListener(this);
       loadData();
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
                        // Toast.makeText(getApplicationContext(),userid,Toast.LENGTH_LONG).show();
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
                            AttendanceCustomAdapter adapter=new AttendanceCustomAdapter(getApplicationContext(),rowItems);
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
}

