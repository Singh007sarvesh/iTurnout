package com.example.sarvesh.i_turnout.Teacher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.sarvesh.i_turnout.Query;
import com.example.sarvesh.i_turnout.R;

public class TDispNotification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tdisp_notification);
    }
    public void queryNotification(View v)
    {
        Intent in=new Intent(TDispNotification.this,Query.class);
        startActivity(in);
        finish();
    }

}
