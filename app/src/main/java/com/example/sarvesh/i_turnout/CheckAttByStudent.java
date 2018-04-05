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

public class CheckAttByStudent extends Activity implements OnItemClickListener {

    String[] subjectname;


    List<CheckAttByStudentRowItem> rowItems;
    ListView mylistview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_att_by_student);

        rowItems = new ArrayList<CheckAttByStudentRowItem>();

        for (int i = 0; i < 15; i++) {
            rowItems.add(new CheckAttByStudentRowItem("Software Engineering"));
        }

        mylistview = (ListView) findViewById(R.id.list6);
       CheckAttStudentCustomAdapter adapter = new CheckAttStudentCustomAdapter(this, rowItems);
        mylistview.setAdapter(adapter);

        mylistview.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

        String subjectname = rowItems.get(position).getSSubjectname();
        Toast.makeText(getApplicationContext(), "" + subjectname,
                Toast.LENGTH_SHORT).show();
    }

}