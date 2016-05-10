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
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.w3c.dom.Text;

public class Friend extends AppCompatActivity {
    Button accept,cancel;
    ListView friendlist;
    Firebase mfirebase;
    TextView namef,emailf,sex,phone;
    Button back;
    String emailM,nameM,sexM,phoneM;
    //String count="0";
    private String[] list={"Tyson","老媽","老姐","Tyson","Jack","Neymar"};
    private ArrayAdapter<String> listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mfirebase=new Firebase("https://sweltering-torch-4496.firebaseio.com/").child("account");
        accept=(Button)findViewById(R.id.button11);
        cancel=(Button)findViewById(R.id.button12);
        friendlist=(ListView)findViewById(R.id.listView);
        listAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
        friendlist.setAdapter(listAdapter);
        friendlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            if(position==0){

                String count="1";

                Log.e("COUNT",count);
                if(count.equals("1")){

                    final Dialog set=new Dialog(Friend.this);
                    set.setContentView(R.layout.dialog_friend);

                    namef=(TextView)set.findViewById(R.id.namef);
                    emailf=(TextView)set.findViewById(R.id.emailf);
                    sex=(TextView)set.findViewById(R.id.sex);
                    phone=(TextView)set.findViewById(R.id.phone);
                    findmember(count);
                    back=(Button)set.findViewById(R.id.button16);

                    back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            set.dismiss();
                        }
                    });
                    set.setTitle("好友資訊");
                    set.show();

                }


            }
            else if(position==1){
                String count="2";

                Log.e("COUNT",count);
                if(count.equals("2")){

                    final Dialog set=new Dialog(Friend.this);
                    set.setContentView(R.layout.dialog_friend);

                    namef=(TextView)set.findViewById(R.id.namef);
                    emailf=(TextView)set.findViewById(R.id.emailf);
                    sex=(TextView)set.findViewById(R.id.sex);
                    phone=(TextView)set.findViewById(R.id.phone);
                    findmember(count);
                    back=(Button)set.findViewById(R.id.button16);

                    back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            set.dismiss();
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
        mfirebase.child("member"+count).child("email").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                emailM=String.valueOf(dataSnapshot.getValue());
                //  name.setText( String.valueOf(dataSnapshot.getValue()));

            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }

        });
        mfirebase.child("member"+count).child("sex").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sexM=String.valueOf(dataSnapshot.getValue());
                //  name.setText( String.valueOf(dataSnapshot.getValue()));

            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }

        });
        mfirebase.child("member"+count).child("phonenumber").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                phoneM=String.valueOf(dataSnapshot.getValue());
                //  name.setText( String.valueOf(dataSnapshot.getValue()));

            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }

        });
        mfirebase.child("member"+count).child("keyname").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                nameM=String.valueOf(dataSnapshot.getValue());
                //  name.setText( String.valueOf(dataSnapshot.getValue()));

            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }

        });
        namef.setText(nameM);
        emailf.setText(emailM);
        sex.setText(sexM);
        phone.setText(phoneM);
    }

}
