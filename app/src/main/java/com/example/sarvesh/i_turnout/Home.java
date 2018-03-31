package com.example.sarvesh.i_turnout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

public class Home extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
      //  getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


    }
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
