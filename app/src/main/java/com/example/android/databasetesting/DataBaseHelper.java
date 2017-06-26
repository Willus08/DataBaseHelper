package com.example.android.databasetesting;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by Android on 6/26/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION =1;
    private static final String NAME ="My_db";
    private static final  String TABLE_NAME = "People";
    private static final  String PERSON_NAME = "name";
    private static final  String PERSON_PHONE ="phone";
    private static final  String PERSON_ADDRESS ="address";
    private static final  String PERSON_ID= "id";
    private static final  String PERSON_PICTURE = "picture";

    public DataBaseHelper(Context context) {

        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+ "(" +PERSON_NAME+" TEXT, "+PERSON_ADDRESS+" TEXT, " +PERSON_PHONE+
                " TEXT, "+PERSON_PICTURE+" BLOB, " +PERSON_ID+ " TEXT PRIMARY KEY)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXITS "+ TABLE_NAME);
        onCreate(db);

    }
    public void saveNewPerson(Person p) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(PERSON_NAME, p.getName());
        content.put(PERSON_ADDRESS, p.getAddress());
        content.put(PERSON_PHONE, p.getPhone());
        content.put(PERSON_ID, p.getID());
        content.put(PERSON_PICTURE, p.getPicture().getRowBytes());
        database.insert(TABLE_NAME, null, content);
        Log.d(TAG, "saveNewContact: value saved");
        database.close();
    }

    public void  getPeople(){
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "Select * from " + TABLE_NAME;
        Cursor cursor = database.rawQuery(query,null);
        if(cursor.moveToFirst())
        {do {

            Log.d(TAG, "getPerson: Name: " + cursor.getString(0) + " Address: " + cursor.getString(1) + "Phone:" + cursor.getString(2) +" Picture: " + cursor.getString(3) + " ID: " + cursor.getString(4) );

        }while (cursor.moveToNext());
        }
        database.close();
    }

    public void DeleteByID(int i){
        String query  = "Delete from " + TABLE_NAME + " WHERE " + PERSON_ID + " = '" + i +"' ";
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(query);
        database.close();
    }



    public void UpdateByID(int i, Person p){
        String query  = PERSON_ID + " = '" + i +"' ";
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues content = new ContentValues();
        content.put(PERSON_NAME, p.getName());
        content.put(PERSON_ADDRESS, p.getAddress());
        content.put(PERSON_PHONE, p.getPhone());
        content.put(PERSON_ID, p.getID());
        content.put(PERSON_PICTURE, p.getPicture().getRowBytes());
        database.update(TABLE_NAME,content , query,null);

        database.close();

    }

}
