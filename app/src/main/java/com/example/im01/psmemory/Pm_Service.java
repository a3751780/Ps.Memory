package com.example.im01.psmemory;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.example.im01.psmemory.Gmail.GMailSender;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Pm_Service extends Service {
    int count=1;
    String TAG = "Service";
    boolean isStop = false;
    String time = " ";
    String date = " ";
    String[] Date;
    String[] Time;
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
                   // Log.e(TAG,ps.sendtimeS[0]);
                    System.out.println(ps.sendtimeS[0]);
                        //date是現在時間
                        if(date.equals(ps.sendtimeS[i])){

                            try{
                                sender.sendMail(ps.titleS[i],ps.messageS[i],ps.emailS[i],ps.emailS[i]);
                                i++;
                            }catch(Exception e){

                                e.printStackTrace();

                            }

                            break;


                       // break;
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

    @Override
    public void onDestroy() {

        isStop=true;
        super.onDestroy();
    }

}