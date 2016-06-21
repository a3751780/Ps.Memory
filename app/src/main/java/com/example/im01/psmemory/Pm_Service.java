package com.example.im01.psmemory;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.example.im01.psmemory.Gmail.GMailSender;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Pm_Service extends Service {

    int count=1;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mfirebase = database.getReference("Acount");

    String TAG = "Service";
    boolean isStop = false;
    String time = " ";
    String date = " ";
    String[] Date;
    String[] Time;
    String[] send=new String[50];
    String[] message=new String[50];
    String[] title=new String[50];
    String[] email=new String[50];
    int countS=0,countT=0,countE=0,countM=0;
    Ps ps = new Ps();
    int i = 0;
    SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
    SimpleDateFormat nowtime = new SimpleDateFormat("HH:mm:ss");
    int nowyear=0,nowmonth=0,nowday=0,nowminute=0,nowhour=0,nowsecond=0;
    GMailSender sender = new GMailSender("s3751780@gmail.com ","happy0204");
    String sendtime;
    int sendtimeI[];

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onCreate() {

        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                while(!isStop){

                    sf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                    date = String.valueOf(sf.format(new java.util.Date()));
                    Date = date.split("/");
                    nowyear=Integer.valueOf(Date[0]);
                    nowmonth=Integer.valueOf(Date[1]);
                    nowday=Integer.valueOf(Date[2]);

                    nowtime.setTimeZone(TimeZone.getTimeZone("GMT+8"));//==取得目前時間
                    time = String.valueOf(nowtime.format(new java.util.Date()));
                    Time = time.split(":");
                    nowhour=Integer.valueOf(Time[0]);//hour 是24小時制
                    nowminute=Integer.valueOf(Time[1]);
                    nowsecond = Integer.valueOf(Time[2]);
                    Log.e(TAG,date);
                    Log.e(TAG,time);
                    try{
                        for(int f = 1 ; f < 5 ; f ++){
                            startfirebase(f);

                            Log.e(TAG,send[i]);
                            i++;
                            if(date.equals(send[i]) && time.equals("17:05:00")){

                                try{
                                    sender.sendMail(title[i],message[i],email[i],email[i]);

                                }catch(Exception e){
                                    e.printStackTrace();
                                }
                                break;
                            }

                        }

                        //每次迴圈跑完歸零
                        i=0;
                        countS=0;
                        countE=0;
                        countT=0;
                        countM=0;
                    }catch(Exception e){
                        Log.e("Error","allmight");
                    }
                    try {
                       Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
               //執行後台作業
            }
        }).start();

        return super.onStartCommand(intent, flags, startId);
    }


    public void startfirebase(int f){


        mfirebase.child("Member1").child("Ps").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                send[countS]=String.valueOf(dataSnapshot.child("Sendtime").getValue());
                // System.out.println(send[countS]);
                countS++;
                message[countM]=String.valueOf(dataSnapshot.child("Message").getValue());
                countM++;
                email[countE]=String.valueOf(dataSnapshot.child("Email").getValue());
                countE++;
                title[countT]=String.valueOf(dataSnapshot.child("Title").getValue());
                countT++;
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                send[countS]=String.valueOf(dataSnapshot.child("Sendtime").getValue());
                // System.out.println(send[countS]);
                countS++;
                message[countM]=String.valueOf(dataSnapshot.child("Message").getValue());
                countM++;
                email[countE]=String.valueOf(dataSnapshot.child("Email").getValue());
                countE++;
                title[countT]=String.valueOf(dataSnapshot.child("Title").getValue());
                countT++;
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
        });

        mfirebase.child("Member1").child("Ps").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });


    }
    @Override
    public void onDestroy() {

        isStop=true;
        super.onDestroy();
    }

}