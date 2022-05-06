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

public class editClient extends AppCompatActivity {

    DBHelper db;
    EditText clientEmailEdit, clientFnameEdit, clientLnameEdit, clientAgeEdit, clientAddressEdit;
    Button updateClient, deleteClient, returnToClientProfile;
    CheckBox clientFemale, clientMale;
//    public static final String SHARED_PREFS = "sharedPrefs";
//    public static final String EMAIL = "email";
    String genderUpdate = " ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_client);

        db = new DBHelper(editClient.this);

        clientEmailEdit = findViewById(R.id.clientEmailEdit);
        clientFnameEdit = findViewById(R.id.clientFnameEdit);
        clientLnameEdit = findViewById(R.id.clientLnameEdit);
        clientAgeEdit = findViewById(R.id.ageClientEdit);
        clientFemale = findViewById(R.id.editClientFemale);
        clientMale = findViewById(R.id.editClientMale);
        //clientGenderEdit = findViewById(R.id.genderClientEdit);
        updateClient = findViewById(R.id.updateClient);
        deleteClient = findViewById(R.id.deleteClient);
        returnToClientProfile = findViewById(R.id.returnClientProfile);
        clientAddressEdit = findViewById(R.id.editClientAddress);

        String email = getIntent().getStringExtra("clientEmail");
        String first = getIntent().getStringExtra("clientFirstName");
        String last = getIntent().getStringExtra("clientLastName");
        String age = getIntent().getStringExtra("clientAge");
        String gender = getIntent().getStringExtra("clientGender");
        String address = getIntent().getStringExtra("clientAddress");
        Integer clientId = getIntent().getIntExtra("USER_ID",-1);
        String idToUse = clientId.toString();

        //displaying info
        clientEmailEdit.setText(email);
        clientFnameEdit.setText(first);
        clientLnameEdit.setText(last);
        clientAgeEdit.setText(age);
        //clientGenderEdit.setText(gender);
        clientAddressEdit.setText(address);
        if(gender.equals("female")){
            clientFemale.setChecked(true);
        }else{
            clientMale.setChecked(true);
        }

        //check if client changed gender
        //useless
        /*if(clientFemale.isChecked()){
            genderUpdate = "female";
        }else if (clientMale.isChecked()){
            genderUpdate = "male";
        }else{
            genderUpdate = " ";
        }*/

        updateClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                //retrieving email of whoever is logged in
//                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
//                String email = sharedPreferences.getString(EMAIL,"");
//                //retrieving id from email
//                Integer id = db.getIdByEmailClient(email);
//                String idToUse = id.toString();
                boolean isUpdated;
                if(clientFemale.isChecked() && clientMale.isChecked()){
                    isUpdated = false;
                    Toast.makeText(editClient.this, "Please select one gender", Toast.LENGTH_SHORT).show();
                }else if(clientFemale.isChecked()){
                    genderUpdate = "female";
                    isUpdated = true;
                }else{
                    genderUpdate = "male";
                    isUpdated = true;
                }
                if(isUpdated == true){
                    isUpdated = db.updateClient(idToUse,clientEmailEdit.getText().toString(),clientFnameEdit.getText().toString(),
                            clientLnameEdit.getText().toString(), genderUpdate,
                            clientAgeEdit.getText().toString(),clientAddressEdit.getText().toString());
                }
                if(isUpdated == true){
                    Toast.makeText(editClient.this, "Profile is updated", Toast.LENGTH_SHORT).show();
                }

            }
        });

        deleteClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                //retrieving email of whoever is logged in
//                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
//                String email = sharedPreferences.getString(EMAIL,"");
//                //retrieving id from email
//                Integer id = db.getIdByEmailClient(email);
//                String idToUse = id.toString();

                //deleting client
                db.deleteClient(idToUse);
                Intent login = new Intent(editClient.this, MainActivity.class);
                startActivity(login);
            }
        });

        returnToClientProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnToClient = new Intent(editClient.this, ClientProfile.class);
                returnToClient.putExtra("USER_ID",clientId);
                startActivity(returnToClient);
            }
        });

    }
}