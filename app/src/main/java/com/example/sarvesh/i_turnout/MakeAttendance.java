package com.example.sarvesh.i_turnout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;

public class MakeAttendance extends AppCompatActivity {

    String[] name;
    List<AttendanceItemRow> rowItems;
    ListView mylistview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_attendance);
        rowItems = new ArrayList< AttendanceItemRow >();
        // rowItems.add(new ViewNotificationItem("Kunj Nivas Flat-2, Indira Nagar, Lucknow", R.drawable.ic_notifications_active_black_24dp));
        for (int i = 0; i < 30; i++) {
            rowItems.add(new AttendanceItemRow("M150054CA"));
        }

        mylistview =  findViewById(R.id.mattendance);
        AttendanceCustomAdapter adapter = new AttendanceCustomAdapter(this, rowItems);
        mylistview.setAdapter(adapter);

    }
}

