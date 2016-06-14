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
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.im01.psmemory.FireUpload.Main;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;



public class Memory_frag extends Fragment {
    private CalendarView cv;
    private String value = "";
    private Spinner method;
    Fragment main;
    Button fromfriend,nowtime,next;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Acount");
    ArrayAdapter<String> methodlist;
    EditText who;
    ImageView imageView;
    private String Dir="/Photos/";
    String methodselect[]={"請選擇要紀念的Ps","Jason","Skyler","Jacky"};
    Context context;
    File file;
    String[] Date;
    int selectwho=0;
    int selyear,selmonth,selday;
    int nowyear,nowmonth,nowday;
    Calendar cal = Calendar.getInstance();
    String dat;
    SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
    String date = " ";
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);


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
        main=this;
        who=(EditText) root.findViewById(R.id.editText3);
        method=(Spinner)root.findViewById(R.id.spinner);
        methodlist = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,methodselect);
        method.setAdapter(methodlist);
        nowtime=(Button)root.findViewById(R.id.nowtime);

        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                selyear=year;selmonth=month;selday=dayOfMonth;
                System.out.println(selyear);
                System.out.println(selmonth+1);
                System.out.println(selday);

            }
        });

        next=(Button)root.findViewById(R.id.button);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


              //  uploadfile();

                if(selectwho==1){
                    myRef.child("Member1").child("Ps").child("Ps1").child("Sendtime").setValue(selyear+"/"+(selmonth+1)+"/"+selday);
                }
                else if(selectwho==2){
                    myRef.child("Member1").child("Ps").child("Ps2").child("Sendtime").setValue(selyear+"/"+(selmonth+1)+"/"+selday);
                }
                else if(selectwho==3){
                    myRef.child("Member1").child("Ps").child("Ps3").child("Sendtime").setValue(selyear+"/"+(selmonth+1)+"/"+selday);
                }

                Intent i=new Intent();
                i.setClass(getActivity(),com.example.im01.psmemory.FireUpload.Main.class);
                startActivity(i);


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
                    Log.e("S","請選擇");
                }

                else if(position==1){
                    myRef.child("Member1").child("Ps").child("Ps"+position).child("Forwho").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String forwho=String.valueOf(dataSnapshot.getValue());
                            who.setText(forwho);
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value

                        }
                    });
                    selectwho=1;
                }

                else if(position==2){
                    myRef.child("Member1").child("Ps").child("Ps"+position).child("Forwho").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String forwho=String.valueOf(dataSnapshot.getValue());
                            who.setText(forwho);
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value

                        }
                    });
                    selectwho=2;
                }
                else if(position==3){
                    myRef.child("Member1").child("Ps").child("Ps"+position).child("Forwho").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String forwho=String.valueOf(dataSnapshot.getValue());
                            who.setText(forwho);
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value

                        }
                    });
                    selectwho=3;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return  root;
    }
    //firebase storage
    public void uploadfile(){
        // Get the data from an ImageView as bytes
        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache();
        Bitmap bitmap = imageView.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        StorageReference storageRef = storage.getReferenceFromUrl("gs://<project-6390619862189429975.appspot.com>");
        StorageReference imagesRef = storageRef.child("images");
        StorageReference spaceRef = storageRef.child("images/space.jpg");

        String fileName = "space.jpg";
        spaceRef = imagesRef.child(fileName);

        UploadTask uploadTask = spaceRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(getActivity(),"上傳失敗",Toast.LENGTH_LONG).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();

            }
        });
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
            File auxFile;
            String realpath;
            ContentResolver cr = getActivity().getContentResolver();


           // InputStream is=ch.openInputStream(uri);
            try {
                //由抽象資料接口轉換圖檔路徑為Bitmap

                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
               // auxFile= new File(realpath);
                auxFile=new File(Environment.getExternalStorageDirectory(), "image.jpg");
                Log.e("Path",uri.getPath());

                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                Log.e("Exception", e.getMessage(),e);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onResume() {//Return to your app after user authorization
        super.onResume();

    }
    @Override
    public void onPause() {//Return to your app after user authorization
        super.onPause();
        main.onDestroy();
    }
}
