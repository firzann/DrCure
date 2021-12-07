package com.example.drcure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class YourHealth extends AppCompatActivity {

    //inisialisasi text view nilai ambil dari id
    private TextView nilai_roomtemp;
    private TextView nilai_roomhumidity;
    private TextView nilai_humantemp;
    private TextView nilai_hbeat;
    private TextView nilai_oxygen;

    //buat reference untuk firebase (koneksi server / host)
    private Firebase mRef, mRef2, mRef3, mRef4, mRef5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_health);

        //baca komponen nilai(textview) tampil android
        nilai_roomtemp = (TextView)findViewById(R.id.nilai_roomtemp);
        nilai_roomhumidity = (TextView)findViewById(R.id.nilai_roomhumid);
        nilai_humantemp = (TextView)findViewById(R.id.nilai_humantemp);
        nilai_hbeat = (TextView)findViewById(R.id.nilai_hbeat);
        nilai_oxygen = (TextView)findViewById(R.id.nilai_oxygen);

        //buka koneksi ke data/value firebase database
        mRef = new Firebase("https://healthmonitoring-f5ba0-default-rtdb.firebaseio.com/Sensor/roomtemp");
        mRef2 = new Firebase("https://healthmonitoring-f5ba0-default-rtdb.firebaseio.com/Sensor/roomhumid");
        mRef3 = new Firebase("https://healthmonitoring-f5ba0-default-rtdb.firebaseio.com/Sensor/humantemp");
        mRef4 = new Firebase("https://healthmonitoring-f5ba0-default-rtdb.firebaseio.com/Sensor/hbeat");
        mRef5 = new Firebase("https://healthmonitoring-f5ba0-default-rtdb.firebaseio.com/Sensor/oxygen");

        //proses realtime
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Ambil nilai field view yg ada di firebase
                String roomtemp = dataSnapshot.getValue(String.class);
                //tampilkan di komponen nilai di android
                nilai_roomtemp.setText(roomtemp);
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
        mRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String roomhumid = dataSnapshot.getValue(String.class);
                nilai_roomhumidity.setText(roomhumid);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        mRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String humantemp = dataSnapshot.getValue(String.class);
                nilai_humantemp.setText(humantemp);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        mRef4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String hbeat = dataSnapshot.getValue(String.class);
                nilai_hbeat.setText(hbeat);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        mRef5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String oxygen = dataSnapshot.getValue(String.class);
                nilai_oxygen.setText(oxygen);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        //Button Next Page
        Button btn_devices = findViewById(R.id.btn_find);
        btn_devices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DevicesActivity.class);
                startActivity(intent);
            }
        });

    }
}