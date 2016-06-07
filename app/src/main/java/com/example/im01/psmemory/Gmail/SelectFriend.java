package com.example.im01.psmemory.Gmail;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.im01.psmemory.MainActivity;
import com.example.im01.psmemory.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SelectFriend extends AppCompatActivity {
    ListView friendlist;
    String[] list={"Jason","Jacky","Ducker"};
    private ArrayAdapter<String> listAdapter;
    Button accept,cancel;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mfirebase = database.getReference("Acount");//抓取table名稱
    Activity main;
    TextView namef,emailf,sex,phone;
    String memberlist;
    Button back,choice;
    String count;
    String emailM,nameM,sexM,phoneM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_friend);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        main=this;
        accept=(Button)findViewById(R.id.accept);
        cancel=(Button)findViewById(R.id.cancel);
        friendlist=(ListView)findViewById(R.id.listfriend);

        listAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
        friendlist.setAdapter(listAdapter);
        friendlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position==0){

                    count="1";

                    Log.e("COUNT",count);

                    if(count.equals("1")){

                        final Dialog set=new Dialog(SelectFriend.this);
                        set.setContentView(R.layout.dialog_friend);
                        choice=(Button)set.findViewById(R.id.select);
                        namef=(TextView)set.findViewById(R.id.namef);
                        emailf=(TextView)set.findViewById(R.id.emailf);
                        sex=(TextView)set.findViewById(R.id.sex);
                        phone=(TextView)set.findViewById(R.id.phone);
                        findmember(count);
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
                                i.setClass(SelectFriend.this,MainActivity.class);
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
                    if(count.equals("2")){

                        final Dialog set=new Dialog(SelectFriend.this);
                        set.setContentView(R.layout.dialog_friend);
                        choice=(Button)set.findViewById(R.id.select);
                        namef=(TextView)set.findViewById(R.id.namef);
                        emailf=(TextView)set.findViewById(R.id.emailf);
                        sex=(TextView)set.findViewById(R.id.sex);
                        phone=(TextView)set.findViewById(R.id.phone);
                        findmember(count);
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
                                i.setClass(SelectFriend.this,MainActivity.class);
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
                main.finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               main.finish();
            }
        });

    }
    public void findmember(String count){
        mfirebase.child("Member1").child("Friend").child("Friend"+count).child("Name").addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                nameM = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

        mfirebase.child("Member1").child("Friend").child("Friend"+count).child("Email").addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                emailM = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
        mfirebase.child("Member1").child("Friend").child("Friend"+count).child("Phone").addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
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
        mfirebase.child("Member1").child("Friend").child("Friend"+count).child("Sex").addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
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
}
