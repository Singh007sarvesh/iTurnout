package com.example.sarvesh.i_turnout;

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
import com.example.sarvesh.i_turnout.Moderator.DeleteUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeleteTeacherRecords extends AppCompatActivity implements View.OnClickListener{
    private List<DeleteTeacherItem> rowItems = new ArrayList<>();
    private ListView myListView;
    private DeleteTeacherAdapter adapter;
    private FloatingActionButton floatingActionButton;
    private SearchView searchView;
    private List<String> deleteTeacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_teacher_records);
       // rowItems = new ArrayList<>();

     //   for (int i = 0; i < 30; i++) {
        //    rowItems.add(new DeleteTeacherItem("Ajay Yadav","t123456ca","2016"));
      //  }

        myListView = findViewById(R.id.delTeacherList1);
        floatingActionButton=findViewById(R.id.delTeacherRecords2);
      //  adapter = new DeleteTeacherAdapter(getApplicationContext(),rowItems);
       // mylistview.setAdapter(adapter);
        deleteTeacher = new ArrayList<>();
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
                            adapter=new DeleteTeacherAdapter(getApplicationContext(),rowItems,deleteTeacher);
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
                        progressDialog.dismiss();
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
            if (text.contains(query))
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
                defConstant.URL_delTeacherRecord,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.hide();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), DeleteUser.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                    Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                    Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
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
                params.put("size",String.valueOf(deleteTeacher.size()));
                for (int i=0;i<deleteTeacher.size();i++) {
                    params.put("userId"+i,deleteTeacher.get(i).toString());
                }
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View v) {
        if(v==floatingActionButton) {
            //for (int i=0;i<deleteTeacher.size();i++) {
            //        Toast.makeText(getApplicationContext(),deleteTeacher.get(i).toString(),Toast.LENGTH_SHORT).show();
            //    }
            //   Toast.makeText(getApplicationContext(),"hey",Toast.LENGTH_SHORT).show();
            deleteData();
        }
    }
}
