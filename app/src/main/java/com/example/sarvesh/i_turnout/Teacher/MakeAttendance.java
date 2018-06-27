package com.example.sarvesh.i_turnout.Teacher;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.sarvesh.i_turnout.AttendanceItemRow;
import com.example.sarvesh.i_turnout.R;
import com.example.sarvesh.i_turnout.RequestHandler;
import com.example.sarvesh.i_turnout.defConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MakeAttendance extends AppCompatActivity implements View.OnClickListener {
    private ListView listview;
    private List<AttendanceItemRow> rowItems;
    private AttendanceCustomAdapter adapter;
    private List<String> absent;
    private static String subjectId;
    private FloatingActionButton floatingActionButton;
    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_attendance);
        Intent in=getIntent();
        subjectId=in.getStringExtra("subjectId");
        rowItems = new ArrayList<>();
        listview = findViewById(R.id.mattendance);
        floatingActionButton=findViewById(R.id.attendance2);

        absent = new ArrayList<>();
       // Toast.makeText(getApplicationContext(),absent.,Toast.LENGTH_SHORT).show();
        loadData();
        floatingActionButton.setOnClickListener(this);
    }
  /* public void submitData()
    {
        String studentId=rowItems.get(0).getRollNumber();
        String courseId=subjectId;
        saveData(studentId,courseId);

    }
    public void saveData(String studentId,String courseId)
    {
        DbHelper dbHelper=new DbHelper(this);
        SQLiteDatabase database= dbHelper.getWritableDatabase();
        if(checkNetworkConnection())
        {
            sendData();
        }
        else
        {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
            String date1=String.valueOf(date);
            String  presence="1";
            //dbHelper.saveToLocalDatabase(studentId,courseId,dateFormat.format(date),presence,DbContract.SYNC_STATUS_FAILED,database);
            saveToLocalStorage(studentId,courseId,date1,presence,DbContract.SYNC_STATUS_FAILED);
        }
    }
    public boolean checkNetworkConnection()
    {
        ConnectivityManager connectivityManager=(ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        return (networkInfo!=null && networkInfo.isConnected());
    }
    private void saveToLocalStorage(String studentId,String courseId,String date, String presence,int sync)
    {
        DbHelper dbHelper=new DbHelper(this);
        SQLiteDatabase database= dbHelper.getWritableDatabase();
        dbHelper.saveToLocalDatabase(studentId,courseId,date,presence,sync,database);
        dbHelper.close();
    }*/
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
                           adapter=new AttendanceCustomAdapter(getApplicationContext(),rowItems, absent);
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
                        progressDialog.dismiss();
                       // Toast.makeText(getApplicationContext(),String.valueOf(rowItems.get(0)),Toast.LENGTH_SHORT).show();
                       try {
                            //Log.("tagconvertstr", "["+response+"]");
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), AssignedCourses.class);
                           i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                   Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                   Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(i);
                           finish();

                       } catch (JSONException e) {
                            e.printStackTrace();
                       }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
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
//            Toast.makeText(getApplicationContext(),absent.size(),Toast.LENGTH_SHORT).show();
           sendData();
          // submitData();
           /* for (int i=0;i<rowItems.size();i++) {
                Toast.makeText(getApplicationContext(),String.valueOf(rowItems.get(i).getRollNumber()),Toast.LENGTH_SHORT).show();
            }*/
           // for (int i=0;i<absent.size();i++) {
          //     Toast.makeText(getApplicationContext(),absent.get(i).toString(),Toast.LENGTH_SHORT).show();
           // }
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item2, menu);
        final MenuItem myActionMenuItem = menu.findItem(R.id.action_search2);
        searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                myActionMenuItem.collapseActionView();
                return false;

            }


            @Override
            public boolean onQueryTextChange(String newText) {
                final  List<AttendanceItemRow> filterModeList=filter(rowItems,newText);
                adapter.setFilter(filterModeList);
                return true;
            }
        });

        return true;
    }
    private List<AttendanceItemRow> filter(List<AttendanceItemRow> pl,String query)
    {
        query=query.toUpperCase();
        final List<AttendanceItemRow> filteredModeList=new ArrayList<>();
        for (AttendanceItemRow model:pl)
        {
            String text=model.getStudentName().toUpperCase();
            if (text.contains(query))
            {
                filteredModeList.add(model);
            }
        }
        return filteredModeList;
    }


}

