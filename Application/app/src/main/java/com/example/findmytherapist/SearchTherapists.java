package com.example.findmytherapist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;


import java.lang.reflect.Array;
import java.util.ArrayList;

public class SearchTherapists extends AppCompatActivity {

    ImageButton clientProfile, searchTherapist, clientAppointments;
    //TherapistAdapter therapistSearch;
    Integer userId;
    DBHelper db = new DBHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_therapists);

        clientProfile = (ImageButton) findViewById(R.id.clientProfileIM);
        searchTherapist = (ImageButton) findViewById(R.id.searchTherapistIM);
        clientAppointments = (ImageButton) findViewById(R.id.clientAppointmentIM);

        Intent intent = getIntent();
        userId = intent.getIntExtra("USER_ID",-1);

        ArrayList<String> fname = new ArrayList<>();
        ArrayList<String> lname = new ArrayList<>();
        ArrayList<String> gender = new ArrayList<>();
        ArrayList<String> platforms = new ArrayList<>();
        ArrayList<String> addresses = new ArrayList<>();
        //get fname,lname and gender
        Cursor result = db.getTherapistData();
        while(result.moveToNext()){
            String platform = "Platforms:";
            fname.add(result.getString(3));
            lname.add(result.getString(4));
            gender.add(result.getString(5));
            //if phone equals 1-> add to string string+= " phone" and same with other platforms
            if(result.getInt(6)== 1){
                platform +=" phone";
            }
            if(result.getInt(7)== 1){
                platform +=" text";
            }
            if(result.getInt(8)== 1){
                platform +=" zoom";
            }
            if(result.getInt(9)== 1){
                platform +=" inperson";
            }
            platforms.add(platform);
            addresses.add(result.getString(10));
        }
        RecyclerView adapter = findViewById(R.id.searchRV);
        TherapistAdapter therapistAdapter = new TherapistAdapter(fname,lname,gender,platforms,addresses,this);
        adapter.setAdapter(therapistAdapter);
        adapter.setLayoutManager(new LinearLayoutManager(this));

        //nav bar
        clientProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent clientProfileIntent3 = new Intent(SearchTherapists.this, ClientProfile.class);
                clientProfileIntent3.putExtra("USER_ID",userId);
                startActivity(clientProfileIntent3);
            }
        });

        searchTherapist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchTherapistIntent3 = new Intent(SearchTherapists.this, SearchTherapists.class);
                searchTherapistIntent3.putExtra("USER_ID",userId);
                startActivity(searchTherapistIntent3);
            }
        });

        clientAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent clientAppointmentsIntent3 = new Intent(SearchTherapists.this, ClientAppointments.class);
                clientAppointmentsIntent3.putExtra("USER_ID",userId);
                startActivity(clientAppointmentsIntent3);
            }
        });
    }
}