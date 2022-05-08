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

    static DBHelper db;
    //Button bookingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        //bookingBtn = findViewById(R.id.bookButton);

        db = new DBHelper(Booking.this);
        Intent intent = getIntent();
        String idToUse = intent.getStringExtra("USER_ID");

        ArrayList<String> times = new ArrayList<>();
        ArrayList<String> timeIdList = new ArrayList<>();

        Cursor cursor = db.getTimeByTherapistId(idToUse);
        while(cursor.moveToNext()){
            Integer id = cursor.getInt(0);
            String timeId= id.toString();
            timeIdList.add(timeId);
            times.add(cursor.getString(3));
        }

        //recyclerview adapter and put in arraylist of time and id
        RecyclerView adapter = findViewById(R.id.bookRecyclerView);
        BookingAdapter bookingAdapter = new BookingAdapter(timeIdList,times,this);
        adapter.setAdapter(bookingAdapter);
        adapter.setLayoutManager(new LinearLayoutManager(this));


    }
}