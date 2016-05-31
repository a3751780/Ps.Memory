package com.example.im01.psmemory;

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


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Friend extends AppCompatActivity {
    Button accept,cancel;
    ListView friendlist;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("account");
    TextView namef,emailf,sex,phone;
    String memberlist;
    Button back,choice;
    String count;
    String emailM,nameM,sexM,phoneM;
    //String count="0";
    String[] list={"Tyson","Gino"};
    private ArrayAdapter<String> listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        accept=(Button)findViewById(R.id.button11);
        cancel=(Button)findViewById(R.id.button12);
        friendlist=(ListView)findViewById(R.id.listView);
        //設定listview值
        int countlist=1;
        for(int i = 0 ; i < 3 ; i++){
            findname(countlist);
           // list[i]="";
            countlist++;
        }

        listAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
        friendlist.setAdapter(listAdapter);

        //設定listview監聽
        friendlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            if(position==0){

                count="1";

                Log.e("COUNT",count);

                if(count.equals("1")){

                    final Dialog set=new Dialog(Friend.this);
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
                if(count.equals("2")){

                    final Dialog set=new Dialog(Friend.this);
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



    }

    public void findmember(String count){
        myRef.child("member"+count).child("keyname").addValueEventListener(new ValueEventListener() {
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

        myRef.child("member"+count).child("email").addValueEventListener(new ValueEventListener() {
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
        myRef.child("member"+count).child("phonenumber").addValueEventListener(new ValueEventListener() {
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
        myRef.child("member"+count).child("sex").addValueEventListener(new ValueEventListener() {
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
    public void findname(int count){
        myRef.child("member"+count).child("keyname").addValueEventListener(new ValueEventListener() {
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

    }

}
