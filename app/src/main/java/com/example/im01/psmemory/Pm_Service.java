package com.example.im01.psmemory;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.example.im01.psmemory.Gmail.GMailSender;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Pm_Service extends Service {
    int count=1;
    String TAG="Service";
    boolean isStop=false;
    String time = " ";
    String date = " ";
    String[] Date;
    String[] Time;
   // Firebase myfirebase=new Firebase("https://sweltering-torch-4496.firebaseio.com/").child("Ps");
    SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
    SimpleDateFormat nowtime = new SimpleDateFormat("HH:mm:ss");
    int nowyear=0,nowmonth=0,nowday=0,nowminute=0,nowhour=0,nowsecond=0;
    GMailSender sender=new GMailSender("s3751780@gmail.com ","happy0204");

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

                    /*myfirebase.child("Ps"+count).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            count++;

                            //  name.setText( String.valueOf(dataSnapshot.getValue()));

                        }
                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }

                    });*/
                    if(nowminute==52){
                        try{
                            sender.sendMail("ff","sdfdf","s3751780@gmail.com","s3751780@gmail.com");
                        }catch(Exception e){

                            e.printStackTrace();

                        }
                        break;
                    }
                    Log.e(TAG,Time[1]);
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

    @Override
    public void onDestroy() {

        isStop=true;
        super.onDestroy();
    }

}