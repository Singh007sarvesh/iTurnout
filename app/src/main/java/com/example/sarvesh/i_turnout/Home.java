package com.example.sarvesh.i_turnout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Home extends AppCompatActivity {
    TextView tv;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tv= (TextView) findViewById(R.id.textview);
        tv.setSelected(true);

    }
    /*public void teacher(View v)
    {
        Intent in=new Intent(Home.this, Student.class);
        startActivity(in);
    }*/
    public void signin(View v)
    {
        Intent in;
        EditText e=(EditText) findViewById(R.id.editText);
        String s=e.getText().toString();
        if(s.length()<1)
        {
            in = new Intent(Home.this, Home.class);
        }
        else if(s.charAt(0)=='a'){
            in = new Intent(Home.this, Admin.class);
        }
        else if (s.charAt(0)=='t'){
            in = new Intent(Home.this, Teacher.class);
        }
        else if(s.charAt(0)=='B' || s.charAt(0)=='b' || s.charAt(0)=='M' || s.charAt(0)=='m'){
            in = new Intent(Home.this, Student.class);
        }
        else
        {
            in = new Intent(Home.this, Home.class);
        }
        startActivity(in);
    }
}
