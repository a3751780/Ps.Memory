package com.example.im01.psmemory;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Firebase;

public class MenberIn extends AppCompatActivity {
    Firebase mfirebase;
    TextView name,password,email;
    Member member=new Member();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menber_in);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mfirebase=new Firebase("https://sweltering-torch-4496.firebaseio.com/");
        name=(TextView)findViewById(R.id.name);
        password=(TextView)findViewById(R.id.password);
        email=(TextView)findViewById(R.id.email);
        password.setText(member.passworda);
        name.setText(member.accanswer);
        email.setText(member.email);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
