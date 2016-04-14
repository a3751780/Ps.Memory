package com.example.im01.psmemory;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class Ps_fragment extends Fragment {

    private Spinner method;
    private Spinner time;
    ArrayAdapter<String> methodlist;
    ArrayAdapter<String> timelist;
    String methodselect[]={"E-mail","簡訊","Facebook","Twitter","其他"};
    String timeselect[]={"天","小時","分鐘"};
    Button accept;
    Button acceptd;
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

        methodlist = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,methodselect);
        method.setAdapter(methodlist);

        method.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               final Dialog set=new Dialog(getActivity());
                set.setTitle("想對誰說");
                set.setContentView(R.layout.dialog_layout);
                set.show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog acc=new Dialog(getActivity());
                acc.setTitle("想多久問候他");
                acc.setContentView(R.layout.dialog_accept);
                acc.show();
                time=(Spinner)acc.findViewById(R.id.spinner2);
                acceptd=(Button)acc.findViewById(R.id.accept);
                timelist = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_item,timeselect);
                time.setAdapter( timelist);
                acceptd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      acc.dismiss();

                    }
                });
            }
        });


        return root;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
