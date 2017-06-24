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


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainSelect extends AppCompatActivity {

    Button ps,memory;
    Activity main;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mfirebase = database.getReference("Ps");
    DatabaseReference mfirebaseA = database.getReference("Acount");
    String selecttime[]=new String[10];
    TextView test;
    public String sendtimeI;
    int count=0,countE=0,countM=0,countT=0,countP=0,countN=0;
    String Time[];
    String message[]=new String[3];
    String email[]=new String[3];
    String title[]=new String[3];
    String number[]=new String[3];
    String name[]=new String[3];
    int i=1;
    Ps pss=new Ps();
    int selectday,selectmonth,selectyear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_select);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        test=(TextView)findViewById(R.id.test);
      //  Service();
        try{
            for(i = 1 ; i < 4 ; i ++){
                //firebase
                    mfirebaseA.child("Member1").child("Ps").child("Ps"+i).child("Sendtime").addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                        @Override
                        public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                            // This method is called once with the initial value and again
                            // whenever data at this location is updated.
                           // selecttime[count]=String.valueOf(dataSnapshot.getValue());
                         //   System.out.println(count);
                          //  System.out.println(selecttime[count]);

                            count++;

                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value


                        }
                    });

                    Firebasestart();


            }
        }catch (ArrayIndexOutOfBoundsException e){

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
                    pss.messageS[h]=message[h];
                }
                for(int h=0 ; h < pss.emailS.length ; h++){
                    pss.emailS[h]=email[h];
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

                main.finish();

                startActivity(i);
            }
        });

        memory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x=new Intent();
                x.setClass(MainSelect.this,MainActivity.class);
                x.putExtra("id",1);


                for(int h=0 ; h < pss.messageS.length ; h++){
                    pss.messageS[h]=message[h];
                }
                for(int h=0 ; h < pss.emailS.length ; h++){
                    pss.emailS[h]=email[h];
                }
                for(int h=0 ; h < pss.titleS.length ; h++){
                    pss.titleS[h]=title[h];
                }
                for(int h=0;h<pss.sendtimeS.length;h++){
                    pss.sendtimeS[h]=selecttime[h];
                }
                main.finish();
                startActivity(x);
            }
        });

    }
    public void Firebasestart() throws ArrayIndexOutOfBoundsException {

        mfirebaseA.child("Member"+i).child("keyname").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                name[countN]=String.valueOf(dataSnapshot.getValue());
                Member.name[countN]=name[countN];
                countN++;
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
        Query name=mfirebaseA.limitToLast(100);

        mfirebaseA.child("Member1").child("Ps").child("Ps"+i).child("Message").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                message[countM]=String.valueOf(dataSnapshot.getValue());
                System.out.println(countM);
                System.out.println(message[countM]);
                countM++;

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

        mfirebaseA.child("Member1").child("Ps").child("Ps"+i).child("Email").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                email[countE]=String.valueOf(dataSnapshot.getValue());
                System.out.println(countE);
                System.out.println( email[countE]);
                countE++;

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });


        mfirebaseA.child("Member1").child("Ps").child("Ps"+i).child("Title").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                title[countT]=String.valueOf(dataSnapshot.getValue());
                System.out.println(countT);
                System.out.println(title[countT]);
                countT++;

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

        mfirebaseA.child("Member1").child("Ps").child("Ps"+i).child("Phone").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                number[countP]=String.valueOf(dataSnapshot.getValue());
                System.out.println(countP);
                System.out.println(number[countP]);
                countP++;

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
            Ps.pscount++;
    }


}
