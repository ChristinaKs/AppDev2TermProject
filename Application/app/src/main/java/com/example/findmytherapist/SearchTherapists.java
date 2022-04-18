package com.example.findmytherapist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SearchTherapists extends AppCompatActivity {

    ImageButton clientProfile, searchTherapist, clientAppointments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_therapists);

        clientProfile = (ImageButton) findViewById(R.id.clientProfileIM);
        searchTherapist = (ImageButton) findViewById(R.id.searchTherapistIM);
        clientAppointments = (ImageButton) findViewById(R.id.clientAppointmentIM);

        clientProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent clientProfileIntent3 = new Intent(SearchTherapists.this, ClientProfile.class);
                startActivity(clientProfileIntent3);
            }
        });

        searchTherapist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchTherapistIntent3 = new Intent(SearchTherapists.this, SearchTherapists.class);
                startActivity(searchTherapistIntent3);
            }
        });

        clientAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent clientAppointmentsIntent3 = new Intent(SearchTherapists.this, ClientAppointments.class);
                startActivity(clientAppointmentsIntent3);
            }
        });
    }
}