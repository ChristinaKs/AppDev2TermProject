package com.example.findmytherapist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button login,signup;
    EditText email,password;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (Button)findViewById(R.id.LoginButton);
        signup = (Button)findViewById(R.id.SignUpButton);
        email = (EditText)findViewById(R.id.Email);
        password = (EditText)findViewById(R.id.Password);
        db = new DBHelper(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginEmail = email.getText().toString();
                String loginPass = password.getText().toString();
                if(loginEmail.equals("") || loginPass.equals("")){
                    Toast.makeText(MainActivity.this,"Information missing",Toast.LENGTH_SHORT).show();
                }else{
                    Boolean validateClient = db.checkClientEmailPassword(loginEmail,loginPass);
                    if(validateClient==true){
                        Intent intent = new Intent(MainActivity.this,ClientProfile.class);
                        startActivity(intent);
                    }else{
                        Boolean validateTherapist = db.checkTherapistEmailPassword(loginEmail,loginPass);
                        if(validateTherapist==true){
                            Intent intent = new Intent(MainActivity.this,TherapistProfile.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(MainActivity.this,"Information incorrect",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SignUpChoice.class);
                startActivity(intent);
            }
        });
    }
}