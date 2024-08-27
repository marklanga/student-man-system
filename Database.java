package com.example.weekx;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class Database extends SQLiteOpenHelper {

    public Context context;
    public static final String DATABASE_NAME = "StudentManSys.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "students";
    public static final String COLUMN_ID = "student_id";
    public static final String COLUMN_NAME = "students_name";
    public static final String COLUMN_SURNAME = "student_surname";
    public static final String COLUMN_COURSE = "student_course";

    public Database(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT,  " +
                COLUMN_SURNAME + " TEXT, " +
                COLUMN_COURSE + " TEXT);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }

    public void add(String name, String surname, String course){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, name);
        values.put(COLUMN_SURNAME, surname);
        values.put(COLUMN_COURSE, course);
        long result = sqLiteDatabase.insert(TABLE_NAME, null, values);

        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(context, "Success", Toast.LENGTH_LONG).show();
        }
    }

    public Cursor readAllStudents(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = null;

        cursor = sqLiteDatabase.rawQuery(query, null);

        return  cursor;
    }

	public void delete(){}



}
