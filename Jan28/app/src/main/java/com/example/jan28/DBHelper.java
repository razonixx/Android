package com.example.jan28;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_FILE =  "Students.db";
    private static final String DB_TABLE = "Students";
    private static final String FIELD_ID = "id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_GRADE = "grade";


    public DBHelper(Context context){
        super(context, DB_FILE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + DB_TABLE  + "(" +
                FIELD_ID + " INTEGER PRIMARY KEY, " +
                FIELD_NAME + " TEXT, " +
                FIELD_GRADE + " INTEGER)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS ?";
        String[] args = {DB_TABLE};
        db.execSQL(query, args);

        onCreate(db);
    }

    public void add(String name, int grade){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //Autoboxing, interchangeability between primitives and complete class
        contentValues.put(FIELD_NAME, name);
        contentValues.put(FIELD_GRADE, grade);

        //DB Insert
        db.insert(DB_TABLE, null, contentValues);
    }

    public int delete(int id){
        SQLiteDatabase db = getWritableDatabase();

        //claue: condition for the query to happen
        String clause = FIELD_ID + " = ?";
        //args: arguments for the clause
        String[] args = {id + ""};

        //db.delete
        return db.delete(DB_TABLE, clause, args);
    }

    public String find(String name){
        SQLiteDatabase db = getReadableDatabase();
        //clause: condition for the query to happen
        String clause = FIELD_NAME + " = ?";
        //args: arguments for the clause
        String[] args = {name};

        //Cursor cursor = db.query(DB_TABLE, null, clause, args, FIELD_ID, null, null);
        Cursor cursor = db.query(DB_TABLE, null, clause, args, FIELD_ID, null, null);

        String result = "";

        //Find all results
        /*while(cursor.moveToNext()){

        }*/

        //Find first result
        if(cursor.moveToFirst()){
            //result = cursor.getInt(2);
            result ="ID: " + cursor.getInt(0) + " Name: " + cursor.getString(1) + " Grade: " + cursor.getInt(2);
        }
        return result;
    }
}
