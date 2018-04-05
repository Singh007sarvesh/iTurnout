package com.example.sarvesh.i_turnout;

/**
 * Created by sarvesh on 31/3/18.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CheckAttendance extends Activity implements OnItemClickListener {

    String[] subjectcheck;


    List<CheckAttRowItem> rowItem;
    ListView mylistview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_attendance);

        rowItem = new ArrayList<CheckAttRowItem>();

        for (int i = 0; i < 15; i++) {
            rowItem.add(new CheckAttRowItem("Database Management System"));
        }

        mylistview = (ListView) findViewById(R.id.list1);
        SubjectCheckAdapter adapter = new SubjectCheckAdapter(this,rowItem);
        mylistview.setAdapter(adapter);

        mylistview.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

       /* String subjectcheck = rowItem.get(position).getSubject();
        Toast.makeText(getApplicationContext(), "" + subjectcheck,
                Toast.LENGTH_SHORT).show();*/
       Intent in = new Intent(CheckAttendance.this,AttendanceInPerticuSubject.class);
        startActivity(in);
    }

}