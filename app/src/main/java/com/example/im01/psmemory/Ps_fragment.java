package com.example.im01.psmemory;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import android.widget.TextView;

import com.firebase.client.Firebase;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;

public class Ps_fragment extends Fragment {
    EditText wanttosay,title;
    private Spinner method;
    int count=1;
    private Spinner time;
    private String who;
    Firebase mfirebase;
    ArrayAdapter<String> methodlist;
    ArrayAdapter<String> timelist;
    String methodselect[]={"選擇你想訴說的方式","簡訊","Facebook","Twitter","其他"};
    String timeselect[]={"天","小時","分鐘"};
    String contactmethod;
    Button accept,selfin,friend;
    Button acceptd;
    LinearLayout wholayout;

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

        method.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


               if(position==0){
                   System.out.print("nice");
                   Log.e("S","請選擇");
               }
                else{
                   final Dialog set=new Dialog(getActivity());
                   set.setTitle("想對誰說");
                   set.setContentView(R.layout.dialog_layout);
                   set.show();
                   selfin=(Button)set.findViewById(R.id.button7);
                   friend=(Button)set.findViewById(R.id.buttonf);
                   selfin.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {

                           wholayout.removeAllViews();
                           EditText selfinwho=new EditText(getActivity());
                           selfinwho.setHint("告訴我她的E-mail");
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
                acc.setTitle("想多久問候他");
                acc.setContentView(R.layout.dialog_accept);
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
                        mfirebase.child("Ps"+count).child("message").setValue(wanttosay.getText().toString());
                        mfirebase.child("Ps"+count).child("title").setValue(title.getText().toString());
                        mfirebase.child("Ps"+count).child("e-mail").setValue(contactmethod);
                        acc.dismiss();

                    }
                });
            }
        });


        return root;
    }
    public void sendmail(){
        String host = "smtp.gmail.com";
        int port = 587;
        final String username = "user@gmail.com";
        final String password = "your password";//your password

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", port);



    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
