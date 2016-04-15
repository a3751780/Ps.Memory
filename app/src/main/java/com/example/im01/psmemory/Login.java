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
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class Login extends AppCompatActivity {
    Button login;
    Button account;
    String accanswer,passworda;
    EditText accounte,password;
    Firebase myFirebaseRef ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Firebase.setAndroidContext(this);

        login=(Button)findViewById(R.id.button3);
        account=(Button)findViewById(R.id.button4);
        accounte=(EditText)findViewById(R.id.editText2);
        password=(EditText)findViewById(R.id.editText);
        myFirebaseRef = new Firebase("https://sweltering-torch-4496.firebaseio.com/");

        //firebase
        myFirebaseRef.child("keyname").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                accanswer=String.valueOf(dataSnapshot.getValue());
                System.out.println(dataSnapshot.getValue());


                //  name.setText( String.valueOf(dataSnapshot.getValue()));

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }

        });
        myFirebaseRef.child("keypassword").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                passworda=String.valueOf(dataSnapshot.getValue());
                System.out.println(dataSnapshot.getValue());



                //  name.setText( String.valueOf(dataSnapshot.getValue()));

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }

        });
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent acco=new Intent();
                acco.setClass(Login.this,Account.class);
                startActivity(acco);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(accounte.getText().toString().equals(accanswer)&& password.getText().toString().equals(passworda)){
                    Intent log=new Intent();
                    log.setClass(Login.this,MainSelect.class);
                    startActivity(log);
                }else{
                    Toast.makeText(Login.this,"帳號或密碼有誤"+accounte.getText().toString()+password.getText().toString(),Toast.LENGTH_LONG).show();


                }


            }
        });


    }

}
