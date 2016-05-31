package com.example.im01.psmemory;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import com.example.im01.psmemory.Gmail.SelectFriend;
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
    int fromfriend=0;
    String inputday,inputmonth,inputyear;
    Ps ps=new Ps();
    Boolean selectmethod=false;
    private Spinner method;
    int count=2;
    private Spinner time;
    private String who;
    int selectmail=0,selects=0;
    Firebase mfirebase;
    ArrayAdapter<String> methodlist;
    ArrayAdapter<String> timelist;
    String methodselect[]={"選擇你想訴說的方式","簡訊","E-mail","Twitter","其他"};
    String timeselect[]={"天","月","年"};
    private static final int PERMISSION_SEND_SMS = 123;
    String contactmethod;
    Button accept,selfin,friend;
    Button acceptd;
    LinearLayout wholayout;
    EditText selfinwho;
    String titleF,message,emailF;
    String phone;
    String[] Date;
    Bundle bd;

    String mail;
    int selecttime ,sendday;
    int nowyear,nowmonth,nowday,sendtime;
    GMailSender sender=new GMailSender("s3751780@gmail.com ","happy0204");
    EditText inputtime;
    EditText friendinwho;
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

       // bd=getActivity().getIntent().getExtras();
       // mail=bd.getString("mail");

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
                           selectmethod=true;
                           wholayout.addView(selfinwho);
                           set.dismiss();
                       }
                   });
                   friend.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           // wholayout.removeAllViews();
                           Intent i=new Intent();
                           i.setClass(getActivity(), SelectFriend.class);
                           startActivity(i);
                           friendinwho=new EditText(getActivity());
                           selectmethod=true;
                           wholayout.addView(friendinwho);

                           fromfriend=1;
                           //i.setClass(getActivity(),Friend.class);
                          // startActivity(i);
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
                           selectmethod=true;
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
                           friendinwho=new EditText(getActivity());
                           selectmethod=true;
                           fromfriend=2;
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

                if(selectmethod==false){
                    Toast.makeText(getActivity(),"還沒選擇訴說方式喔喔",Toast.LENGTH_LONG).show();
                   // System.out.println(selectmathod);
                }
                else if(selectmethod==true){
                  //  System.out.println(selectmathod);

                    contactmethod=selfin.getText().toString();
                    contactmethod=friend.getText().toString();

                    final Dialog acc=new Dialog(getActivity());
                    acc.setContentView(R.layout.dialog_accept);
                    inputtime=(EditText)acc.findViewById(R.id.editText6);
                    acc.setTitle("想多久問候他");
                    acc.show();
                    time=(Spinner)acc.findViewById(R.id.spinner2);
                    acceptd=(Button)acc.findViewById(R.id.accept);

                    timelist = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item,timeselect);
                    time.setAdapter(timelist);

                    time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if(position==0){
                                selecttime=0;
                                inputday=inputtime.getText().toString();

                            }else if(position==1){
                                selecttime=1;
                                inputmonth=inputtime.getText().toString();

                            }else if(position==2){
                                selecttime=2;
                                inputyear=inputtime.getText().toString();

                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    acceptd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count++;
                            String want=wanttosay.getText().toString();
                            String ti= title.getText().toString();
                            String self= selfinwho.getText().toString();

                            if(want.equals("")){
                                Toast.makeText(getActivity(),"還沒輸入想說的話喔",Toast.LENGTH_LONG).show();
                            }
                            else  if(ti.equals("")){
                                Toast.makeText(getActivity(),"還沒輸入主旨喔",Toast.LENGTH_LONG).show();
                            }
                            else if(self.equals("")){
                                Toast.makeText(getActivity(),"還沒輸入email喔",Toast.LENGTH_LONG).show();
                            }
                            else {

                                System.out.println("你進來了"+wanttosay.getText().toString()+title.getText().toString()+selfinwho.getText().toString());
                                if(selectmail==1){
                                    sendtime=Integer.valueOf(inputtime.getText().toString());
                                    mfirebase.child("Ps"+count).child("message").setValue(wanttosay.getText().toString());
                                    mfirebase.child("Ps"+count).child("title").setValue(title.getText().toString());
                                    mfirebase.child("Ps"+count).child("e-mail").setValue(selfinwho.getText().toString());
                                    if(selecttime==0){
                                        sendday=nowday+sendtime;
                                        mfirebase.child("Ps"+count).child("sendtime").setValue(nowyear+"/"+nowmonth+"/"+sendday);
                                    }else if(selecttime==1){
                                        sendday=nowmonth+sendtime;
                                        mfirebase.child("Ps"+count).child("sendtime").setValue(nowyear+"/"+sendday+"/"+nowday);
                                    }else if(selecttime==2){
                                        sendday=nowyear+sendtime;
                                        mfirebase.child("Ps"+count).child("sendtime").setValue(sendday+"/"+nowmonth+"/"+nowday);
                                    }

                                    else{
                                        Toast.makeText(getActivity(),"還沒輸入時間喔",Toast.LENGTH_LONG).show();

                                    }

                                    Log.e("主旨",title.getText().toString());
                                    Log.e("訊息",wanttosay.getText().toString());
                                    Log.e("收件人",selfinwho.getText().toString());
                                    Log.e("頻率",inputtime.getText().toString());

                                    selectmail=0;
                                    acc.dismiss();
                                }
                                else if(selects==1){
                                    sendtime=Integer.valueOf(inputtime.getText().toString());
                                    mfirebase.child("Ps"+count).child("message").setValue(wanttosay.getText().toString());
                                    mfirebase.child("Ps"+count).child("title").setValue(title.getText().toString());
                                    mfirebase.child("Ps"+count).child("phonenumber").setValue(selfinwho.getText().toString());
                                    if(selecttime==0){
                                        sendday=nowday+sendtime;
                                        mfirebase.child("Ps"+count).child("sendtime").setValue(nowyear+"/"+nowmonth+"/"+sendday);
                                    }else if(selecttime==1){
                                        sendday=nowmonth+sendtime;
                                        mfirebase.child("Ps"+count).child("sendtime").setValue(nowyear+"/"+sendday+"/"+nowday);
                                    }else if(selecttime==2){
                                        sendday=nowyear+sendtime;
                                        mfirebase.child("Ps"+count).child("sendtime").setValue(sendday+"/"+nowmonth+"/"+nowday);
                                    }

                                    else{
                                        Toast.makeText(getActivity(),"還沒輸入時間喔",Toast.LENGTH_LONG).show();

                                    }
                                    ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.SEND_SMS},1);
                                    requestSmsPermission();
                                    send();
                                    selects=0;
                                    acc.dismiss();
                                }
                                else if(fromfriend==1){
                                    ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.SEND_SMS},1);
                                    requestSmsPermission();
                                    friendinwho.setText(mail);
                                    sendtime=Integer.valueOf(inputtime.getText().toString());
                                    mfirebase.child("Ps"+count).child("message").setValue(wanttosay.getText().toString());
                                    mfirebase.child("Ps"+count).child("title").setValue(title.getText().toString());
                                    mfirebase.child("Ps"+count).child("phonenumber").setValue(friendinwho.getText().toString());
                                    if(selecttime==0){
                                        sendday=nowday+sendtime;
                                        mfirebase.child("Ps"+count).child("sendtime").setValue(nowyear+"/"+nowmonth+"/"+sendday);
                                    }else if(selecttime==1){
                                        sendday=nowmonth+sendtime;
                                        mfirebase.child("Ps"+count).child("sendtime").setValue(nowyear+"/"+sendday+"/"+nowday);
                                    }else if(selecttime==2){
                                        sendday=nowyear+sendtime;
                                        mfirebase.child("Ps"+count).child("sendtime").setValue(sendday+"/"+nowmonth+"/"+nowday);
                                    }

                                    else{
                                        Toast.makeText(getActivity(),"還沒輸入時間喔",Toast.LENGTH_LONG).show();

                                    }
                                    send();
                                    fromfriend=0;
                                    acc.dismiss();

                                }else if(fromfriend==2){
                                    sendtime=Integer.valueOf(inputtime.getText().toString());
                                    friendinwho.setText(mail);
                                    mfirebase.child("Ps"+count).child("message").setValue(wanttosay.getText().toString());
                                    mfirebase.child("Ps"+count).child("title").setValue(title.getText().toString());
                                    mfirebase.child("Ps"+count).child("e-mail").setValue(mail);

                                    if(selecttime==0){
                                        sendday=nowday+sendtime;
                                        mfirebase.child("Ps"+count).child("sendtime").setValue(nowyear+"/"+nowmonth+"/"+sendday);
                                    }else if(selecttime==1){
                                        sendday=nowmonth+sendtime;
                                        mfirebase.child("Ps"+count).child("sendtime").setValue(nowyear+"/"+sendday+"/"+nowday);
                                    }else if(selecttime==2){
                                        sendday=nowyear+sendtime;
                                        mfirebase.child("Ps"+count).child("sendtime").setValue(sendday+"/"+nowmonth+"/"+nowday);
                                    }
                                    else{
                                        Toast.makeText(getActivity(),"還沒輸入時間喔",Toast.LENGTH_LONG).show();

                                    }

                                    Log.e("主旨",title.getText().toString());
                                    Log.e("訊息",wanttosay.getText().toString());
                                    Log.e("收件人",mail);
                                    Log.e("頻率",inputtime.getText().toString());
                                    fromfriend=0;
                                    acc.dismiss();
                                }

                            }

                        }
                    });
                }

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
