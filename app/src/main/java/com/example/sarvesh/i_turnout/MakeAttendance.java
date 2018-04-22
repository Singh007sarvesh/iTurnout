package com.example.sarvesh.i_turnout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;
import java.util.ArrayList;
import java.util.List;
public class MakeAttendance extends AppCompatActivity {
    private ListView listview;
    private SearchView sv;
    private List<AttendanceItemRow> rowItems;
    AttendanceCustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_attendance);
        sv =  findViewById(R.id.search5);
        rowItems = new ArrayList<>();
        // rowItems.add(new ViewNotificationItem("Kunj Nivas Flat-2, Indira Nagar, Lucknow", R.drawable.ic_notifications_active_black_24dp));
        for (int i = 0; i < 30; i++) {
            rowItems.add(new AttendanceItemRow("M150054CA"));
        }

        listview =  findViewById(R.id.mattendance);
         adapter = new AttendanceCustomAdapter(getApplicationContext(), rowItems);
       // mylistview.setTextFilterEnabled(true);
        listview.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        MenuItem item=menu.findItem(R.id.search5);
        sv=(SearchView) item.getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}

