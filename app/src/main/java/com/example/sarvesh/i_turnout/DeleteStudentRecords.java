package com.example.sarvesh.i_turnout;

import android.app.ProgressDialog;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
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

public class DeleteStudentRecords extends AppCompatActivity implements SearchView.OnQueryTextListener {


    List<DeleteItemforStudent> rowItems;
    ListView mylistview;
    DeleteStudentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_recordof_student);
        rowItems = new ArrayList<>();

       /* for (int i = 0; i < 30; i++) {
            rowItems.add(new DeleteItemforStudent("Sarvesh Singh","m150054ca","2015"));
        }*/

        mylistview = findViewById(R.id.delstudentlist);
        adapter = new DeleteStudentAdapter(getApplicationContext(), rowItems);
        mylistview.setAdapter(adapter);
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
                defConstant.URL_DELETESTUDENT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                 //        Toast.makeText(getApplicationContext(),"hey",Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject=new JSONObject(response.toString());
                            JSONArray array=jsonObject.getJSONArray("flag");
                            for(int i=0;i<array.length();i++)
                            {
                                JSONObject o= array.getJSONObject(i);
                               DeleteItemforStudent item=new DeleteItemforStudent(
                                        o.getString("studentName"),
                                        o.getString("studentId"),
                                        o.getString("date")
                                );
                                rowItems.add(item);
                            }
                            DeleteStudentAdapter adapter=new DeleteStudentAdapter(getApplicationContext(),rowItems);
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
      //  Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
     //   Toast.makeText(getApplicationContext(),"hey111",Toast.LENGTH_LONG).show();

        getMenuInflater().inflate(R.menu.menu_items,menu);
        MenuItem menuItem=menu.findItem(R.id.action_search);
        SearchView searchView=(SearchView) MenuItemCompat.getActionView(menuItem);
     //   Toast.makeText(getApplicationContext(),"hey22",Toast.LENGTH_LONG).show();

        searchView.setOnQueryTextListener(this);
     //   Toast.makeText(getApplicationContext(),"hey333",Toast.LENGTH_LONG).show();

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {



             newText = newText.toString().toLowerCase();

            //Log.d("Query", newText);


                ArrayList<DeleteItemforStudent> searchRows = new ArrayList<>();

               for (DeleteItemforStudent deleteItemforStudent : rowItems) {
                    String name = deleteItemforStudent.getStudentName().toLowerCase();
                    if (name.contains(newText)){
                        searchRows.add(deleteItemforStudent);
                       // Toast.makeText(getApplicationContext(),name,Toast.LENGTH_LONG).show();
                    }
                }
             adapter.setFilter(searchRows);
        return true;
    }
}
