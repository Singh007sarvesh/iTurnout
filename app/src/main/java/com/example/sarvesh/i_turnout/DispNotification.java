package com.example.sarvesh.i_turnout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DispNotification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disp_notification);
    }
    public void queryNotification(View v)
    {
        Intent in=new Intent(DispNotification.this,Query.class);

        startActivity(in);
        finish();
    }
}
