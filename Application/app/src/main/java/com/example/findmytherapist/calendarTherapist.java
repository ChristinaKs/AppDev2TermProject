package com.example.findmytherapist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.Toast;


public class calendarTherapist extends AppCompatActivity {

    Button openDateBtn, openTimeBtn, addAvailabilityBtn, backToTherapistProfileBtn,deleteAvailability;
    TextView dateDisplay, timeDisplay, dateTimeDisplay;
    ListView allAvailabilities;
    int hour, minute;
    DBHelper db;
    ArrayList<String> timeList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_therapist);

        db = new DBHelper(calendarTherapist.this);
        Intent therapistCalendarIntent = getIntent();
        Integer userId = therapistCalendarIntent.getIntExtra("USER_ID",-1);
        String idToUse = userId.toString();

        openDateBtn = findViewById(R.id.openDateBtn);
        openTimeBtn = findViewById(R.id.openTimeBtn);
        addAvailabilityBtn = findViewById(R.id.addAvailabilityBtn);
        backToTherapistProfileBtn = findViewById(R.id.backToTherapistProfileBtn);
        deleteAvailability = findViewById(R.id.removeAvailability);

        dateDisplay = findViewById(R.id.dateDisplayTV);
        timeDisplay = findViewById(R.id.timeDisplayTV);
        dateTimeDisplay = findViewById(R.id.dateDisplayTV);

        allAvailabilities = findViewById(R.id.allAvailabilitiesLV);

        Cursor cursor = db.getTimeByTherapistId(idToUse);
        while(cursor.moveToNext()){
            timeList.add(cursor.getString(3));
        }
        //use arraylist to display times
        ArrayAdapter arrayAdapter = new ArrayAdapter(calendarTherapist.this,android.R.layout.simple_list_item_1,timeList);
        allAvailabilities.setAdapter(arrayAdapter);

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month +1;
                String date = makeDateString(day,month,year);
                dateDisplay.setText(date);

            }
        };

        openDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                int style = AlertDialog.THEME_HOLO_LIGHT;
                DatePickerDialog datePickerDialog = new DatePickerDialog(calendarTherapist.this,style,dateSetListener,year,month,day);
                datePickerDialog.show();
//                DialogFragment dialogFragment = new DatePickerFragment();
//                dialogFragment.show(getSupportFragmentManager(), "Date Picker");
            }
        });

        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                timeDisplay.setText(String.format(Locale.getDefault(),"%02d:%02d",hour,minute));
            }
        };

        openTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int style = AlertDialog.THEME_HOLO_LIGHT;
                TimePickerDialog timePickerDialog = new TimePickerDialog(calendarTherapist.this,style,onTimeSetListener,hour,minute,true);
                timePickerDialog.setTitle("Select Time");
                timePickerDialog.show();
//                DialogFragment dialogFragment = new TimePickerFragment();
//                dialogFragment.show(getSupportFragmentManager(), "Time Picker");
            }
        });

//        dateTimeDisplay.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                //if(!dateDisplay.equals(null) && !timeDisplay.equals(null)){
//                dateTimeDisplay.setText(dateDisplay.getText().toString() + " at " + timeDisplay.getText().toString());
//                //}
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//        if(!dateDisplay.equals(null) && !timeDisplay.equals(null)){
//            dateTimeDisplay.setText(dateDisplay.getText().toString() + " at " + timeDisplay.getText().toString());
//        }

        addAvailabilityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Need to add date and time to database
                String dateAndTime = dateDisplay.getText().toString()+" at "+timeDisplay.getText().toString();
                //check that therapist entered date AND time
                String emptyDate = dateDisplay.getText().toString();
                String emptyTime = timeDisplay.getText().toString();
                if (emptyDate.isEmpty() || emptyTime.isEmpty()){
                    Toast.makeText(calendarTherapist.this,"Must enter a day and time",Toast.LENGTH_SHORT).show();
                }
                //check that time slot doesn't already exist
                else if(db.checkTherapistTimeSlotExists(idToUse,dateAndTime)){
                    Toast.makeText(calendarTherapist.this,"This time slot already exists for you",Toast.LENGTH_SHORT).show();
                }else{
                    if(db.insertTime(userId,1,dateAndTime)){
                        //Need to add dateTimeDisplay into ListView
                        //get from cursor and then add into arraylist
                        timeList.add(dateAndTime);
                        ArrayAdapter arrayAdapter = new ArrayAdapter(calendarTherapist.this,android.R.layout.simple_list_item_1,timeList);
                        allAvailabilities.setAdapter(arrayAdapter);
                    }
                }


            }
        });

        deleteAvailability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent delete = new Intent(calendarTherapist.this, DeleteTimeSlotTherapist.class);
                delete.putExtra("USER_ID",userId);
                startActivity(delete);
            }
        });

        backToTherapistProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backToTherapistProfile = new Intent(calendarTherapist.this, TherapistProfile.class);
                backToTherapistProfile.putExtra("USER_ID",userId);
                startActivity(backToTherapistProfile);
            }
        });
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month)+" "+day+" "+year;
    }

    private String getMonthFormat(int month) {
        if(month == 1){
            return "JAN";
        }
        if(month == 2){
            return "FEB";
        }
        if(month == 3){
            return "MAR";
        }
        if(month == 4){
            return "APR";
        }
        if(month == 5){
            return "MAY";
        }
        if(month == 6){
            return "JUN";
        }
        if(month == 7){
            return "JUL";
        }
        if(month == 8){
            return "AUG";
        }
        if(month == 9){
            return "SEP";
        }
        if(month == 10){
            return "OCT";
        }
        if(month == 11){
            return "NOV";
        }
        if(month == 12){
            return "DEC";
        }
        return "JAN";
    }

    //@Override
    /*public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, i);
        c.set(Calendar.MONTH, i1);
        c.set(Calendar.DAY_OF_MONTH, i2);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        dateDisplay.setText(currentDateString);
    }

    //@Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        timeDisplay.setText(" Hour : " + i + " Minute : " + i1);
    }*/
}