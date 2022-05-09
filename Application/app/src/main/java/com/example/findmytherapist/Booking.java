package com.example.findmytherapist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Booking extends AppCompatActivity {

    //booking class that will use booking adapter for its recycler view
    static DBHelper db;
    //Button bookingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        //bookingBtn = findViewById(R.id.bookButton);

        db = new DBHelper(Booking.this);
        //getting clientid and therapist id from when user clicks  "view times"
        Intent intent = getIntent();
        String therapistId = intent.getStringExtra("THERAPIST_ID");
        String clientId = intent.getStringExtra("USER_ID");

        ArrayList<String> times = new ArrayList<>();
        ArrayList<String> timeIdList = new ArrayList<>();

        //getting all the times from that therapist and adding the id and the times into array list
        Cursor cursor = db.getTimeByTherapistId(therapistId);
        while(cursor.moveToNext()){
            Integer id = cursor.getInt(0);
            String timeId= id.toString();
            timeIdList.add(timeId);
            times.add(cursor.getString(3));
        }

        //recyclerview adapter and put in arraylist of time,time id,therapist id and client id
        RecyclerView adapter = findViewById(R.id.bookRecyclerView);
        BookingAdapter bookingAdapter = new BookingAdapter(timeIdList,times,therapistId,clientId,this);
        adapter.setAdapter(bookingAdapter);
        adapter.setLayoutManager(new LinearLayoutManager(this));


    }
}