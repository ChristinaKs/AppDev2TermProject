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
    Button editClientBtn, signOutClientBtn;
    EditText clientEmail, clientFname, clientLname, clientAge, clientGender;
    TextView clientAddy;
//    public static final String SHARED_PREFS = "sharedPrefs";
//    public static final String EMAIL = "email";
    DBHelper db = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_profile);

        clientProfile = (ImageButton) findViewById(R.id.clientProfileIM);
        searchTherapist = (ImageButton) findViewById(R.id.searchTherapistIM);
        clientAppointments = (ImageButton) findViewById(R.id.clientAppointmentIM);
        editClientBtn = findViewById(R.id.editClientProfileButton);
        signOutClientBtn = findViewById(R.id.signOutClientButton);
        clientEmail = findViewById(R.id.clientEmailDisplay);
        clientFname = findViewById(R.id.clientFnameDisplay);
        clientLname = findViewById(R.id.clientLnameDisplay);
        clientAge = findViewById(R.id.ageClientDisplay);
        clientGender = findViewById(R.id.genderClientDisplay);
        clientAddy = findViewById(R.id.clientAddress);



        //display info of client
        Intent clientProfileIntent = getIntent();
        Integer userId = clientProfileIntent.getIntExtra("USER_ID",-1);
        //retrieving email of whoever is logged in
//        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
//        String email = sharedPreferences.getString(EMAIL,"");
//        //retrieving id from email
//        Integer id = db.getIdByEmailClient(email);
//        String idToUse = id.toString();
//        Cursor result =  db.getClientDataById(idToUse);

        editClientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editClientIntent = new Intent(ClientProfile.this, editClient.class);
                editClientIntent.putExtra("clientEmail", clientEmail.getText().toString());
                editClientIntent.putExtra("clientFirstName", clientFname.getText().toString());
                editClientIntent.putExtra("clientLastName", clientLname.getText().toString());
                editClientIntent.putExtra("clientAge", clientAge.getText().toString());
                editClientIntent.putExtra("clientGender", clientGender.getText().toString());
                editClientIntent.putExtra("clientAddress", clientAddy.getText().toString());
                editClientIntent.putExtra("USER_ID",userId);

                startActivity(editClientIntent);
            }
        });

        signOutClientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signOutClientIntent = new Intent(ClientProfile.this, MainActivity.class);
                startActivity(signOutClientIntent);
            }
        });

        clientProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent clientProfileIntent2 = new Intent(ClientProfile.this, ClientProfile.class);
                clientProfileIntent2.putExtra("USER_ID",userId);
                startActivity(clientProfileIntent2);
            }
        });

        searchTherapist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchTherapistIntent2 = new Intent(ClientProfile.this, SearchTherapists.class);
                searchTherapistIntent2.putExtra("USER_ID",userId);
                startActivity(searchTherapistIntent2);
            }
        });

        clientAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent clientAppointmentsIntent2 = new Intent(ClientProfile.this, ClientAppointments.class);
                clientAppointmentsIntent2.putExtra("USER_ID",userId);
                startActivity(clientAppointmentsIntent2);
            }
        });
    }
}