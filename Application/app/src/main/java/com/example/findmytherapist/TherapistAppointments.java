package com.example.findmytherapist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class TherapistAppointments extends AppCompatActivity {

    ImageButton therapistProfile, myClients, therapistAppointments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapist_appointments);

        therapistProfile = (ImageButton) findViewById(R.id.therapistProfileIM);
        myClients = (ImageButton) findViewById(R.id.myClientsIM);
        therapistAppointments = (ImageButton) findViewById(R.id.therapistAppointmentIM);


        Integer id = getIntent().getIntExtra("USER_ID",-1);
        String idToUse = id.toString();

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