package com.example.findmytherapist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button login,signup;
    EditText email,password;
    DBHelper db;

    //public static final String SHARED_PREFS = "sharedPrefs";
    //public static final String EMAIL = "email";
    //SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (Button)findViewById(R.id.LoginButton);
        signup = (Button)findViewById(R.id.SignUpButton);
        email = (EditText)findViewById(R.id.Email);
        password = (EditText)findViewById(R.id.Password);
        db = new DBHelper(this);

        /*String whoLoggedIn = sharedPreferences.getString(EMAIL,"");
        if(whoLoggedIn != null){
            //go straight to profile
        }*/

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginEmail = email.getText().toString();
                String loginPass = password.getText().toString();
                //if user didn't enter anything
                if(loginEmail.equals("") || loginPass.equals("")){
                    Toast.makeText(MainActivity.this,"Information missing",Toast.LENGTH_SHORT).show();
                }else{
                    //go through database to see if its a client
                    Boolean validateClient = db.checkClientEmailPassword(loginEmail,loginPass);
                    if(validateClient==true){
                        //saveWhoLoggedIn(loginEmail);
                        //if it's client then log in
                        Integer id = db.getIdByEmailClient(loginEmail);
                        Intent intent = new Intent(MainActivity.this,ClientProfile.class);
                        intent.putExtra("USER_ID",id);
                        startActivity(intent);
                    }else{
                        //if it's not a client maybe it's a therapist
                        Boolean validateTherapist = db.checkTherapistEmailPassword(loginEmail,loginPass);
                        if(validateTherapist==true){
                            //saveWhoLoggedIn(loginEmail);
                            //if it is a therapist then log in
                            Integer id = db.getIdByEmailTherapist(loginEmail);
                            Intent intent = new Intent(MainActivity.this,TherapistProfile.class);
                            intent.putExtra("USER_ID",id);
                            startActivity(intent);
                        }else{
                            //it's neither or user got their info wrong
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

   /* public void saveWhoLoggedIn(String email){
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(EMAIL, email);
        editor.apply();
    }*/
}