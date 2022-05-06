package com.example.findmytherapist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;


public class calendarTherapist extends AppCompatActivity {

    Button openDateBtn, openTimeBtn, addAvailabilityBtn, backToTherapistProfileBtn;
    TextView dateDisplay, timeDisplay, dateTimeDisplay;
    ListView allAvailabilities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_therapist);

        openDateBtn = findViewById(R.id.openDateBtn);
        openTimeBtn = findViewById(R.id.openTimeBtn);
        addAvailabilityBtn = findViewById(R.id.addAvailabilityBtn);
        backToTherapistProfileBtn = findViewById(R.id.backToTherapistProfileBtn);

        dateDisplay = findViewById(R.id.dateDisplayTV);
        timeDisplay = findViewById(R.id.timeDisplayTV);
        dateTimeDisplay = findViewById(R.id.dateDisplayTV);

        allAvailabilities = findViewById(R.id.allAvailabilitiesLV);


        openDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment = new DatePickerFragment();
                dialogFragment.show(getSupportFragmentManager(), "Date Picker");
            }
        });

        openTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment = new TimePickerFragment();
                dialogFragment.show(getSupportFragmentManager(), "Time Picker");
            }
        });

        if(!dateDisplay.equals(null) && !timeDisplay.equals(null)){
            dateTimeDisplay.setText(dateDisplay.getText().toString() + " at " + timeDisplay.getText().toString());
        }

        addAvailabilityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Need to add date and time to database
                //Need to add dateTimeDisplay into ListView
            }
        });

        backToTherapistProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backToTherapistProfile = new Intent(calendarTherapist.this, TherapistProfile.class);
                startActivity(backToTherapistProfile);
            }
        });
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, i);
        c.set(Calendar.MONTH, i1);
        c.set(Calendar.DAY_OF_MONTH, i2);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        dateDisplay.setText(currentDateString);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        timeDisplay.setText(" Hour : " + i + " Minute : " + i1);
    }
}