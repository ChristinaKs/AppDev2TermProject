package com.example.findmytherapist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class SignUpChoice extends AppCompatActivity {


    Button therapist,client;
    TextView iam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_choice);

        therapist = findViewById(R.id.TherapistButton);
        client = findViewById(R.id.CustomerButton);
        iam = findViewById(R.id.IAmTV);

        therapist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //removing stuff in activity
                ViewGroup layout = (ViewGroup) therapist.getParent();
                layout.removeView(therapist);
                ViewGroup layout2 = (ViewGroup) client.getParent();
                layout2.removeView(client);
                ViewGroup layout3 = (ViewGroup) iam.getParent();
                layout3.removeView(iam);
                //displaying fragment
                FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.choice,new TherapistSignupFragment()).commit();
            }
        });

        client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //removing stuff in activity
                ViewGroup layout = (ViewGroup) therapist.getParent();
                layout.removeView(therapist);
                ViewGroup layout2 = (ViewGroup) client.getParent();
                layout2.removeView(client);
                ViewGroup layout3 = (ViewGroup) iam.getParent();
                layout3.removeView(iam);
                //displaying fragment
                FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.choice,new ClientSignupFragment()).commit();
            }
        });
    }


}