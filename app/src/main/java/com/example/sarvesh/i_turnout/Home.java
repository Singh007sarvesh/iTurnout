package com.example.sarvesh.i_turnout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

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
        //Intent in;
        EditText e=(EditText) findViewById(R.id.editText);
        String s=e.getText().toString();
        if(s.length()<1)
        {
            Toast.makeText(getApplicationContext(),"Plz fill proper id....",Toast.LENGTH_LONG).show();
        }
        else if(s.charAt(0)=='a'){
            Intent in = new Intent(Home.this, Admin.class);
            startActivity(in);
            finish();
        }
        else if (s.charAt(0)=='t' && (s.length()>1 && s.length()<10)){
          Intent  in = new Intent(Home.this, Teacher.class);
            startActivity(in);
            finish();
        }
        else if((s.charAt(0)=='B' || s.charAt(0)=='b' || s.charAt(0)=='M' || s.charAt(0)=='m') && (s.length()>1 && s.length()<10) ){
          Intent  in = new Intent(Home.this, Student.class);
            startActivity(in);
            finish();
        }
        else
        {

            Toast.makeText(getApplicationContext(),"Plz fill proper id...",Toast.LENGTH_LONG).show();
        }

    }

}
