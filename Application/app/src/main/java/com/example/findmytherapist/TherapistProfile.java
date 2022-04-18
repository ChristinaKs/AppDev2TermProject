package com.example.findmytherapist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class TherapistProfile extends AppCompatActivity {

    ImageButton therapistProfile, myClients, therapistAppointments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapist_profile);

        therapistProfile = (ImageButton) findViewById(R.id.therapistProfileIM);
        myClients = (ImageButton) findViewById(R.id.myClientsIM);
        therapistAppointments = (ImageButton) findViewById(R.id.therapistAppointmentIM);

        therapistProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent clientProfileIntent = new Intent(TherapistProfile.this, TherapistProfile.class);
                startActivity(clientProfileIntent);
            }
        });

        myClients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchTherapistIntent = new Intent(TherapistProfile.this, MyClients.class);
                startActivity(searchTherapistIntent);
            }
        });

        therapistAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent clientAppointmentsIntent = new Intent(TherapistProfile.this, TherapistAppointments.class);
                startActivity(clientAppointmentsIntent);
            }
        });
    }
}