package com.example.im01.psmemory;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class Pslist extends AppCompatActivity {

    Firebase mfirebase=new Firebase("https://sweltering-torch-4496.firebaseio.com/");
    ListView pslist;
    private String[] list = {"Ps1","Ps2"};
    private ArrayAdapter<String> listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pslist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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

                    for(int i=0;i<5;i++){

                    }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {


            }
        });
    }

}
