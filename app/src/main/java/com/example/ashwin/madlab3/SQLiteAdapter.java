package com.example.ashwin.madlab3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Ashwin on 10/28/2015.
 */
public class SQLiteAdapter {
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_GRNO = "GRNo";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_ROLLNO = "RollNo";
    public static final String COLUMN_DIVISION = "Division";
    public static final String COLUMN_CLASS = "Class";

    private static final String DATABASE_NAME = "MyDB";
    private static final String TABLE_STUDENT = "Student";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_DATABASE = "CREATE TABLE " + TABLE_STUDENT + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_GRNO + " INTEGER NOT NULL, " + COLUMN_NAME + " TEXT NOT NULL, " + COLUMN_ROLLNO + " INTEGER NOT NULL, " + COLUMN_DIVISION + " TEXT NOT NULL, " + COLUMN_CLASS + " TEXT NOT NULL);";

    private DatabaseHelper DBHelper;
    private SQLiteDatabase database;
    private Context context;

    public SQLiteAdapter(Context context) {
        this.context = context;
        DBHelper = new DatabaseHelper(this.context);
    }

    public SQLiteAdapter openDB() {
        database = DBHelper.getWritableDatabase();
        return this;
    }

    public void closeDB() {
        DBHelper.close();
    }

    public void insertInDB(int GRNo, String Name, int RollNo, String Division, String Class) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_GRNO, GRNo);
        contentValues.put(COLUMN_NAME, Name);
        contentValues.put(COLUMN_ROLLNO, RollNo);
        contentValues.put(COLUMN_DIVISION, Division);
        contentValues.put(COLUMN_CLASS, Class);

        database.insert(TABLE_STUDENT, null, contentValues);
    }

    public Cursor displayAll() {
        return database.query(TABLE_STUDENT, new String[]{COLUMN_ID, COLUMN_GRNO, COLUMN_NAME, COLUMN_ROLLNO, COLUMN_DIVISION, COLUMN_CLASS}, null, null, null, null, null, null);
    }

    public ArrayList<Cursor> getData(String Query) {
        //get writable database
        SQLiteDatabase sqlDB = DBHelper.getWritableDatabase();
        String[] columns = new String[]{"mesage"};
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2 = new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);


        try {
            String maxQuery = Query;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);


            //add value to cursor2
            Cursor2.addRow(new Object[]{"Success"});

            alc.set(1, Cursor2);
            if (null != c && c.getCount() > 0) {


                alc.set(0, c);
                c.moveToFirst();

                return alc;
            }
            return alc;
        } catch (SQLException sqlEx) {
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + sqlEx.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        } catch (Exception ex) {

            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + ex.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        }


    }

    private class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_DATABASE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(SQLiteAdapter.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
            onCreate(db);
        }
    }
}
