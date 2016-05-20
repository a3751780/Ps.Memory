package com.example.im01.psmemory;

import android.content.Intent;
import android.os.Bundle;
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
    String accountM,passM,emailM;
    Member member=new Member();
    EditText accounte,password;
    Firebase myFirebaseRef ;
    int count=0;
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
        myFirebaseRef = new Firebase("https://sweltering-torch-4496.firebaseio.com/").child("account");

        //firebase
        myFirebaseRef.child("member1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                count++;
                //  name.setText( String.valueOf(dataSnapshot.getValue()));


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }

        });

        myFirebaseRef.child("member1").child("email").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               emailM=String.valueOf(dataSnapshot.getValue());
                //  name.setText( String.valueOf(dataSnapshot.getValue()));

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }

        });

        myFirebaseRef.child("member1").child("keyname").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                accountM=String.valueOf(dataSnapshot.getValue());
                System.out.println(dataSnapshot.getValue());
               // System.out.println(member.accanswer);
                //  name.setText( String.valueOf(dataSnapshot.getValue()));
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }

        });
        myFirebaseRef.child("member1").child("keypassword").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                passM=String.valueOf(dataSnapshot.getValue());
                System.out.println(dataSnapshot.getValue());
              //  System.out.println( member.passworda);

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
                if(accounte.getText().toString().equals(accountM)&& password.getText().toString().equals(passM)){
                    Intent log=new Intent();
                    log.setClass(Login.this,MainSelect.class);
                    startActivity(log);
                    member.email=emailM;
                    member.accanswer=accountM;
                    member.passworda=passM;

                }
                else if(accounte.getText().toString().equals("")){
                    Toast.makeText(Login.this,"帳號尚未輸入喔",Toast.LENGTH_LONG).show();
                }
                else if(password.getText().toString().equals("")){
                    Toast.makeText(Login.this,"密碼尚未輸入喔",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(Login.this,"帳號或密碼有誤"+accounte.getText().toString()+password.getText().toString(),Toast.LENGTH_LONG).show();
                }


            }
        });


    }

}
