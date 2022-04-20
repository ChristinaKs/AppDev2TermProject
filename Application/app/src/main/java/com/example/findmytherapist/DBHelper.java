package com.example.findmytherapist;

import android.content.Context;
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
    private static final String THERAPIST_COL_7 = "Therapist_Platform";
    private static final String THERAPIST_COL_8 = "Therapist_Price_Range";
    private static final String THERAPIST_COL_9 = "Therapist_Therapy_Type";

    private static final String CLIENT_TABLE = "Client_table";
    private static final String CLIENT_COL_1 = "Client_Id";
    private static final String CLIENT_COL_2 = "Client_Email";
    private static final String CLIENT_COL_3 = "Client_Password";
    private static final String CLIENT_COL_4 = "Client_First_Name";
    private static final String CLIENT_COL_5 = "Client_Last_Name";
    private static final String CLIENT_COL_6 = "Client_Gender";
    private static final String CLIENT_COL_7 = "Client_Age";

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
                +"Therapist_Platform Text, Therapist_Price_Range Text,Therapist_Therapy_Type Text)");

        sqLiteDatabase.execSQL("create table "
                + CLIENT_TABLE + " (Client_Id Integer primary key autoincrement, Client_Email Text, Client_Password Text,"
                +"Client_First_Name Text,Client_Last_Name Text,Client_Gender Text,Client_Age Integer)");

        sqLiteDatabase.execSQL("create table "
                + TIME_TABLE + " (Time_Id Integer primary key autoincrement, Therapist_License Integer, "
                +"Time_IsAvailable Integer,Time_Time Text"
                + "FOREIGNKEY ("+TIME_COL_2+") REFERENCES "+THERAPIST_TABLE+"("+THERAPIST_COL_1+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists " + THERAPIST_TABLE);
        sqLiteDatabase.execSQL("Drop table if exists " + CLIENT_TABLE);
        sqLiteDatabase.execSQL("Drop table if exists " + TIME_TABLE);
        onCreate(sqLiteDatabase);
    }
}
