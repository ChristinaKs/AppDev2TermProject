package com.example.findmytherapist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class ClientProfile extends AppCompatActivity {

    ImageButton clientProfile, searchTherapist, clientAppointments;
    Button editClientBtn, signOutBtn;
    EditText clientEmail, clientFname, clientLname, clientAge, clientGender;
    TextView clientAddy;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String EMAIL = "email";
    DBHelper db = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_profile);

        clientProfile = (ImageButton) findViewById(R.id.clientProfileIM);
        searchTherapist = (ImageButton) findViewById(R.id.searchTherapistIM);
        clientAppointments = (ImageButton) findViewById(R.id.clientAppointmentIM);
        editClientBtn = findViewById(R.id.editClientProfileButton);
        signOutBtn = findViewById(R.id.signOutClientButton);
        clientEmail = findViewById(R.id.clientEmailDisplay);
        clientFname = findViewById(R.id.clientFnameDisplay);
        clientLname = findViewById(R.id.clientLnameDisplay);
        clientAge = findViewById(R.id.ageClientDisplay);
        clientGender = findViewById(R.id.genderClientDisplay);
        clientAddy = findViewById(R.id.clientAddress);


        //display info of client

        //retrieving email of whoever is logged in
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String email = sharedPreferences.getString(EMAIL,"");
        //retrieving id from email
        Integer id = db.getIdByEmailClient(email);
        String idToUse = id.toString();
        Cursor result =  db.getClientDataById(idToUse);

        editClientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editClientIntent = new Intent(ClientProfile.this, editClient.class);
                getIntent().putExtra("clientEmail", clientEmail.getText().toString());
                getIntent().putExtra("clientFirstName", clientFname.getText().toString());
                getIntent().putExtra("clientLastName", clientLname.getText().toString());
                getIntent().putExtra("clientAge", clientAge.getText().toString());
                getIntent().putExtra("clientGender", clientGender.getText().toString());
                getIntent().putExtra("clientAddress", clientAddy.getText().toString());
                startActivity(editClientIntent);
            }
        });

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