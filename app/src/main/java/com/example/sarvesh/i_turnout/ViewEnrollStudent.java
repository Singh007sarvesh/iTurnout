package com.example.sarvesh.i_turnout;

/**
 * Created by sarvesh on 31/3/18.
 */

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewEnrollStudent extends Activity implements OnItemClickListener {

    String[] studentid;


    List<ViewEnrollRowItem> rowItems;
    ListView mylistview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_enroll_student);

        rowItems = new ArrayList<ViewEnrollRowItem>();

        for (int i = 0; i < 15; i++) {
            rowItems.add(new ViewEnrollRowItem("M150054CA"));
        }

        mylistview = (ListView) findViewById(R.id.list4);
        ViewEnrollCustomAdapter adapter = new ViewEnrollCustomAdapter(this, rowItems);
        mylistview.setAdapter(adapter);

        mylistview.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

        String studentid = rowItems.get(position).getStudentId();
        Toast.makeText(getApplicationContext(), "" + studentid,
                Toast.LENGTH_SHORT).show();
    }

}