package com.example.im01.psmemory;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.Spinner;

import android.widget.Toast;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.example.im01.psmemory.Dropbox.DBRoulette;
import com.example.im01.psmemory.Dropbox.UploadPicture;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;


public class Memory_frag extends Fragment {
    private CalendarView cv;
    private String value = "";
    private Spinner method;
    Button fromfriend,nowtime,next;
    private DBRoulette myDropboxTool;
    ArrayAdapter<String> methodlist;
    ImageView imageView;
    private String Dir="/Photos/";
    String methodselect[]={"請選擇要紀念的對象"};
    Context context;
    File file;
    String[] Date;
    int selyear,selmonth,selday;
    int nowyear,nowmonth,nowday;
    Calendar cal = Calendar.getInstance();
    String dat;
    SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
    String date = " ";
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        MainActivity mainActivity = (MainActivity)activity;

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root=inflater.inflate(R.layout.activity_memory_frag, container, false);
        imageView = (ImageView)root.findViewById(R.id.imageView2);
        cv=(CalendarView)root.findViewById(R.id.calendarView);
        myDropboxTool  = new DBRoulette(getActivity());
        method=(Spinner)root.findViewById(R.id.spinner);
        methodlist = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,methodselect);
        method.setAdapter(methodlist);
        nowtime=(Button)root.findViewById(R.id.nowtime);
        next=(Button)root.findViewById(R.id.button);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent();
                                    intent.setClass(getActivity(),DBRoulette.class);
                                    startActivity(intent);*/
                Intent intent=new Intent();
                intent.setType("image/*");
                //使用Intent.ACTION_GET_CONTENT這個Action                                            //會開啟選取圖檔視窗讓您選取手機內圖檔
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,1);
            }
        });

        nowtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settime();
            }
        });

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
            cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                    selyear=year;selmonth=month;selday=dayOfMonth;

                }
            });




        return  root;
    }
    public void settime(){
        Calendar day=Calendar.getInstance();

        cv.setDate(day.getTimeInMillis());

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    //取得相片後返回的監聽式
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_OK) {
            //取得圖檔的路徑位置
           /// Uri uri = data.getData();
            Uri uri;
            uri = data.getData();
            File auxFile ;
            String realpath;


            ContentResolver cr = getActivity().getContentResolver();
            ContentResolver ch =getActivity().getContentResolver();

           // InputStream is=ch.openInputStream(uri);
            try {
                //由抽象資料接口轉換圖檔路徑為Bitmap

                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
               // auxFile= new File(realpath);
                auxFile=new File(Environment.getExternalStorageDirectory(), "image.jpg");
                Log.e("Path",uri.getPath());
                myDropboxTool.sentFile(Dir,auxFile);
                // 將Bitmap設定到ImageView

                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                Log.e("Exception", e.getMessage(),e);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getActivity().getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    public void inputstreamtofile(InputStream ins,File file){

    }

    public void GetDateTime(){



    }
    @Override
    public void onResume() {//Return to your app after user authorization
        super.onResume();

        // ...

        myDropboxTool.doOnResume();

        // ...
    }


}
