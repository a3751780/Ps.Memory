package com.example.im01.psmemory;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StreamDownloadTask;

import java.util.ArrayList;

public class Friend extends AppCompatActivity
        {
    Button accept,cancel;
    ListView friendlist;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Acount");//抓取table名稱
    Firebase myfire=new Firebase("https://project-6390619862189429975.firebaseio.com/Acount/Member1/Friend/");
    TextView namef,emailf,sex,phone;
    String memberlist;
    Button back,choice,ok,can;
    String count;
    String emailM,nameM,sexM,phoneM;
            EditText nameA,sexA,emailA,phoneA;
    //String count="0";
   // String[] list={"Jason","Jacky","Ducker"};
    ArrayList<String> items=new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myfire.setAndroidContext(this);

        accept=(Button)findViewById(R.id.button11);
        cancel=(Button)findViewById(R.id.button12);
        friendlist=(ListView)findViewById(R.id.listView);
        //設定listview值
        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1);
        myfire.addChildEventListener(new com.firebase.client.ChildEventListener() {
            @Override
            public void onChildAdded(com.firebase.client.DataSnapshot dataSnapshot, String s) {
                adapter.add(
                        (String) dataSnapshot.child("Name").getValue());
            }

            @Override
            public void onChildChanged(com.firebase.client.DataSnapshot dataSnapshot, String s) {
                adapter.remove(
                        (String) dataSnapshot.child("Name").getValue());
            }

            @Override
            public void onChildRemoved(com.firebase.client.DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(com.firebase.client.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


       /* myRef.child("1").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                adapter.add(
                        (String) dataSnapshot.getValue());
                System.out.println( "I'm "+(String) dataSnapshot.getValue());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                adapter.remove(
                        (String) dataSnapshot.getValue());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

        friendlist.setAdapter(adapter);

        //設定listview監聽
        friendlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            if(position==0){

                count="1";

                Log.e("COUNT",count);
                findmember(count);
                if(count.equals("1")){

                    final Dialog set=new Dialog(Friend.this);
                    set.setContentView(R.layout.dialog_friend);
                    choice=(Button)set.findViewById(R.id.select);
                    namef=(TextView)set.findViewById(R.id.namef);
                    emailf=(TextView)set.findViewById(R.id.emailf);
                    sex=(TextView)set.findViewById(R.id.sex);
                    phone=(TextView)set.findViewById(R.id.phone);

                    back=(Button)set.findViewById(R.id.button16);

                    namef.setText(nameM);
                    emailf.setText(emailM);
                    sex.setText(sexM);
                    phone.setText(phoneM);




                    back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            set.dismiss();
                        }
                    });

                    choice.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i=new Intent();
                            i.setClass(Friend.this,MainActivity.class);
                            Bundle bundle=new Bundle();
                            bundle.putString("mail",emailM);
                            i.putExtras(bundle);

                            startActivity(i);
                        }
                    });
                    set.setTitle("好友資訊");
                    set.show();

                }


            }
            else if(position==1){
                count="2";

                Log.e("COUNT",count);
                findmember(count);
                if(count.equals("2")){

                    final Dialog set=new Dialog(Friend.this);
                    set.setContentView(R.layout.dialog_friend);
                    choice=(Button)set.findViewById(R.id.select);
                    namef=(TextView)set.findViewById(R.id.namef);
                    emailf=(TextView)set.findViewById(R.id.emailf);
                    sex=(TextView)set.findViewById(R.id.sex);
                    phone=(TextView)set.findViewById(R.id.phone);

                    back=(Button)set.findViewById(R.id.button16);
                    namef.setText(nameM);
                    emailf.setText(emailM);
                    sex.setText(sexM);
                    phone.setText(phoneM);
                    back.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            set.dismiss();
                        }
                    });
                    choice.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            Intent i=new Intent();
                            i.setClass(Friend.this,MainActivity.class);
                            Bundle bundle=new Bundle();
                            bundle.putString("mail",emailM);
                            i.putExtras(bundle);

                            startActivity(i);
                        }
                    });
                    set.setTitle("好友資訊");
                    set.show();
                }
            }

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
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog set=new Dialog(Friend.this);
                set.setContentView(R.layout.add_friend);
                set.show();
                ok=(Button)set.findViewById(R.id.select);
                can=(Button)set.findViewById(R.id.cancel);

                nameA=(EditText)set.findViewById(R.id.editText13);
                sexA=(EditText)set.findViewById(R.id.editText11);
                emailA=(EditText)set.findViewById(R.id.editText12);
                phoneA=(EditText)set.findViewById(R.id.editText10);

                can.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        set.dismiss();
                    }
                });

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myRef.child("Member1").child("Friend").child(nameA.getText().toString()).child("Name").setValue(nameA.getText().toString());
                        myRef.child("Member1").child("Friend").child(nameA.getText().toString()).child("Sex").setValue(sexA.getText().toString());
                        myRef.child("Member1").child("Friend").child(nameA.getText().toString()).child("E-mail").setValue(emailA.getText().toString());
                        myRef.child("Member1").child("Friend").child(nameA.getText().toString()).child("Phone").setValue(phoneA.getText().toString());
                        set.dismiss();
                    }
                });

            }
        });
    }

    public void findmember(String count){
        myRef.child("Member1").child("Friend").child("Friend"+count).child("Name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                nameM = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

        myRef.child("Member1").child("Friend").child("Friend"+count).child("Email").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                emailM = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
        myRef.child("Member1").child("Friend").child("Friend"+count).child("Phone").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                phoneM = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e("E","error");

            }
        });
        myRef.child("Member1").child("Friend").child("Friend"+count).child("Sex").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                sexM = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

    }


    @Override
    protected void onStart(){
            super.onStart();

    }

}
