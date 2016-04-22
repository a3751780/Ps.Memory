package com.example.im01.psmemory;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

public class Account extends AppCompatActivity {

    EditText account,password,email;
    String accountA,passwordA,emailA;
    Firebase mfirebase;
    Button accept;
    int count=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        account=(EditText)findViewById(R.id.editText7);
        password=(EditText)findViewById(R.id.editText8);
        email=(EditText)findViewById(R.id.editText9);
        accept=(Button)findViewById(R.id.button8);

        mfirebase=new Firebase("https://sweltering-torch-4496.firebaseio.com/").child("account");

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accountA=account.getText().toString();
                passwordA=password.getText().toString();
                emailA=email.getText().toString();
                count++;
                mfirebase.child("member"+count).child("keyname").setValue(accountA);
                mfirebase.child("member"+count).child("keypassword").setValue(passwordA);
                mfirebase.child("member"+count).child("email").setValue(emailA);

                Intent i=new Intent();
                i.setClass(Account.this,Login.class);
                startActivity(i);
            }
        });

    }

}
