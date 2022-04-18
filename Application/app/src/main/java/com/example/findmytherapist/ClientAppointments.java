package com.example.findmytherapist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ClientAppointments extends AppCompatActivity {

    ImageButton clientProfile, searchTherapist, clientAppointments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_appointments);

        clientProfile = (ImageButton) findViewById(R.id.clientProfileIM);
        searchTherapist = (ImageButton) findViewById(R.id.searchTherapistIM);
        clientAppointments = (ImageButton) findViewById(R.id.clientAppointmentIM);

        clientProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent clientProfileIntent = new Intent(ClientAppointments.this, ClientProfile.class);
                startActivity(clientProfileIntent);
            }
        });

        searchTherapist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchTherapistIntent = new Intent(ClientAppointments.this, SearchTherapists.class);
                startActivity(searchTherapistIntent);
            }
        });

        clientAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent clientAppointmentsIntent = new Intent(ClientAppointments.this, ClientAppointments.class);
                startActivity(clientAppointmentsIntent);
            }
        });

    }
}