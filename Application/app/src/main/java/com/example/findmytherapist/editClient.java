package com.example.findmytherapist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class editClient extends AppCompatActivity {

    DBHelper db;
    EditText clientEmailEdit, clientFnameEdit, clientLnameEdit, clientAgeEdit, clientGenderEdit;
    Button updateClient, deleteClient, returnToClientProfile;

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

        String email = getIntent().getStringExtra("clientEmail");
        String first = getIntent().getStringExtra("clientFirstName");
        String last = getIntent().getStringExtra("clientLastName");
        String age = getIntent().getStringExtra("clientAge");
        String gender = getIntent().getStringExtra("clientGender");

        clientFnameEdit.setText(first);
        clientLnameEdit.setText(last);
        clientAgeEdit.setText(age);
        clientGenderEdit.setText(gender);

        updateClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdated = db.updateClient(/*Need session ID?*/clientFnameEdit.getText().toString(),
                        clientEmailEdit.getText().toString(), clientFnameEdit.getText().toString(),
                        clientLnameEdit.getText().toString(), clientGenderEdit.getText().toString(),
                        clientAgeEdit.getText().toString());
                if(isUpdated = true){
                    Toast.makeText(editClient.this, "Profile is updated", Toast.LENGTH_SHORT).show();
                }

            }
        });

        deleteClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteClient(/*Need session ID?*/clientFnameEdit.getText().toString());
                Intent login = new Intent(editClient.this, MainActivity.class);
                startActivity(login);
            }
        });

        returnToClientProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnToClient = new Intent(editClient.this, ClientProfile.class);
                startActivity(returnToClient);
            }
        });

    }
}