package com.example.im01.psmemory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class MainSelect extends AppCompatActivity {

    Button ps,memory;
    Activity main;
    Firebase mfirebase;
    String selecttime[]=new String[10];
    TextView test;
    public String sendtimeI;
    int count=0,countE=0,countM=0,countT=0,countP=0;
    String Time[];
    String message[]=new String[3];
    String email[]=new String[3];
    String title[]=new String[3];
    String number[]=new String[3];
    int i;
    Ps pss=new Ps();
    int selectday,selectmonth,selectyear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_select);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Firebase.setAndroidContext(this);

        mfirebase=new Firebase("https://sweltering-torch-4496.firebaseio.com/").child("Ps");

        test=(TextView)findViewById(R.id.test);
      //  Service();
        for(i = 1 ; i < 4 ; i ++){
            //firebase
            mfirebase.child("Ps"+i).child("sendtime").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    selecttime[count]=String.valueOf(dataSnapshot.getValue());
                    System.out.println(count);
                    System.out.println(selecttime[count]);
                    count++;
                    //  name.setText( String.valueOf(dataSnapshot.getValue()));
                }
                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }


            });

                Firebasestart();

        }
        //pss.sendtimeS[0]=sendtimeI[0];


        test.setText(Member.accanswer+" 's ");
        main=this;
        ps=(Button)findViewById(R.id.button6);
        memory=(Button)findViewById(R.id.button13);



        ps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int h=0 ; h < pss.messageS.length ; h++){
                    pss.titleS[h]=message[h];
                }
                for(int h=0 ; h < pss.emailS.length ; h++){
                    pss.titleS[h]=email[h];
                }
                for(int h=0 ; h < pss.titleS.length ; h++){
                    pss.titleS[h]=title[h];
                }
                for(int h=0;h<pss.sendtimeS.length;h++){
                    pss.sendtimeS[h]=selecttime[h];
                }

                Intent i=new Intent();
                i.setClass(MainSelect.this,MainActivity.class);
               // i.putExtra("id",1);

               // main.finish();

                startActivity(i);
            }
        });

        memory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x=new Intent();
                x.setClass(MainSelect.this,MainActivity.class);
                x.putExtra("id",1);

               // main.finish();
                for(int h=0 ; h < pss.messageS.length ; h++){
                    pss.titleS[h]=message[h];
                }
                for(int h=0 ; h < pss.emailS.length ; h++){
                    pss.titleS[h]=email[h];
                }
                for(int h=0 ; h < pss.titleS.length ; h++){
                    pss.titleS[h]=title[h];
                }
                for(int h=0;h<pss.sendtimeS.length;h++){
                    pss.sendtimeS[h]=selecttime[h];
                }
                startActivity(x);
            }
        });

    }
    public void Firebasestart(){
        mfirebase.child("Ps"+i).child("message").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                message[countM]=String.valueOf(dataSnapshot.getValue());
                System.out.println(countM);
                System.out.println( message[countM]);
                countM++;

                //  name.setText( String.valueOf(dataSnapshot.getValue()));
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }


        });
        mfirebase.child("Ps"+i).child("e-mail").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                email[countE]=String.valueOf(dataSnapshot.getValue());
                System.out.println(countE);
                System.out.println( email[countE]);
                countE++;
                //  name.setText( String.valueOf(dataSnapshot.getValue()));
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }


        });

        mfirebase.child("Ps"+i).child("title").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                title[countT]=String.valueOf(dataSnapshot.getValue());
                System.out.println(countT);
                System.out.println(title[countT]);
                countT++;
                //  name.setText( String.valueOf(dataSnapshot.getValue()));
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }


        });
        mfirebase.child("Ps"+i).child("phonenumber").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                number[countP]=String.valueOf(dataSnapshot.getValue());
                System.out.println(countP);
                System.out.println(number[countP]);
                countP++;
                //  name.setText( String.valueOf(dataSnapshot.getValue()));
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }


        });

    }
    public void Service(){
        Intent i=new Intent(this,Pm_Service.class);

        startService(i);
    }

}
