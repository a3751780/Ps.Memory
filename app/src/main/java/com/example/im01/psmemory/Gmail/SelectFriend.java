package com.example.im01.psmemory.Gmail;

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

public class SelectFriend extends AppCompatActivity {
    ListView friendlist;
    private String[] list={"Tyson","Gino"};
    private ArrayAdapter<String> listAdapter;
    Button accept,cancel;

    Firebase mfirebase;
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
        mfirebase=new Firebase("https://sweltering-torch-4496.firebaseio.com/");
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
                Intent i=new Intent();
                i.setClass(SelectFriend.this,MainActivity.class);
                startActivity(i);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(SelectFriend.this,MainActivity.class);
                startActivity(i);
            }
        });

    }
    public void findmember(String count){
        mfirebase.child("account").child("member"+count).child("email").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                emailM=String.valueOf(dataSnapshot.getValue());
                //  name.setText( String.valueOf(dataSnapshot.getValue()));

            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }

        });
        mfirebase.child("account").child("member"+count).child("sex").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sexM=String.valueOf(dataSnapshot.getValue());
                //  name.setText( String.valueOf(dataSnapshot.getValue()));

            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }

        });
        mfirebase.child("account").child("member"+count).child("phonenumber").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                phoneM=String.valueOf(dataSnapshot.getValue());
                //  name.setText( String.valueOf(dataSnapshot.getValue()));

            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }

        });
        mfirebase.child("account").child("member"+count).child("keyname").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                nameM=String.valueOf(dataSnapshot.getValue());
                //  name.setText( String.valueOf(dataSnapshot.getValue()));

            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }

        });

    }
}
