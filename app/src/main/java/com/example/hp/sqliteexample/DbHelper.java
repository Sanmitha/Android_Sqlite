package com.example.hp.sqliteexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by hp on 20-12-2017.
 */


public class DbHelper extends SQLiteOpenHelper {


    private String TAG = "DATABASE";
    //database Version
    private static final int DATABASE_VERSION = 1;

    //Data base nama

    private static final String DATABASE_NAME = "infodata";
    //Table name

    private static final String TABLE_NAME = "publicinfo";
    //Column Name
    private static final String NAME = "name";
    private static final String PH_NUMBER = "ph_number";
    private static final String KEY_ID = "id";

    //String query

    private static final String CREATE_QUERY = "CREATE TABLE " + TABLE_NAME + "("
            + KEY_ID + " INTEGER PRIMARY KEY," + NAME + " TEXT," + PH_NUMBER + " TEXT" + ")";
    private static final String DROP_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private static final String SELECT_QUERY = "SELECT * FROM " + TABLE_NAME;

    ModuleClass moduleClass = new ModuleClass();

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

   /* public DbHelper(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }*/


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_QUERY);
        onCreate(sqLiteDatabase);
    }
    public void addDetails(String Name, String phnum) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, Name);
        values.put(PH_NUMBER, phnum);
        sqLiteDatabase.insert(TABLE_NAME, null, values);
        sqLiteDatabase.close();
        Log.i(TAG, "Inserted successfully");
    }

    public void update(String Name, String phnum, int ID) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, Name);
        values.put(PH_NUMBER, phnum);
        sqLiteDatabase.update(TABLE_NAME, values, KEY_ID + " = ?", new String[]{String.valueOf(ID)});
        sqLiteDatabase.close();
    }

    public void delete(int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, KEY_ID + " = ?", new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
    }

    public ArrayList<ModuleClass> retriveData() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ArrayList<ModuleClass> arrayList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
        if (cursor.moveToFirst()) {
            for (int i = 0; i < cursor.getCount(); i++) {
                ModuleClass moduleClass = new ModuleClass();
                moduleClass.id = Integer.parseInt(cursor.getString(0));
                moduleClass.name = cursor.getString(1);
                moduleClass.phnumber = cursor.getString(2);
                arrayList.add(moduleClass);
                cursor.moveToNext();
            }
        }
        cursor.close();
        sqLiteDatabase.close();
        return arrayList;
    }



}