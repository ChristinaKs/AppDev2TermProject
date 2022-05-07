package com.example.findmytherapist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;

public class DeleteTimeSlotTherapist extends AppCompatActivity {

    ArrayList<String> mTimeSlot = new ArrayList<>();
    RecyclerView rv;
    DBHelper db = new DBHelper(DeleteTimeSlotTherapist.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_time_slot_therapist);

        //get intent
        Intent getId = getIntent();
        Integer userId = getId.getIntExtra("USER_ID",-1);
        String idToUse = userId.toString();
        rv = findViewById(R.id.timeSlotRV);
        //add time slots from that therapist into arraylist
        Cursor cursor = db.getTimeByTherapistId(idToUse);
        while(cursor.moveToNext()){
            mTimeSlot.add(cursor.getString(3));
        }

        DeleteTimeSlotAdapter adapter = new DeleteTimeSlotAdapter(mTimeSlot,DeleteTimeSlotTherapist.this,idToUse);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(DeleteTimeSlotTherapist.this));
    }
}