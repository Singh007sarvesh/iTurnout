package com.example.sarvesh.i_turnout;

/**
 * Created by sarvesh on 31/3/18.
 */

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewNotification extends Activity implements OnItemClickListener {

    String[] name;
    TypedArray seticon;

    List<ViewNotificationItem> rowItems;
    ListView mylistview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notification);

        rowItems = new ArrayList<ViewNotificationItem>();
       // rowItems.add(new ViewNotificationItem("Kunj Nivas Flat-2, Indira Nagar, Lucknow", R.drawable.ic_notifications_active_black_24dp));
        for (int i = 0; i < 15; i++) {
           rowItems.add(new ViewNotificationItem("m150054ca",R.drawable.ic_notifications_active_black_24dp));
       }

        mylistview = (ListView) findViewById(R.id.viewnotification);
        ViewNotificationCustomAdapter adapter = new ViewNotificationCustomAdapter(this, rowItems);
        mylistview.setAdapter(adapter);

        mylistview.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

       /* String subjectname = rowItems.get(position).getSubjectname();
        Toast.makeText(getApplicationContext(), "" + subjectname,
                Toast.LENGTH_SHORT).show();*/
      //  Intent in=new Intent(ViewNotification.this,MakeAttByTeacher.class);
       // startActivity(in);
    }

}