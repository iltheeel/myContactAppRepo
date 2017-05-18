package com.example.liaiwei.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by liaiwei on 5/11/17.
 */


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Contact.db";
    public static final String TABLE_NAME = "Contact_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "ADDRESS";
    public static final String COL_4 = "NUMBER";



    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, ADDRESS TEXT, NUMBER TEXT)"); //, NAME, ADDRESS, NUMBER)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }

    public Cursor findData(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT ID, NAME, ADDRESS, NUMBER FROM Contact_table WHERE NAME = ? " , new String[] {name});
        return res;
    }

    public boolean insertData (String name, String address, String number) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, address);
        contentValues.put(COL_4, number);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1) {
            return false;

        } else {
            return true;
        }
    }



    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
}
