package com.example.findmytherapist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class editTherapist extends AppCompatActivity {

    DBHelper db;
    EditText therapistEmailEdit, therapistFnameEdit, therapistLnameEdit, therapistGenderEdit;
    Button updateTherapist, deleteTherapist, returnToTherapistProfile;
    CheckBox phoneEdit, textEdit, zoomEdit, inPersonEdit;
    TextView addressTherapist;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_therapist);

        therapistEmailEdit = findViewById(R.id.therapistEmailEdit);
        therapistFnameEdit = findViewById(R.id.therapistFnameEdit);
        therapistLnameEdit = findViewById(R.id.therapistLnameEdit);
        therapistGenderEdit = findViewById(R.id.genderTherapistEdit);
        updateTherapist = findViewById(R.id.updateTherapist);
        deleteTherapist = findViewById(R.id.deleteTherapist);
        returnToTherapistProfile = findViewById(R.id.returnTherapistProfile);
        phoneEdit = findViewById(R.id.phoneEdit);
        textEdit = findViewById(R.id.textEdit);
        zoomEdit = findViewById(R.id.zoomEdit);
        inPersonEdit = findViewById(R.id.inPersonEdit);
        addressTherapist = findViewById(R.id.editTherapistAddress);

        String email = getIntent().getStringExtra("therapistEmail");
        String first = getIntent().getStringExtra("therapistFirstName");
        String last = getIntent().getStringExtra("therapistLastName");
        String gender = getIntent().getStringExtra("therapistGender");
        String address = getIntent().getStringExtra("therapistAddress");

        Integer phone;
        Integer text;
        Integer zoom;
        Integer person;
        //if checked it becomes 1 else 0 (1=true 0=false for the database)
        if(phoneEdit.isChecked()){
            phone = 1;
        }else{
            phone = 0;
        }
        if(textEdit.isChecked()){
            text = 1;
        }else{
            text = 0;
        }
        if(zoomEdit.isChecked()){
            zoom = 1;
        }else{
            zoom = 0;
        }
        if(inPersonEdit.isChecked()){
            person = 1;
        }else{
            person = 0;
        }

        therapistEmailEdit.setText(email);
        therapistFnameEdit.setText(first);
        therapistLnameEdit.setText(last);
        therapistGenderEdit.setText(gender);
        addressTherapist.setText(address);

        updateTherapist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //retrieving email of whoever is logged in
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                String email = sharedPreferences.getString(EMAIL,"");
                //retrieving id from email
                Integer id = db.getIdByEmailTherapist(email);
                String idToUse = id.toString();
                boolean isUpdated = db.updateTherapist(idToUse,therapistEmailEdit.getText().toString(),therapistFnameEdit.getText().toString(),
                        therapistLnameEdit.getText().toString(), therapistGenderEdit.getText().toString(),phone,text,zoom,person,addressTherapist.getText().toString());
                if(isUpdated = true){
                    Toast.makeText(editTherapist.this, "Profile is updated", Toast.LENGTH_SHORT).show();
                }

            }
        });

        deleteTherapist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteTherapist(/*Need session ID?*/therapistFnameEdit.getText().toString());
                Intent login = new Intent(editTherapist.this, MainActivity.class);
                startActivity(login);
            }
        });

        returnToTherapistProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnToTherapist = new Intent(editTherapist.this, TherapistProfile.class);
                startActivity(returnToTherapist);
            }
        });
    }
}