package com.example.im01.psmemory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    Button login;
    Button account;
    String accountM,passM,emailM;
    Member member=new Member();
    EditText accounte,password;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference  myFirebaseRef = database.getReference("account");
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        login=(Button)findViewById(R.id.button3);
        account=(Button)findViewById(R.id.button4);
        accounte=(EditText)findViewById(R.id.editText2);
        password=(EditText)findViewById(R.id.editText);

        myFirebaseRef.child("member1").child("keyname").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                accountM=String.valueOf(dataSnapshot.getValue());
                System.out.println(String.valueOf(dataSnapshot.getValue()));

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
        myFirebaseRef.child("member1").child("keypassword").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                passM=String.valueOf(dataSnapshot.getValue());
                System.out.println(String.valueOf(dataSnapshot.getValue()));

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
        myFirebaseRef.child("member1").child("email").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                emailM=dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

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
