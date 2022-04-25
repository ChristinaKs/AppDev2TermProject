package com.example.findmytherapist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUpChoice extends AppCompatActivity {


    Button therapist,client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_choice);

        therapist = findViewById(R.id.TherapistButton);
        client = findViewById(R.id.CustomerButton);

        therapist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpChoice.this,TherapistSignupFragment.class);
                startActivity(intent);
            }
        });

        client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpChoice.this,ClientSignupFragment.class);
                startActivity(intent);
            }
        });
    }


}