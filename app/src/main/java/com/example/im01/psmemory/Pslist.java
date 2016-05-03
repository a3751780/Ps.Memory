package com.example.im01.psmemory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class Pslist extends AppCompatActivity {

    Firebase mfirebase=new Firebase("https://sweltering-torch-4496.firebaseio.com/");
    ListView pslist;
    private String[] list={"王大錘","老媽","老姐","Tyson","Jack","Neymar"};
    private ArrayAdapter<String> listAdapter;
    Button accept,cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pslist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        accept=(Button)findViewById(R.id.button14);
        cancel=(Button)findViewById(R.id.button15);
        pslist=(ListView)findViewById(R.id.listView2);
        listAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
        pslist.setAdapter(listAdapter);
        pslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        mfirebase.child("Ps").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                  System.out.print(String.valueOf(dataSnapshot.getValue()));
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {


            }
        });

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(Pslist.this,MainActivity.class);
                startActivity(i);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(Pslist.this,MainActivity.class);
                startActivity(i);
            }
        });
    }

}
