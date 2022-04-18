package com.example.findmytherapist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ClientProfile extends AppCompatActivity {

    ImageButton clientProfile, searchTherapist, clientAppointments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_profile);

        clientProfile = (ImageButton) findViewById(R.id.clientProfileIM);
        searchTherapist = (ImageButton) findViewById(R.id.searchTherapistIM);
        clientAppointments = (ImageButton) findViewById(R.id.clientAppointmentIM);

        clientProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent clientProfileIntent2 = new Intent(ClientProfile.this, ClientProfile.class);
                startActivity(clientProfileIntent2);
            }
        });

        searchTherapist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchTherapistIntent2 = new Intent(ClientProfile.this, SearchTherapists.class);
                startActivity(searchTherapistIntent2);
            }
        });

        clientAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent clientAppointmentsIntent2 = new Intent(ClientProfile.this, ClientAppointments.class);
                startActivity(clientAppointmentsIntent2);
            }
        });
    }
}