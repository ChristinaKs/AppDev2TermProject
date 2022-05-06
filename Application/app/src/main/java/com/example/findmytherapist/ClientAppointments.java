package com.example.findmytherapist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ClientAppointments extends AppCompatActivity {

    ImageButton clientProfile, searchTherapist, clientAppointments;
    Integer userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_appointments);

        clientProfile = (ImageButton) findViewById(R.id.clientProfileIM);
        searchTherapist = (ImageButton) findViewById(R.id.searchTherapistIM);
        clientAppointments = (ImageButton) findViewById(R.id.clientAppointmentIM);

        Intent intent = getIntent();
        userId = intent.getIntExtra("USER_ID",-1);

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