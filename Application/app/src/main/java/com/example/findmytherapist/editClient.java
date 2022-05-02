package com.example.findmytherapist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class editClient extends AppCompatActivity {

    DBHelper db;
    EditText clientEmailEdit, clientFnameEdit, clientLnameEdit, clientAgeEdit, clientGenderEdit,clientAddressEdit;
    Button updateClient, deleteClient, returnToClientProfile;
//    public static final String SHARED_PREFS = "sharedPrefs";
//    public static final String EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_client);

        clientEmailEdit = findViewById(R.id.clientEmailEdit);
        clientFnameEdit = findViewById(R.id.clientFnameEdit);
        clientLnameEdit = findViewById(R.id.clientLnameEdit);
        clientAgeEdit = findViewById(R.id.ageClientEdit);
        clientGenderEdit = findViewById(R.id.genderClientEdit);
        updateClient = findViewById(R.id.updateClient);
        deleteClient = findViewById(R.id.deleteClient);
        returnToClientProfile = findViewById(R.id.returnClientProfile);
        clientAddressEdit = findViewById(R.id.editClientAddress);

        String email = getIntent().getStringExtra("clientEmail");
        String first = getIntent().getStringExtra("clientFirstName");
        String last = getIntent().getStringExtra("clientLastName");
        String age = getIntent().getStringExtra("clientAge");
        String gender = getIntent().getStringExtra("clientGender");
        Integer clientId = getIntent().getIntExtra("USER_ID",-1);
        String idToUse = clientId.toString();
        clientEmailEdit.setText(email);
        clientFnameEdit.setText(first);
        clientLnameEdit.setText(last);
        clientAgeEdit.setText(age);
        clientGenderEdit.setText(gender);

        updateClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                //retrieving email of whoever is logged in
//                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
//                String email = sharedPreferences.getString(EMAIL,"");
//                //retrieving id from email
//                Integer id = db.getIdByEmailClient(email);
//                String idToUse = id.toString();
                boolean isUpdated = db.updateClient(idToUse,clientEmailEdit.getText().toString(),clientFnameEdit.getText().toString(),
                         clientLnameEdit.getText().toString(), clientGenderEdit.getText().toString(),
                        clientAgeEdit.getText().toString(),clientAddressEdit.getText().toString());
                if(isUpdated = true){
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