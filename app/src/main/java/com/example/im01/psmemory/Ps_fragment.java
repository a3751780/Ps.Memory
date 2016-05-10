package com.example.im01.psmemory;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import android.telephony.gsm.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.im01.psmemory.Gmail.GMailSender;
import com.firebase.client.Firebase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class Ps_fragment extends Fragment {
    private final static String MSG_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    Calendar cal = Calendar.getInstance();
    String dat;
    SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
    SimpleDateFormat nowtime = new SimpleDateFormat("HH:mm:ss");
    String date = " ";
    EditText wanttosay,title;
    private Spinner method;
    int count=1;
    private Spinner time;
    private String who;
    int selectmail=0,selects=0;
    Firebase mfirebase;
    ArrayAdapter<String> methodlist;
    ArrayAdapter<String> timelist;
    String methodselect[]={"選擇你想訴說的方式","簡訊","E-mail","Twitter","其他"};
    String timeselect[]={"天","小時","分鐘"};
    private static final int PERMISSION_SEND_SMS = 123;
    String contactmethod;
    Button accept,selfin,friend;
    Button acceptd;
    LinearLayout wholayout;
    EditText selfinwho;
    String titleF,message,emailF;
    String phone;
    String[] Date;
    int nowyear,nowmonth,nowday;
    GMailSender sender=new GMailSender("s3751780@gmail.com ","happy0204");


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        MainActivity mainActivity = (MainActivity)activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.activity_ps_fragment, container, false);
        method=(Spinner)root.findViewById(R.id.spinner);
        accept=(Button)root.findViewById(R.id.button);
        wholayout= (LinearLayout)root.findViewById (R.id.whoed);
        wanttosay=(EditText)root.findViewById(R.id.editText5);
        title=(EditText)root.findViewById(R.id.editText4);
        mfirebase=new Firebase("https://sweltering-torch-4496.firebaseio.com/").child("Ps");
        methodlist = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,methodselect);
        method.setAdapter(methodlist);

        dat=String.valueOf(sf.format(cal.getTime()));

        sf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        date = String.valueOf(sf.format(new java.util.Date()));
        Date = date.split("/");
        nowyear=Integer.valueOf(Date[0]);
        nowmonth=Integer.valueOf(Date[1]);
        nowday=Integer.valueOf(Date[2]);
        Log.e("Year",Date[0]);
        Log.e("Month",Date[1]);
        Log.e("Day",Date[2]);
              ///  Toast.makeText(getActivity(),date,Toast.LENGTH_LONG).show();
        method.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


               if(position==0){
                   System.out.print("nice");
                   Log.e("S","請選擇");
               }else if(position==1){
                   final Dialog set=new Dialog(getActivity());

                   set.setContentView(R.layout.dialog_layout);
                   set.setTitle("想對誰說");
                   set.show();
                   selfin=(Button)set.findViewById(R.id.button7);
                   friend=(Button)set.findViewById(R.id.buttonf);
                   selectmail=0;
                   selects=1;
                   selfin.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {

                           wholayout.removeAllViews();
                           selfinwho=new EditText(getActivity());
                           selfinwho.setHint("告訴我他的電話");
                           selectmail=0;
                           selects=1;
                           wholayout.addView(selfinwho);
                           set.dismiss();
                       }
                   });
                   friend.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           // wholayout.removeAllViews();
                           EditText friendinwho=new EditText(getActivity());

                           wholayout.addView(friendinwho);
                           Intent i=new Intent();
                           i.setClass(getActivity(),Friend.class);
                           startActivity(i);
                       }
                   });
                }
                else{
                   final Dialog set=new Dialog(getActivity());

                   set.setContentView(R.layout.dialog_layout);
                   set.setTitle("想對誰說");
                   set.show();
                   selects=0;
                   selectmail=1;
                   selfin=(Button)set.findViewById(R.id.button7);
                   friend=(Button)set.findViewById(R.id.buttonf);
                   selfin.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {

                           wholayout.removeAllViews();
                           selfinwho=new EditText(getActivity());
                           selfinwho.setHint("告訴我他的E-mail");
                           selectmail=1;
                           wholayout.addView(selfinwho);
                           set.dismiss();
                       }
                   });
                   friend.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                          // wholayout.removeAllViews();
                           EditText friendinwho=new EditText(getActivity());

                           wholayout.addView(friendinwho);
                           Intent i=new Intent();
                           i.setClass(getActivity(),Friend.class);
                           startActivity(i);
                       }
                   });

               }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactmethod=selfin.getText().toString();
                contactmethod=friend.getText().toString();
                final Dialog acc=new Dialog(getActivity());

                acc.setContentView(R.layout.dialog_accept);
                acc.setTitle("想多久問候他");
                acc.show();
                time=(Spinner)acc.findViewById(R.id.spinner2);
                acceptd=(Button)acc.findViewById(R.id.accept);

                timelist = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_item,timeselect);
                time.setAdapter(timelist);

                acceptd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        count++;
                        if(selectmail==1){
                            mfirebase.child("Ps"+count).child("message").setValue(wanttosay.getText().toString());
                            mfirebase.child("Ps"+count).child("title").setValue(title.getText().toString());
                            mfirebase.child("Ps"+count).child("e-mail").setValue(selfinwho.getText().toString());
                            Log.e("主旨",title.getText().toString());
                            Log.e("訊息",wanttosay.getText().toString());
                            Log.e("收件人",selfinwho.getText().toString());
                            titleF=title.getText().toString();
                            message=wanttosay.getText().toString();
                            emailF=selfinwho.getText().toString();

                            new AsyncTask<Void, Void, Void>() {

                                @Override
                                protected void onPreExecute()
                                {

                                }

                                @Override
                                protected Void doInBackground(Void... params)
                                {

                                    try{
                                        sender.sendMail(titleF,message,"s3751780@gmail.com", emailF);
                                    }catch(Exception e){

                                        e.printStackTrace();

                                    }
                                    return null;
                                }

                                @Override
                                protected void onPostExecute(Void res)
                                {

                                }
                            }.execute();
                            selectmail=0;
                            acc.dismiss();
                        }

                       else if(selects==1){
                            if(nowday==10){
                                ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.SEND_SMS},1);
                                requestSmsPermission();
                                send();

                            }
                            acc.dismiss();
                        }
                    }
                });
            }
        });


        return root;
    }
    private void requestSmsPermission() {

        // check permission is given
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            // request permission (see result in onRequestPermissionsResult() method)
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.SEND_SMS},
                    PERMISSION_SEND_SMS);
        } else {
            // permission already granted run sms send
            send();
        }
    }
    @Override
    public void onRequestPermissionsResult (int requestCode,String permissions[],int[] grantResults){
        switch (requestCode) {
            case 1: {
                // 如果權限請求被取消了，grantResults array 的 length 會是 0
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 權限請求通過的處理
                  send();
                } else {
                    // 權限請求駁回的處理(如告知使用者那些功能無法使用之類的)
                }
                return;
            }

        }
    }

    private void send(){
        mfirebase.child("Ps"+count).child("message").setValue(wanttosay.getText().toString());
        mfirebase.child("Ps"+count).child("title").setValue(title.getText().toString());
        mfirebase.child("Ps"+count).child("phonenumber").setValue(selfinwho.getText().toString());
        Log.e("主旨",title.getText().toString());
        Log.e("訊息",wanttosay.getText().toString());
        Log.e("收件人",selfinwho.getText().toString());
        titleF=title.getText().toString();
        message=wanttosay.getText().toString();
        phone=selfinwho.getText().toString();

        SmsManager smsManager = SmsManager.getDefault();
        try{
            smsManager.sendTextMessage(phone,
                    null,
                   message, PendingIntent.getBroadcast(getActivity().getApplicationContext(), 0, new Intent(), 0), null);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
