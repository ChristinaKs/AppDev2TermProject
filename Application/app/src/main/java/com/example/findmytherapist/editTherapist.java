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
    CheckBox phoneEdit, textEdit, zoomEdit, inPersonEdit,femaleTherapist,maleTherapist;
    TextView addressTherapist;
    String genderUpdate;
    /*public static final String SHARED_PREFS = "sharedPrefs";
    public static final String EMAIL = "email";*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_therapist);

        db = new DBHelper(editTherapist.this);

        therapistEmailEdit = findViewById(R.id.therapistEmailEdit);
        therapistFnameEdit = findViewById(R.id.therapistFnameEdit);
        therapistLnameEdit = findViewById(R.id.therapistLnameEdit);
        femaleTherapist = findViewById(R.id.therapistFemaleEdit);
        maleTherapist = findViewById(R.id.therapistMaleEdit);
        //therapistGenderEdit = findViewById(R.id.genderTherapistEdit);
        updateTherapist = findViewById(R.id.updateTherapist);
        deleteTherapist = findViewById(R.id.deleteTherapist);
        returnToTherapistProfile = findViewById(R.id.returnTherapistProfile);
        phoneEdit = findViewById(R.id.phoneEdit);
        textEdit = findViewById(R.id.textEdit);
        zoomEdit = findViewById(R.id.zoomEdit);
        inPersonEdit = findViewById(R.id.inPersonEdit);
        addressTherapist = findViewById(R.id.editTherapistAddress);

        //getting all the intents
        Integer id = getIntent().getIntExtra("USER_ID",-1);
        String email = getIntent().getStringExtra("therapistEmail");
        String first = getIntent().getStringExtra("therapistFname");
        String last = getIntent().getStringExtra("therapistLname");
        String gender = getIntent().getStringExtra("therapistGender");
        Integer phone = getIntent().getIntExtra("therapistPhone",-1);
        Integer text = getIntent().getIntExtra("therapistText",-1);
        Integer zoom = getIntent().getIntExtra("therapistZoom",-1);
        Integer person = getIntent().getIntExtra("therapistPerson",-1);
        String address = getIntent().getStringExtra("therapistAddress");

        String idToUse = id.toString();
        //displaying info
        therapistEmailEdit.setText(email);
        therapistFnameEdit.setText(first);
        therapistLnameEdit.setText(last);
        //therapistGenderEdit.setText(gender);
        addressTherapist.setText(address);
        if(phone == 1){
            phoneEdit.setChecked(true);
        }
        if(text == 1){
            textEdit.setChecked(true);
        }
        if(zoom == 1){
            zoomEdit.setChecked(true);
        }
        if(person == 1){
            inPersonEdit.setChecked(true);
        }
        /*if(gender.equals("female")){
            femaleTherapist.setChecked(true);
        }else{
            maleTherapist.setChecked(true);
        }*/

        updateTherapist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //retrieving email of whoever is logged in
                /*SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                String email = sharedPreferences.getString(EMAIL,"");
                //retrieving id from email
                Integer id = db.getIdByEmailTherapist(email);
                String idToUse = id.toString();*/

                //getting info of what therapist updated in platform
                Integer updatePhone;
                Integer updateText;
                Integer updateZoom;
                Integer updatePerson;
                //if checked it becomes 1 else 0 (1=true 0=false for the database)
                if(phoneEdit.isChecked()){
                    updatePhone = 1;
                }else{
                    updatePhone = 0;
                }
                if(textEdit.isChecked()){
                    updateText = 1;
                }else{
                    updateText = 0;
                }
                if(zoomEdit.isChecked()){
                    updateZoom = 1;
                }else{
                    updateZoom = 0;
                }
                if(inPersonEdit.isChecked()){
                    updatePerson = 1;
                }else{
                    updatePerson = 0;
                }

                boolean isUpdated;
                //if therapist picked both genders, display message
                if(femaleTherapist.isChecked() && maleTherapist.isChecked()){
                    isUpdated = false;
                    Toast.makeText(editTherapist.this, "Please select one gender", Toast.LENGTH_SHORT).show();
                //else check what is actually picked and make isupdated true
                }else if(femaleTherapist.isChecked()){
                    genderUpdate = "female";
                    isUpdated = true;
                }else{
                    genderUpdate = "male";
                    isUpdated = true;
                }
                //if it's true then actually update it
                if(isUpdated == true) {
                    isUpdated = db.updateTherapist(idToUse, therapistEmailEdit.getText().toString(), therapistFnameEdit.getText().toString(),
                            therapistLnameEdit.getText().toString(), genderUpdate, updatePhone, updateText, updateZoom, updatePerson, addressTherapist.getText().toString());
                }
                //if update went through display message
                if(isUpdated == true){
                    Toast.makeText(editTherapist.this, "Profile is updated", Toast.LENGTH_SHORT).show();
                }

            }
        });

        deleteTherapist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteTherapist(idToUse);
                Intent login = new Intent(editTherapist.this, MainActivity.class);
                startActivity(login);
            }
        });

        returnToTherapistProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnToTherapist = new Intent(editTherapist.this, TherapistProfile.class);
                returnToTherapist.putExtra("USER_ID",id);
                startActivity(returnToTherapist);
            }
        });
    }
}