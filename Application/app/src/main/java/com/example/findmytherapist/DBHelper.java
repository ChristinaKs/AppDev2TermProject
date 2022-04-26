package com.example.findmytherapist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    private static final String THERAPIST_COL_7 = "Therapist_Address";
    private static final String THERAPIST_COL_8 = "Therapist_Price_Range";
    private static final String THERAPIST_COL_9 = "Therapist_Therapy_Type";
    private static final String THERAPIST_COL_10 = "Therapist_OffersPhone";
    private static final String THERAPIST_COL_11 = "Therapist_OffersText";
    private static final String THERAPIST_COL_12 = "Therapist_OffersZoom";
    private static final String THERAPIST_COL_13 = "Therapist_OfferInPerson";

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

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "
                + THERAPIST_TABLE + " (Therapist_License Integer primary key, "
                + "Therapist_Email Text, Therapist_Password Text,Therapist_First_Name Text,Therapist_Last_Name Text,Therapist_Gender Text,"
                +"Therapist_Address Text, Therapist_Price_Range Text,Therapist_Therapy_Type Text,Therapist_OffersPhone Integer,Therapist_OffersText Integer,Therapist_OffersZoom Integer,Therapist_OfferInPerson Integer)");

        sqLiteDatabase.execSQL("create table "
                + CLIENT_TABLE + " (Client_Id Integer primary key autoincrement, Client_Email Text, Client_Password Text,"
                +"Client_First_Name Text,Client_Last_Name Text,Client_Gender Text,Client_Age Text,Client_Address)");

        sqLiteDatabase.execSQL("create table "
                + TIME_TABLE + " Time_Id Integer primary key autoincrement, Therapist_License Integer, Time_IsAvailable Integer,Time_Time Text"
                + " FOREIGN KEY ("+TIME_COL_2+") REFERENCES "+THERAPIST_TABLE+"("+THERAPIST_COL_1+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists " + THERAPIST_TABLE);
        sqLiteDatabase.execSQL("Drop table if exists " + CLIENT_TABLE);
        sqLiteDatabase.execSQL("Drop table if exists " + TIME_TABLE);
        onCreate(sqLiteDatabase);
    }

    public boolean insertTime (String Therapist_License, String Time_IsAvailable,String Time_Time) {

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

    public boolean insertTherapist (String Therapist_License, String Therapist_Email,String Therapist_Password,
                                    String Therapist_First_Name, String Therapist_Last_Name, String Therapist_Gender,
                                    String Therapist_Address, String Therapist_Price_Range, String Therapist_Therapy_Type,
                                    Integer Therapist_OffersPhone,Integer Therapist_OffersText,Integer Therapist_OffersZoom,Integer Therapist_OfferInPerson) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(THERAPIST_COL_1,Therapist_License);
        contentValues.put(THERAPIST_COL_2,Therapist_Email);
        contentValues.put(THERAPIST_COL_3,Therapist_Password);
        contentValues.put(THERAPIST_COL_4,Therapist_First_Name);
        contentValues.put(THERAPIST_COL_5,Therapist_Last_Name);
        contentValues.put(THERAPIST_COL_6,Therapist_Gender);
        contentValues.put(THERAPIST_COL_7,Therapist_Address);
        contentValues.put(THERAPIST_COL_8,Therapist_Price_Range);
        contentValues.put(THERAPIST_COL_9,Therapist_Therapy_Type);
        contentValues.put(THERAPIST_COL_10,Therapist_OffersPhone);
        contentValues.put(THERAPIST_COL_11,Therapist_OffersText);
        contentValues.put(THERAPIST_COL_12,Therapist_OffersZoom);
        contentValues.put(THERAPIST_COL_13,Therapist_OfferInPerson);
        long result = db.insert(THERAPIST_TABLE, null, contentValues);
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
    public Cursor getClientData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + CLIENT_TABLE, null);
        return res;
    }

    public Cursor getTimeData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TIME_TABLE, null);
        return res;
    }

    public boolean updateTherapist (String id, String Therapist_Email,String Therapist_First_Name,String Therapist_Last_Name,String Therapist_Gender,
                                    String Therapist_Platform,String Therapist_Price_Range,String Therapist_Therapy_Type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(THERAPIST_COL_2, Therapist_Email);
        contentValues.put(THERAPIST_COL_4, Therapist_First_Name);
        contentValues.put(THERAPIST_COL_5, Therapist_Last_Name);
        contentValues.put(THERAPIST_COL_6, Therapist_Gender);
        contentValues.put(THERAPIST_COL_7, Therapist_Platform);
        contentValues.put(THERAPIST_COL_8, Therapist_Price_Range);
        contentValues.put(THERAPIST_COL_9, Therapist_Therapy_Type);
        db.update(THERAPIST_TABLE, contentValues, THERAPIST_COL_1+" = ? ", new String[] {id});
        return  true;
    }
    public boolean updateClient (String id, String Client_Email,String Client_First_Name,String Client_Last_Name,
                                 String Client_Gender,String Client_Age) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CLIENT_COL_2, Client_Email);
        contentValues.put(CLIENT_COL_4, Client_First_Name);
        contentValues.put(CLIENT_COL_5, Client_Last_Name);
        contentValues.put(CLIENT_COL_6, Client_Gender);
        contentValues.put(CLIENT_COL_7, Client_Age);
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
        return db.delete(TIME_TABLE, TIME_COL_1+" = ?", new String[] {id});

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
}
