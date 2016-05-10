package com.example.im01.psmemory;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;


import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.session.AppKeyPair;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.io.File;
import java.io.FileInputStream;

public class MainActivity extends ActionBarActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Firebase myFirebaseRef ;

    TextView membername,memberemail;
    FragmentTabHost tabHost;
    Activity Main;
    Member member=new Member();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        tabHost= (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        // Here, thisActivity is the current activity
        Main=this;        //1
        tabHost.addTab(tabHost.newTabSpec("PS")
                        .setIndicator("PS"),
                Ps_fragment.class,
                null);
        //2
        tabHost.addTab(tabHost.newTabSpec("Memory")
                        .setIndicator("Memory"),
                Memory_frag.class,
                null);
        //firebase start
        Firebase.setAndroidContext(this);
       // change=(Button)findViewById(R.id.button);
        //name=(TextView)findViewById(R.id.textView2);
        myFirebaseRef = new Firebase("https://sweltering-torch-4496.firebaseio.com/");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if(navigationView.getHeaderCount() > 0) {
            View header = navigationView.getHeaderView(0);
            membername=(TextView)header.findViewById(R.id.membername);
            memberemail=(TextView) header.findViewById(R.id.memberemail);
            membername.setText(member.accanswer);
            memberemail.setText(member.email);
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    protected void onResume() {
        int id = getIntent().getIntExtra("id", 0);

        if (id == 1 ) {
            tabHost.setCurrentTab(1);

        }
        super.onResume();
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.ps) {
            Intent i=new Intent();
            i.setClass(MainActivity.this,Pslist.class);
            startActivity(i);
        } else if (id == R.id.member) {
            Intent i=new Intent();
            i.setClass(MainActivity.this,MenberIn.class);
            startActivity(i);
        } else if (id == R.id.friendlist) {
            Intent i=new Intent();
            i.setClass(MainActivity.this,Friend.class);
            startActivity(i);
        } else if (id == R.id.logout) {

            Intent i=new Intent();
            i.setClass(MainActivity.this,Login.class);
            startActivity(i);
            Main.finish();
        } else if (id == R.id.introduction) {

        } else if (id == R.id.feedback) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
