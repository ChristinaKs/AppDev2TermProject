package com.example.findmytherapist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class MyClients extends AppCompatActivity {

    ImageButton therapistProfile, myClients, therapistAppointments;
    DBHelper db = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_clients);

        therapistProfile = (ImageButton) findViewById(R.id.therapistProfileIM);
        myClients = (ImageButton) findViewById(R.id.myClientsIM);
        therapistAppointments = (ImageButton) findViewById(R.id.therapistAppointmentIM);

        Integer id = getIntent().getIntExtra("USER_ID",-1);
        String idToUse = id.toString();

        ArrayList<String> ClientsID = new ArrayList<>();
        ArrayList<String> ClientsFirstName = new ArrayList<>();
        ArrayList<String> ClientsLastName = new ArrayList<>();
        ArrayList<String> ClientsAge = new ArrayList<>();
        ArrayList<String> ClientsGender = new ArrayList<>();


//        Cursor result = db.getTherapistData();
//        while(result.moveToNext()){
//            //id in db is integer so we're turning it into a string
//            Integer ids = result.getInt(0);
//            String arrayId = ids.toString();
//            ClientsID.add(arrayId);
//
//            //getting rest of info
//            ClientsFirstName.add(result.getString(3));
//            ClientsLastName.add(result.getString(4));
//            ClientsAge.add(result.getString(6));
//            ClientsGender.add(result.getString(5));
//        }
//
//        //display it all in the recyclerview
//        RecyclerView adapter = findViewById(R.id.searchRV);
//        MyClientsAdapter myClientsAdapter = new MyClientsAdapter(ClientsFirstName,ClientsLastName,ClientsAge,ClientsGender, this);
//        adapter.setAdapter(myClientsAdapter);
//        adapter.setLayoutManager(new LinearLayoutManager(this));

        therapistProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent clientProfileIntent = new Intent(MyClients.this, TherapistProfile.class);
                clientProfileIntent.putExtra("USER_ID",id);
                startActivity(clientProfileIntent);
            }
        });

        myClients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchTherapistIntent = new Intent(MyClients.this, MyClients.class);
                searchTherapistIntent.putExtra("USER_ID",id);
                startActivity(searchTherapistIntent);
            }
        });

        therapistAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent clientAppointmentsIntent = new Intent(MyClients.this, TherapistAppointments.class);
                clientAppointmentsIntent.putExtra("USER_ID",id);
                startActivity(clientAppointmentsIntent);
            }
        });
    }
}