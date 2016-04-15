package com.example.im01.psmemory;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Memory_frag extends Fragment {
    private String value = "";
    private Spinner method;
    Button fromfriend;
    ArrayAdapter<String> methodlist;
    String methodselect[]={"請選擇要紀念的方式","簡訊","Facebook","E-mail","其他"};
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        MainActivity mainActivity = (MainActivity)activity;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root=inflater.inflate(R.layout.activity_memory_frag, container, false);
        method=(Spinner)root.findViewById(R.id.spinner);
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
                    fromfriend=(Button)set.findViewById(R.id.buttonf);
                    fromfriend.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i=new Intent();
                            i.setClass(getActivity(),Friend.class);
                            startActivity(i);
                        }
                    });
                    set.show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return  root;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}
