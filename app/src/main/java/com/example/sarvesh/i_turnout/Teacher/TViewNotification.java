package com.example.sarvesh.i_turnout.Teacher;

/**
 * Created by sarvesh on 31/3/18.
 */

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.sarvesh.i_turnout.R;

import java.util.ArrayList;
import java.util.List;

public class TViewNotification extends AppCompatActivity implements OnItemClickListener {

    String[] name;
    TypedArray seticon;

    List<TViewNotificationItem> rowItems;
    ListView mylistview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notification);

        rowItems = new ArrayList< TViewNotificationItem >();
        // rowItems.add(new ViewNotificationItem("Kunj Nivas Flat-2, Indira Nagar, Lucknow", R.drawable.ic_notifications_active_black_24dp));
        for (int i = 0; i < 15; i++) {
            rowItems.add(new TViewNotificationItem("m150056ca",R.drawable.ic_notifications_active_black_24dp));
        }

        mylistview = (ListView) findViewById(R.id.viewnotification);
        TViewNotificationCusAdapter adapter = new TViewNotificationCusAdapter(this, rowItems);
        mylistview.setAdapter(adapter);

        mylistview.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

       /* String subjectname = rowItems.get(position).getSubjectname();
        Toast.makeText(getApplicationContext(), "" + subjectname,
                Toast.LENGTH_SHORT).show();*/
        Intent in=new Intent(TViewNotification.this,TDispNotification.class);
        startActivity(in);

    }

}