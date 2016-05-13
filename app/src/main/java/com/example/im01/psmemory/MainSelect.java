package com.example.im01.psmemory;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainSelect extends AppCompatActivity {
    Button ps,memory;
    Activity main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_select);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Service();
        main=this;
        ps=(Button)findViewById(R.id.button6);
        memory=(Button)findViewById(R.id.button13);
        ps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(MainSelect.this,MainActivity.class);
               // i.putExtra("id",1);
                startActivity(i);
                main.finish();
            }
        });

        memory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x=new Intent();
                x.setClass(MainSelect.this,MainActivity.class);
                x.putExtra("id",1);
                startActivity(x);
                main.finish();
            }
        });

    }
    public void Service(){
        Intent i=new Intent(this,Pm_Service.class);
        startService(i);
    }

}
