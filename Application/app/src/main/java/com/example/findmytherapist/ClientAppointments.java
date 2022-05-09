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

public class ClientAppointments extends AppCompatActivity {

    ImageButton clientProfile, searchTherapist, clientAppointments;
    Integer userId;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_appointments);

        db = new DBHelper(ClientAppointments.this);

        clientProfile = (ImageButton) findViewById(R.id.clientProfileIM);
        searchTherapist = (ImageButton) findViewById(R.id.searchTherapistIM);
        clientAppointments = (ImageButton) findViewById(R.id.clientAppointmentIM);

        //id of client
        Intent intent = getIntent();
        userId = intent.getIntExtra("USER_ID",-1);


//        ArrayList<String> TherapistLicense = new ArrayList<>();
//        ArrayList<String> AppointmentTime = new ArrayList<>();
//
//        Cursor result = db.getTherapistData();
//        while(result.moveToNext()){
//            //license in db is integer so we're turning it into a string
//            Integer license = result.getInt(1);
//            String therapistlicense = license.toString();
//            TherapistLicense.add(therapistlicense);
//
//            //getting rest of info
//            AppointmentTime.add(result.getString(3));
//        }
//
//        //display it all in the recyclerview
//        RecyclerView adapter = findViewById(R.id.clientAppoitmentRV);
//        ClientAppointmentAdapter clientAppointmentAdapter = new ClientAppointmentAdapter(TherapistLicense, AppointmentTime, this);
//        adapter.setAdapter(clientAppointmentAdapter);
//        adapter.setLayoutManager(new LinearLayoutManager(this));


        clientProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent clientProfileIntent = new Intent(ClientAppointments.this, ClientProfile.class);
                clientProfileIntent.putExtra("USER_ID",userId);
                startActivity(clientProfileIntent);
            }
        });

        searchTherapist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchTherapistIntent = new Intent(ClientAppointments.this, SearchTherapists.class);
                searchTherapistIntent.putExtra("USER_ID",userId);
                startActivity(searchTherapistIntent);
            }
        });

        clientAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent clientAppointmentsIntent = new Intent(ClientAppointments.this, ClientAppointments.class);
                clientAppointmentsIntent.putExtra("USER_ID",userId);
                startActivity(clientAppointmentsIntent);
            }
        });

    }
}