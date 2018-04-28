package com.example.sarvesh.i_turnout;

import android.app.ProgressDialog;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeleteTeacherRecords extends AppCompatActivity implements View.OnClickListener{
    private List<DeleteTeacherItem> rowItems = new ArrayList<>();;
    private ListView myListView;
    private DeleteTeacherAdapter adapter;
    private FloatingActionButton floatingActionButton;
    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_teacher_records);
       // rowItems = new ArrayList<>();

     //   for (int i = 0; i < 30; i++) {
        //    rowItems.add(new DeleteTeacherItem("Ajay Yadav","t123456ca","2016"));
      //  }

        myListView = findViewById(R.id.delteacherlist1);
        floatingActionButton=findViewById(R.id.delteacherrecords);
      //  adapter = new DeleteTeacherAdapter(getApplicationContext(),rowItems);
       // mylistview.setAdapter(adapter);
        loadListData();
        //DeleteAdapterforStudent adapter = new DeleteAdapterforStudent(getApplicationContext(), rowItems);
        // mylistview.setTextFilterEnabled(true);
        // mylistview.setAdapter(adapter);
        floatingActionButton.setOnClickListener(this);
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
                                        o.getString("teacherName").toUpperCase(),
                                        o.getString("teacherId").toUpperCase(),
                                        o.getString("date")
                                );

                              rowItems.add(item);
                            }
                            adapter=new DeleteTeacherAdapter(getApplicationContext(),rowItems);
                            myListView.setAdapter(adapter);
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items, menu);

        final MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
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
                final  List<DeleteTeacherItem> filtermodelist=filter(rowItems,newText);
                adapter.setfilter(filtermodelist);
                return true;
            }
        });

        return true;
    }
    private List<DeleteTeacherItem> filter(List<DeleteTeacherItem> pl,String query)
    {
        query=query.toUpperCase();
        final List<DeleteTeacherItem> filteredModeList=new ArrayList<>();
        for (DeleteTeacherItem model:pl)
        {
             String text=model.getTeacherName().toUpperCase();
            if (text.startsWith(query))
            {
                filteredModeList.add(model);
            }
        }
        return filteredModeList;
    }

   /* @Override
    public boolean onQueryTextSubmit(String query) {
        if(!searchView.isIconified())
        {
            searchView.setIconified(true);
        }

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {



        newText = newText.toString().toLowerCase();

        //Log.d("Query", newText);


        final List<DeleteTeacherItem> searchRows = new ArrayList<>();

        for (DeleteTeacherItem deleteTeacherItem : rowItems) {
            final String name = deleteTeacherItem.getTeacherName().toLowerCase();
            if (name.startsWith(newText)) {
                searchRows.add(deleteTeacherItem);
                // Toast.makeText(getApplicationContext(),name,Toast.LENGTH_LONG).show();
            }
        }
        adapter.setFilter(searchRows);
        return true;
    }*/

    public void deleteData()
    {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("wait...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                defConstant.URL_DELSTUDENTRECORD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(),"ajay"+String.valueOf(rowItems.get(1).getTeacherId()),Toast.LENGTH_LONG).show();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                           // Intent i = new Intent(getApplicationContext(), AssignedCourses.class);
                           // startActivity(i);
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
               /* params.put("tid", tid);
                params.put("tname", tname);
                params.put("password", password);*/
                // for (int i=0;i<rowItems.size();i++) {
                // params.put("subjectId", subjectId);
                params.put("teacherId",String.valueOf(rowItems.get(1)));
                //  }
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View v) {
        if(v==floatingActionButton)
            deleteData();
    }
}
