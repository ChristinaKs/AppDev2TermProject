package com.example.findmytherapist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "FindMyTherapist.db";

    private static final String THERAPIST_TABLE = "Therapist_table";
    private static final String THERAPIST_COL_1 = "Therapist_License";
    private static final String THERAPIST_COL_2 = "Therapist_Email";
    private static final String THERAPIST_COL_3 = "Therapist_Password";
    private static final String THERAPIST_COL_4 = "Therapist_First_Name";
    private static final String THERAPIST_COL_5 = "Therapist_Last_Name";
    private static final String THERAPIST_COL_6 = "Therapist_Gender";
    private static final String THERAPIST_COL_7 = "Therapist_OffersPhone";
    private static final String THERAPIST_COL_8 = "Therapist_OffersText";
    private static final String THERAPIST_COL_9 = "Therapist_OffersZoom";
    private static final String THERAPIST_COL_10 = "Therapist_OffersPerson";
    private static final String THERAPIST_COL_11 = "Therapist_Address";


    private static final String CLIENT_TABLE = "Client_table";
    private static final String CLIENT_COL_1 = "Client_Id";
    private static final String CLIENT_COL_2 = "Client_Email";
    private static final String CLIENT_COL_3 = "Client_Password";
    private static final String CLIENT_COL_4 = "Client_First_Name";
    private static final String CLIENT_COL_5 = "Client_Last_Name";
    private static final String CLIENT_COL_6 = "Client_Gender";
    private static final String CLIENT_COL_7 = "Client_Age";
    private static final String CLIENT_COL_8 = "Client_Address";

    private static final String TIME_TABLE = "Time_table";
    private static final String TIME_COL_1 = "Time_Id";
    private static final String TIME_COL_2 = "Therapist_License";
    private static final String TIME_COL_3 = "Time_IsAvailable";
    private static final String TIME_COL_4 = "Time_Time";

    private static final String APPOINTMENT_TABLE = "Appointment_table";
    private static final String APPOINTMENT_COL_1 = "Appointment_Id";
    private static final String APPOINTMENT_COL_2 = "Appointment_Therapist_License";
    private static final String APPOINTMENT_COL_3 = "Appointment_Client_Id";
    private static final String APPOINTMENT_COL_4 = "Appointment_Time";


    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 4);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "
                + THERAPIST_TABLE + " (Therapist_License Integer primary key, "
                + "Therapist_Email Text, Therapist_Password Text,Therapist_First_Name Text,Therapist_Last_Name Text,Therapist_Gender Text,"
                +"Therapist_OffersPhone Integer,Therapist_OffersText Integer,Therapist_OffersZoom Integer,Therapist_OffersPerson Integer,Therapist_Address Text)");

        sqLiteDatabase.execSQL("create table "
                + CLIENT_TABLE + " (Client_Id Integer primary key autoincrement, Client_Email Text, Client_Password Text,"
                +"Client_First_Name Text,Client_Last_Name Text,Client_Gender Text,Client_Age Text,Client_Address Text)");

        sqLiteDatabase.execSQL("create table "
                + TIME_TABLE + "(Time_Id Integer primary key autoincrement, Therapist_License Integer, Time_IsAvailable Integer,Time_Time Text)");
               // + " FOREIGN KEY ("+TIME_COL_2+") REFERENCES "+THERAPIST_TABLE+"("+THERAPIST_COL_1+")");

        sqLiteDatabase.execSQL("create table "
                + APPOINTMENT_TABLE + " (Appointment_Id Integer primary key autoincrement, Appointment_Therapist_License Integer, Appointment_Client_Id Integer,"
                +"Appointment_Time Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists " + THERAPIST_TABLE);
        sqLiteDatabase.execSQL("Drop table if exists " + CLIENT_TABLE);
        sqLiteDatabase.execSQL("Drop table if exists " + TIME_TABLE);
        sqLiteDatabase.execSQL("Drop table if exists " + APPOINTMENT_TABLE);
        onCreate(sqLiteDatabase);
    }

    public boolean insertTime (Integer Therapist_License, Integer Time_IsAvailable,String Time_Time) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TIME_COL_2,Therapist_License);
        contentValues.put(TIME_COL_3,Time_IsAvailable);
        contentValues.put(TIME_COL_4,Time_Time);
        long result = db.insert(TIME_TABLE, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }

    public boolean insertClient (String Client_Email, String Client_Password,String Client_First_Name,
                                    String Client_Last_Name, String Client_Gender, String Client_Age,String Client_Address) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CLIENT_COL_2,Client_Email);
        contentValues.put(CLIENT_COL_3,Client_Password);
        contentValues.put(CLIENT_COL_4,Client_First_Name);
        contentValues.put(CLIENT_COL_5,Client_Last_Name);
        contentValues.put(CLIENT_COL_6,Client_Gender);
        contentValues.put(CLIENT_COL_7,Client_Age);
        contentValues.put(CLIENT_COL_8,Client_Address);
        long result = db.insert(CLIENT_TABLE, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }

    public boolean insertTherapist (Integer Therapist_License, String Therapist_Email,String Therapist_Password,
                                    String Therapist_First_Name, String Therapist_Last_Name, String Therapist_Gender,
                                    Integer phone, Integer text, Integer zoom, Integer person, String address) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(THERAPIST_COL_1,Therapist_License);
        contentValues.put(THERAPIST_COL_2,Therapist_Email);
        contentValues.put(THERAPIST_COL_3,Therapist_Password);
        contentValues.put(THERAPIST_COL_4,Therapist_First_Name);
        contentValues.put(THERAPIST_COL_5,Therapist_Last_Name);
        contentValues.put(THERAPIST_COL_6,Therapist_Gender);
        contentValues.put(THERAPIST_COL_7,phone);
        contentValues.put(THERAPIST_COL_8,text);
        contentValues.put(THERAPIST_COL_9,zoom);
        contentValues.put(THERAPIST_COL_10,person);
        contentValues.put(THERAPIST_COL_11,address);
        long result = db.insert(THERAPIST_TABLE, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }

    public boolean insertAppointment (Integer Therapist_License, Integer Client_Id,String Time_Time) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(APPOINTMENT_COL_2,Therapist_License);
        contentValues.put(APPOINTMENT_COL_3,Client_Id);
        contentValues.put(APPOINTMENT_COL_4,Time_Time);
        long result = db.insert(APPOINTMENT_TABLE, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }

    public Cursor getTherapistData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + THERAPIST_TABLE, null);
        return res;
    }

    public Cursor getTherapistById(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + THERAPIST_TABLE + " where "+THERAPIST_COL_1+" =?",new String[]{id}, null);
        //res.moveToFirst();
        return res;
    }
    public Cursor getClientDataById(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + CLIENT_TABLE + " where "+CLIENT_COL_1+" =?",new String[]{id}, null);
        return res;
    }
    public Cursor getTimeByTherapistId(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TIME_TABLE + " where "+TIME_COL_2+" =?",new String[]{id}, null);
        //res.moveToFirst();
        return res;
    }
    public Cursor getTimeData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TIME_TABLE, null);
        return res;
    }

    public boolean updateTherapist (String id, String Therapist_Email,String Therapist_First_Name,String Therapist_Last_Name,String Therapist_Gender,
                                    Integer phone, Integer text, Integer zoom, Integer person, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(THERAPIST_COL_2, Therapist_Email);
        contentValues.put(THERAPIST_COL_4, Therapist_First_Name);
        contentValues.put(THERAPIST_COL_5, Therapist_Last_Name);
        contentValues.put(THERAPIST_COL_6, Therapist_Gender);
        contentValues.put(THERAPIST_COL_7, phone);
        contentValues.put(THERAPIST_COL_8, text);
        contentValues.put(THERAPIST_COL_9, zoom);
        contentValues.put(THERAPIST_COL_10, person);
        contentValues.put(THERAPIST_COL_11, address);
        db.update(THERAPIST_TABLE, contentValues, THERAPIST_COL_1+" = ? ", new String[] {id});
        return  true;
    }
    public boolean updateClient (String id, String Client_Email,String Client_First_Name,String Client_Last_Name,
                                 String Client_Gender,String Client_Age,String Client_Address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CLIENT_COL_2, Client_Email);
        contentValues.put(CLIENT_COL_4, Client_First_Name);
        contentValues.put(CLIENT_COL_5, Client_Last_Name);
        contentValues.put(CLIENT_COL_6, Client_Gender);
        contentValues.put(CLIENT_COL_7, Client_Age);
        contentValues.put(CLIENT_COL_8, Client_Address);
        db.update(CLIENT_TABLE, contentValues, CLIENT_COL_1+" = ? ", new String[] {id});
        return  true;
    }
    public boolean updateTime (String id, String Time_IsAvailable,String Time_Time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TIME_COL_3, Time_IsAvailable);
        contentValues.put(TIME_COL_3, Time_Time);
        db.update(TIME_TABLE, contentValues, TIME_COL_1+" = ? ", new String[] {id});
        return  true;
    }
    public Integer deleteTherapist (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(THERAPIST_TABLE, THERAPIST_COL_1+" = ?", new String[] {id});

    }


    public Integer deleteClient (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(CLIENT_TABLE, CLIENT_COL_1+" = ?", new String[] {id});

    }

    public Integer deleteTimeSlot (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TIME_TABLE, TIME_COL_1+" = ? ", new String[] {id});

    }

    public Boolean checkClientEmailExists(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " +CLIENT_TABLE+" where "+CLIENT_COL_2+" =?",new String[]{email});
        if(cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }
    public Boolean checkTherapistTimeSlotExists(String id,String time){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " +TIME_TABLE+" where "+TIME_COL_2+" =? and "+TIME_COL_4+" =? ",new String[]{id,time});
        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }
    public Boolean checkTherapistEmailExists(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " +THERAPIST_TABLE+" where "+THERAPIST_COL_2+" =?",new String[]{email});
        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }

    public Boolean checkClientEmailPassword(String email,String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+CLIENT_TABLE+" where "+CLIENT_COL_2+" =? and "+CLIENT_COL_3+" =? ",new String[]{email,pass});
        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }
    public Boolean checkTherapistEmailPassword(String email,String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+THERAPIST_TABLE+" where "+THERAPIST_COL_2+" =? and "+THERAPIST_COL_3+" =? ",new String[]{email,pass});
        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }

    public Integer getIdByEmailClient(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select Client_Id from "+CLIENT_TABLE+" where "+CLIENT_COL_2+" =? ",new String[]{email});
        cursor.moveToFirst();
        Integer result = cursor.getInt(cursor.getColumnIndexOrThrow("Client_Id"));
        return result;
    }
    public Integer getTimeIdByLicenseAndTime(String license,String time){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select Time_Id from "+TIME_TABLE+" where "+TIME_COL_2+" =? and "+TIME_COL_4+" =? ",new String[]{license,time});
        cursor.moveToFirst();
        //System.out.print(cursor.moveToFirst());
        System.out.print("ah");
        Integer result = cursor.getInt(cursor.getColumnIndexOrThrow("Time_Id"));
        return result;
    }
    public Integer getIdByEmailTherapist(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select Therapist_License from "+THERAPIST_TABLE+" where "+THERAPIST_COL_2+" =? ",new String[]{email});
        cursor.moveToFirst();
        Integer result = cursor.getInt(cursor.getColumnIndexOrThrow("Therapist_License") );
        return result;
    }
}
