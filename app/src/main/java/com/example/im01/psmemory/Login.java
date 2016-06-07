package com.example.im01.psmemory;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    DatabaseReference  myFirebaseRef = database.getReference("Acount");
    FirebaseAuth auth;
    int count=0;
    FirebaseAuth.AuthStateListener authListener;
     String userUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        auth = FirebaseAuth.getInstance();
        login=(Button)findViewById(R.id.button3);
        account=(Button)findViewById(R.id.button4);
        accounte=(EditText)findViewById(R.id.editText2);
        password=(EditText)findViewById(R.id.editText);

        authListener=new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(
                    @NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user!=null) {
                    Log.d("onAuthStateChanged", "登入:"+
                            user.getUid());
                    userUID =  user.getUid();
                }else{
                    Log.d("onAuthStateChanged", "已登出");
                }
            }
        };


        myFirebaseRef.child("Member1").child("keyname").addValueEventListener(new ValueEventListener() {
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
        myFirebaseRef.child("Member1").child("keypassword").addValueEventListener(new ValueEventListener() {
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
        myFirebaseRef.child("Member1").child("email").addValueEventListener(new ValueEventListener() {
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
                login();

              /*  if(accounte.getText().toString().equals(accountM)&& password.getText().toString().equals(passM)){


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
*/

            }
        });

    }
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        auth.removeAuthStateListener(authListener);
    }
    public void login(){
       final String emailL =  accounte
                .getText().toString();
        final String passwordL = password
                .getText().toString();
        Log.d("AUTH", emailL+"/"+passwordL);
        auth.signInWithEmailAndPassword(emailL, passwordL)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.d("onComplete", "onComplete");
                            Intent log=new Intent();
                            log.setClass(Login.this,MainSelect.class);
                            startActivity(log);
                            member.email= emailL;
                            member.accanswer=accountM;
                            member.passworda=passwordL;
                        }

                        if (!task.isSuccessful()){
                            if(emailL.equals("")){
                                Toast.makeText(Login.this,"你忘記輸入帳號囉",Toast.LENGTH_LONG).show();
                            }else if(passwordL.equals("")){
                                Toast.makeText(Login.this,"你忘記輸入密碼囉",Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(Login.this,"帳號密碼錯誤喔",Toast.LENGTH_LONG).show();
                            }

                            Log.d("onComplete", "登入失敗");

                        }
                    }
                });


    }
}
