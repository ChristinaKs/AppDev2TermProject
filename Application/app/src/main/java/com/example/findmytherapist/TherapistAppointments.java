package com.example.findmytherapist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class TherapistAppointments extends AppCompatActivity {

    ImageButton therapistProfile, myClients, therapistAppointments;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapist_appointments);

        therapistProfile = (ImageButton) findViewById(R.id.therapistProfileIM);
        myClients = (ImageButton) findViewById(R.id.myClientsIM);
        therapistAppointments = (ImageButton) findViewById(R.id.therapistAppointmentIM);


        Integer id = getIntent().getIntExtra("USER_ID",-1);
        String idToUse = id.toString();

//        ArrayList<String> ClientID = new ArrayList<>();
//        ArrayList<String> AppointmentTime = new ArrayList<>();
//
//        db = new DBHelper(TherapistAppointments.this);
//        Cursor result = db.getTherapistAppointments(idToUse);
//        while(result.moveToNext()){
//            //id in db is integer so we're turning it into a string
//            Integer ids = result.getInt(0);
//            String clientId = ids.toString();
//            ClientID.add(clientId);
//
//            //getting rest of info
//            AppointmentTime.add(result.getString(1));
//        }

        //display it all in the recyclerview
//        RecyclerView adapter = findViewById(R.id.therapistAppointmentsRV);
//        TherapistAppointmentAdapter therapistAppointmentAdapter = new TherapistAppointmentAdapter(ClientID, AppointmentTime, this);
//        adapter.setAdapter(therapistAppointmentAdapter);
//        adapter.setLayoutManager(new LinearLayoutManager(this));

        therapistProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent clientProfileIntent = new Intent(TherapistAppointments.this, TherapistProfile.class);
                clientProfileIntent.putExtra("USER_ID",id);
                startActivity(clientProfileIntent);
            }
        });

        myClients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchTherapistIntent = new Intent(TherapistAppointments.this, MyClients.class);
                searchTherapistIntent.putExtra("USER_ID",id);
                startActivity(searchTherapistIntent);
            }
        });

        therapistAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent clientAppointmentsIntent = new Intent(TherapistAppointments.this, TherapistAppointments.class);
                clientAppointmentsIntent.putExtra("USER_ID",id);
                startActivity(clientAppointmentsIntent);
            }
        });
    }
}