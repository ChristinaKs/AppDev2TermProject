package com.example.findmytherapist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class editTherapist extends AppCompatActivity {

    DBHelper db;
    EditText therapistEmailEdit, therapistFnameEdit, therapistLnameEdit, therapistGenderEdit, therapistSpecialitiesEdit;
    Button updateTherapist, deleteTherapist, returnToTherapistProfile;
    CheckBox phoneEdit, textEdit, zoomEdit, inPersonEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_therapist);

        therapistEmailEdit = findViewById(R.id.therapistEmailEdit);
        therapistFnameEdit = findViewById(R.id.therapistFnameEdit);
        therapistLnameEdit = findViewById(R.id.therapistLnameEdit);
        therapistGenderEdit = findViewById(R.id.genderTherapistEdit);
        therapistSpecialitiesEdit = findViewById(R.id.specialitiesTherapistEdit);
        updateTherapist = findViewById(R.id.updateTherapist);
        deleteTherapist = findViewById(R.id.deleteTherapist);
        returnToTherapistProfile = findViewById(R.id.returnTherapistProfile);
        phoneEdit = findViewById(R.id.phoneEdit);
        textEdit = findViewById(R.id.textEdit);
        zoomEdit = findViewById(R.id.zoomEdit);
        inPersonEdit = findViewById(R.id.inPersonEdit);

        String email = getIntent().getStringExtra("therapistEmail");
        String first = getIntent().getStringExtra("therapistFirstName");
        String last = getIntent().getStringExtra("therapistLastName");
        String gender = getIntent().getStringExtra("therapistGender");
        String specialities = getIntent().getStringExtra("therapistSpecialities");

        StringBuilder platforms = new StringBuilder();
        String therapistPlatform = "This therapist is available for";
        String zoom = " zoom";
        String phone = " phone";
        String text = " text";
        String inPerson = " in person";
        if(phoneEdit.isChecked()){
            therapistPlatform.equals(platforms.append(therapistPlatform).append(phone));
        }
        if(textEdit.isChecked()){
            therapistPlatform.equals(platforms.append(therapistPlatform).append(text));
        }
        if(zoomEdit.isChecked()){
            therapistPlatform.equals(platforms.append(therapistPlatform).append(zoom));
        }
        if(inPersonEdit.isChecked()){
            therapistPlatform.equals(platforms.append(therapistPlatform).append(inPerson));
        }

        therapistEmailEdit.setText(email);
        therapistFnameEdit.setText(first);
        therapistLnameEdit.setText(last);
        therapistGenderEdit.setText(gender);
        therapistSpecialitiesEdit.setText(specialities);

        updateTherapist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdated = db.updateTherapist(/*Need session ID?*/therapistFnameEdit.getText().toString(),
                        therapistEmailEdit.getText().toString(), therapistFnameEdit.getText().toString(),
                        therapistLnameEdit.getText().toString(), therapistGenderEdit.getText().toString(),
                        therapistPlatform, therapistSpecialitiesEdit.getText().toString());
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