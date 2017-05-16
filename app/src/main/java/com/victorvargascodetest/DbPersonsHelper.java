package com.victorvargascodetest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbPersonsHelper  extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = DbPersonsDefinition.Entry.TABLE_NAME+".db";

    public DbPersonsHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+ DbPersonsDefinition.Entry.TABLE_NAME;
        query += " ("+DbPersonsDefinition.Entry.ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,";
        query += DbPersonsDefinition.Entry.FIRST_NAME+" INTEGER NOT NULL,";
        query += DbPersonsDefinition.Entry.LAST_NAME+" INTEGER NOT NULL,";
        query += DbPersonsDefinition.Entry.PHONE_NUMBER+" INTEGER NOT NULL,";
        query += DbPersonsDefinition.Entry.DATE_OF_BIRTH+" INTEGER NOT NULL,";
        query += DbPersonsDefinition.Entry.ZIPCODE+ " TEXT NOT NULL)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insert(ContentValues values){
        SQLiteDatabase db = getReadableDatabase();
        db.insert(DbPersonsDefinition.Entry.TABLE_NAME, null, values);
    }

    public void update(String first_name, String last_name, ContentValues values){
        SQLiteDatabase db = getReadableDatabase();
        String where = DbPersonsDefinition.Entry.FIRST_NAME + " = ? AND "+ DbPersonsDefinition.Entry.LAST_NAME + " = ?";
        db.update(DbPersonsDefinition.Entry.TABLE_NAME, values, where, new String[]{first_name, last_name});
    }

    public void delete(String first_name, String last_name){
        SQLiteDatabase db = getReadableDatabase();
        String where = DbPersonsDefinition.Entry.FIRST_NAME + " = ? AND "+ DbPersonsDefinition.Entry.LAST_NAME + " = ?";
        db.delete(DbPersonsDefinition.Entry.TABLE_NAME, where, new String[]{first_name, last_name});
    }

    public Cursor getAll(){
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                DbPersonsDefinition.Entry.ID,
                DbPersonsDefinition.Entry.FIRST_NAME,
                DbPersonsDefinition.Entry.LAST_NAME,
                DbPersonsDefinition.Entry.PHONE_NUMBER,
                DbPersonsDefinition.Entry.DATE_OF_BIRTH,
                DbPersonsDefinition.Entry.ZIPCODE
        };
        Cursor cursor = db.query(DbPersonsDefinition.Entry.TABLE_NAME, projection, null, null, null, null, null);
        return cursor;
    }

    public Cursor getByFirstNameAndLastName(String fn, String ln){
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                DbPersonsDefinition.Entry.ID,
                DbPersonsDefinition.Entry.FIRST_NAME,
                DbPersonsDefinition.Entry.LAST_NAME,
                DbPersonsDefinition.Entry.PHONE_NUMBER,
                DbPersonsDefinition.Entry.DATE_OF_BIRTH,
                DbPersonsDefinition.Entry.ZIPCODE
        };
        String where = DbPersonsDefinition.Entry.FIRST_NAME + " = ? AND "+ DbPersonsDefinition.Entry.LAST_NAME + " = ?";
        Cursor cursor = db.query(DbPersonsDefinition.Entry.TABLE_NAME, projection, where, new String[]{fn, ln}, null, null, null);
        return cursor;
    }


}
