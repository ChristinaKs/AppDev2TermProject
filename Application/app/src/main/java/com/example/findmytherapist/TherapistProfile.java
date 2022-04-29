package com.example.findmytherapist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

public class TherapistProfile extends AppCompatActivity {

    ImageButton therapistProfile, myClients, therapistAppointments;
    Button editTherapistBtn, signOutTherapistBtn, viewTherapistAvailabilitiesBtn;
    EditText therapistEmail, therapistFname, therapistLname, therapistGender;
    CheckBox bruh;
    TextView address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapist_profile);

        therapistProfile = (ImageButton) findViewById(R.id.therapistProfileIM);
        myClients = (ImageButton) findViewById(R.id.myClientsIM);
        therapistAppointments = (ImageButton) findViewById(R.id.therapistAppointmentIM);
        editTherapistBtn = findViewById(R.id.editTherapistProfileButton);
        signOutTherapistBtn = findViewById(R.id.signOutTherapistButton);
        viewTherapistAvailabilitiesBtn = findViewById(R.id.viewTherapistAvailabilitiesBtn);
        therapistEmail = findViewById(R.id.therapistEmailDisplay);
        therapistFname = findViewById(R.id.therapistFnameDisplay);
        therapistLname = findViewById(R.id.therapistLnameDisplay);
        therapistGender = findViewById(R.id.genderTherapistDisplay);
        address = findViewById(R.id.therapistAddress);

        editTherapistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editTherapistIntent = new Intent(TherapistProfile.this, editClient.class);
                getIntent().putExtra("therapistEmail", therapistEmail.getText().toString());
                getIntent().putExtra("therapistFname", therapistFname.getText().toString());
                getIntent().putExtra("therapistLname", therapistLname.getText().toString());
                getIntent().putExtra("therapistGender", therapistGender.getText().toString());
                getIntent().putExtra("therapistAddress", address.getText().toString());
                startActivity(editTherapistIntent);
            }
        });

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