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

public class DeleteStudentRecord extends AppCompatActivity implements View.OnClickListener {

    private List<DeleteStudentItem> rowItems = new ArrayList<>();
    private ListView myListView;
    private DeleteStudentAdapter adapter;
    private FloatingActionButton floatingActionButton;
    private SearchView searchView;
    private List<String> deleteStudent;
   // private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_student_record);

        myListView=findViewById(R.id.deleteStudentList5);
        floatingActionButton=findViewById(R.id.deleteStudentRecord5);
        deleteStudent=new ArrayList<>();
        loadDataStudent();
        floatingActionButton.setOnClickListener(this);
    }
    public void loadDataStudent()
    {
       final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        StringRequest stringRequest=new StringRequest(Request.Method.GET, defConstant.URL_DeleteStudent,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try
                        {
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray jsonArray=jsonObject.getJSONArray("flag");
                            for(int x=0;x<jsonArray.length();x++)
                            {
                                JSONObject jsonObject1=jsonArray.getJSONObject(x);
                                DeleteStudentItem deleteStudentItem=new DeleteStudentItem(
                                        jsonObject1.getString("studentName"),
                                        jsonObject1.getString("studentId"),
                                        jsonObject1.getString("sDate")
                                );
                                rowItems.add(deleteStudentItem);
                            }
                            adapter=new DeleteStudentAdapter(getApplicationContext(),rowItems,deleteStudent);
                            myListView.setAdapter(adapter);
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item1, menu);
        final MenuItem myActionMenuItem = menu.findItem(R.id.action_search1);
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
                final  List<DeleteStudentItem> filterModeList=filter(rowItems,newText);
                adapter.setfilter(filterModeList);
                return true;
            }
        });

        return true;
    }
    private List<DeleteStudentItem> filter(List<DeleteStudentItem> pl,String query)
    {
        query=query.toUpperCase();
        final List<DeleteStudentItem> filteredModeList=new ArrayList<>();
        for (DeleteStudentItem model:pl)
        {
            String text=model.getStudentName().toUpperCase();
            if (text.contains(query))
            {
                filteredModeList.add(model);
            }
        }
        return filteredModeList;
    }
    public void deleteData()
    {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("wait...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                defConstant.URL_delStudentRecord,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.hide();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), DeleteUser.class);
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
                params.put("size",String.valueOf(deleteStudent.size()));
                for (int i=0;i<deleteStudent.size();i++) {
                    params.put("studentId"+i,deleteStudent.get(i).toString());
                }
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View v) {
        if(v==floatingActionButton)
        {
            deleteData();
           // for(int i=0;i<deleteStudent.size();i++){
           // Toast.makeText(getApplicationContext(), deleteStudent.get(i).toString(), Toast.LENGTH_SHORT).show();
           // }
        }
    }
}
