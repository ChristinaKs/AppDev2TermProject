package com.example.findmytherapist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
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
    CheckBox therapistPhone, therapistText, therapistZoom, therapistPerson;
    TextView therapistAddy;
    DBHelper db = new DBHelper(this);

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
        therapistPhone = findViewById(R.id.therapistPhone);
        therapistPhone.setEnabled(false);
        therapistText = findViewById(R.id.textCB);
        therapistText.setEnabled(false);
        therapistZoom = findViewById(R.id.zoomCB);
        therapistZoom.setEnabled(false);
        therapistPerson = findViewById(R.id.inPersonCB);
        therapistPerson.setEnabled(false);
        therapistAddy = findViewById(R.id.therapistAddress);

        //displaying info of therapist
        Intent therapistProfileIntent = getIntent();
        Integer userId = therapistProfileIntent.getIntExtra("USER_ID",-1);
        String idToUse = userId.toString();
        //displaying therapist info in their profile
        String email = " ";
        String firstName =" ";
        String lastname = " ";
        String gender = " ";
        Integer phone = 0;
        Integer text = 0;
        Integer zoom = 0;
        Integer person = 0;
        String address = " ";
        Cursor cursor = db.getTherapistById(idToUse);
        while(cursor.moveToNext()){
            email = cursor.getString(1);
            firstName = cursor.getString(3);
            lastname = cursor.getString(4);
            gender = cursor.getString(5);
            phone = cursor.getInt(6);
            text = cursor.getInt(7);
            zoom = cursor.getInt(8);
            person = cursor.getInt(9);
            address = cursor.getString(10);
        }
        therapistEmail.setText(email);
        therapistFname.setText(firstName);
        therapistLname.setText(lastname);
        therapistGender.setText(gender);
        if(phone == 1){
            therapistPhone.setChecked(true);
        }
        if(text == 1){
            therapistText.setChecked(true);
        }
        if(zoom == 1){
            therapistZoom.setChecked(true);
        }
        if(person == 1){
            therapistPerson.setChecked(true);
        }
        therapistAddy.setText(address);

        editTherapistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editTherapistIntent = new Intent(TherapistProfile.this, editTherapist.class);

                editTherapistIntent.putExtra("therapistEmail", therapistEmail.getText().toString());
                editTherapistIntent.putExtra("therapistFname", therapistFname.getText().toString());
                editTherapistIntent.putExtra("therapistLname", therapistLname.getText().toString());
                editTherapistIntent.putExtra("therapistGender", therapistGender.getText().toString());
                // PASS CHECKBOX VALUE -- or do they have to re-check what they want?
//                if(therapistPhone.isChecked()){
//                    getIntent().putExtra()
//                }
                //if its checked pass value of 1 else pass value of 0
                if(therapistPhone.isChecked()){
                    editTherapistIntent.putExtra("therapistPhone", 1);
                }else{
                    editTherapistIntent.putExtra("therapistPhone", 0);
                }
                if(therapistText.isChecked()){
                    editTherapistIntent.putExtra("therapistText", 1);
                }else{
                    editTherapistIntent.putExtra("therapistText", 0);
                }
                if(therapistZoom.isChecked()){
                    editTherapistIntent.putExtra("therapistZoom", 1);
                }else{
                    editTherapistIntent.putExtra("therapistZoom", 0);
                }
                if(therapistPerson.isChecked()){
                    editTherapistIntent.putExtra("therapistPerson", 1);
                }else{
                    editTherapistIntent.putExtra("therapistPerson", 0);
                }
                editTherapistIntent.putExtra("therapistAddress", therapistAddy.getText().toString());
                editTherapistIntent.putExtra("USER_ID",userId);
                startActivity(editTherapistIntent);
            }
        });

        viewTherapistAvailabilitiesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewAvailabilities = new Intent(TherapistProfile.this, calendarTherapist.class);
                viewAvailabilities.putExtra("USER_ID",userId);
                startActivity(viewAvailabilities);
            }
        });

        signOutTherapistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent therapistLogoutIntent = new Intent (TherapistProfile.this, MainActivity.class);
                startActivity(therapistLogoutIntent);
            }
        });

        therapistProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent clientProfileIntent = new Intent(TherapistProfile.this, TherapistProfile.class);
                clientProfileIntent.putExtra("USER_ID",userId);
                startActivity(clientProfileIntent);
            }
        });

        myClients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchTherapistIntent = new Intent(TherapistProfile.this, MyClients.class);
                searchTherapistIntent.putExtra("USER_ID",userId);
                startActivity(searchTherapistIntent);
            }
        });

        therapistAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent clientAppointmentsIntent = new Intent(TherapistProfile.this, TherapistAppointments.class);
                clientAppointmentsIntent.putExtra("USER_ID",userId);
                startActivity(clientAppointmentsIntent);
            }
        });
    }
}