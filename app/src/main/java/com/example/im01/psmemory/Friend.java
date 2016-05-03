package com.example.im01.psmemory;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Friend extends AppCompatActivity {
    Button accept,cancel;
    ListView friendlist;
    private String[] list={"王大錘","老媽","老姐","Tyson","Jack","Neymar"};
    private ArrayAdapter<String> listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        accept=(Button)findViewById(R.id.button11);
        cancel=(Button)findViewById(R.id.button10);
        friendlist=(ListView)findViewById(R.id.listView);
        listAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
        friendlist.setAdapter(listAdapter);
        friendlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(Friend.this,MainActivity.class);
                startActivity(i);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(Friend.this,MainActivity.class);
                startActivity(i);
            }
        });
    }

}
