package com.example.sarvesh.i_turnout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MakeAttByTeacher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_att_by_teacher);
        Intent in=getIntent();
        Toast.makeText(getApplicationContext(),in.getStringExtra("subjectid"),Toast.LENGTH_LONG).show();
    }
}
