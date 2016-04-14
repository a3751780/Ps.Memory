package com.example.im01.psmemory;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class Memory_frag extends Fragment {
    private String value = "";
    private Spinner method;
    ArrayAdapter<String> methodlist;
    String methodselect[]={"E-mail","簡訊","Facebook","Twitter","其他"};
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        MainActivity mainActivity = (MainActivity)activity;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.activity_memory_frag, container, false);
        method=(Spinner)root.findViewById(R.id.spinner);
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
        return  root;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}
