package com.example.sarvesh.i_turnout;

/**
 * Created by sarvesh on 31/3/18.
 */

import android.app.Activity;
import android.app.LauncherActivity;
import android.app.ProgressDialog;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckAttByStudent extends Activity  {

   // private static final  String URL_DATA="https://i-turmout.000webhostapp.com/ViewSubjectByStudent.php";

    private ProgressDialog progressDialog;
    SharedPrefManager sharedPrefManager;

   private List<CheckAttByStudentRowItem> rowItems;
    ListView mylistview;
    private static String userid="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_att_by_student);

        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        HashMap<String,String> userDetails = sharedPrefManager.getUserDetails();
        userid = userDetails.get(SharedPrefManager.KEY_Id);

        mylistview = findViewById(R.id.list6);

        rowItems = new ArrayList<CheckAttByStudentRowItem>();
        /*for (int i = 0; i < 15; i++) {
            rowItems.add(new CheckAttByStudentRowItem("Software Engineering"));
        }*/


      /* CheckAttStudentCustomAdapter adapter = new CheckAttStudentCustomAdapter(this, rowItems);
        mylistview.setAdapter(adapter);

        mylistview.setOnItemClickListener(this);*/
      loadListViewData();

    }

  /*  @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

        String subjectname = rowItems.get(position).getSSubjectname();
        Toast.makeText(getApplicationContext(), "" + subjectname,
                Toast.LENGTH_SHORT).show();
    }*/
    public void loadListViewData()
    {

        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();

        StringRequest request=new StringRequest(Request.Method.POST, defConstant.URL_VIEWSUBJECTBYSTUDENT,
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
                                CheckAttByStudentRowItem item=new CheckAttByStudentRowItem(
                                        o.getString("data")
                                );
                                rowItems.add(item);
                            }
                            CheckAttStudentCustomAdapter adapter=new CheckAttStudentCustomAdapter(getApplicationContext(),rowItems);
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
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("userid", userid);
                return params;
            }

        };
        RequestHandler.getInstance(this).addToRequestQueue(request);

    }
}