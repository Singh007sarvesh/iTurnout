package com.example.sarvesh.i_turnout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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

public class QueryStudentDetails extends AppCompatActivity implements AdapterView.OnItemClickListener{


    private SharedPrefManager sharedPrefManager;
    private static String userId="";
    private List<QueryStudentItem> rowItems;
    private ListView myListView;
    private QueryStudentAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_student_details);

        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        HashMap<String,String> userDetails = sharedPrefManager.getUserDetails();
        userId = userDetails.get(SharedPrefManager.KEY_Id);
        rowItems = new ArrayList<QueryStudentItem>();
        myListView=findViewById(R.id.queryStudentDetails);
        loadData();
        myListView.setOnItemClickListener(this);
    }
    public void loadData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST
                , defConstant.URL_QueryDetailsStudent,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());
                            JSONArray array = jsonObject.getJSONArray("flag");
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject o = array.getJSONObject(i);
                                QueryStudentItem item = new QueryStudentItem(
                                        o.getString("data"),
                                        o.getString("data1")
                                );
                                rowItems.add(item);
                            }
                            adapter = new QueryStudentAdapter(getApplicationContext(), rowItems);
                            myListView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("userId", userId);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

        @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
          //  String subjectName = rowItems.get(position).getStudentName();
          //  Toast.makeText(getApplicationContext(), "" + subjectName,
               //     Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(QueryStudentDetails.this,TeacherQuery.class);
            intent.putExtra("studentId",rowItems.get(position).getStudentName());
            startActivity(intent);

    }
}
