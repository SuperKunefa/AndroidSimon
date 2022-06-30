package com.example.simon;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String RECORDS_TABLE = "RECORDS_TABLE";
    public static final String CLOUMN_ID = "ID";
    public static final String CLOUMN_RECORD = "RECORD";
    public static final String CLOUMN_STAGE = "STAGE";
    public static final String CLOUMN_LEVEL = "LEVEL";
    public static final String CLOUMN_WIN = "WIN";
    public DataBaseHelper(@Nullable Context context) {
        super(context, "simon.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatment = "CREATE TABLE " + RECORDS_TABLE + " (" + CLOUMN_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + CLOUMN_RECORD + " INT, " + CLOUMN_STAGE + " INT, " + CLOUMN_LEVEL + " INT, " + CLOUMN_WIN + " BOOL" + ")";
        db.execSQL(createTableStatment);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(UserRecords rec)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CLOUMN_RECORD, rec.getRecord());
        cv.put(CLOUMN_LEVEL, rec.getLevel());
        cv.put(CLOUMN_WIN, rec.isWin());
        cv.put(CLOUMN_STAGE, rec.getStage());
        long insert = db.insertOrThrow(RECORDS_TABLE, null, cv);
        return insert == -1? false: true;
    }

    public List<UserRecords> getAll()
    {
        List<UserRecords> newLst = new ArrayList<UserRecords>();
        String queryString = "SELECT * FROM " + RECORDS_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst())
        {
            do {
                int rec_id = cursor.getInt(0);
                int rec = cursor.getInt(1);
                int level = cursor.getInt(2);
                int stage = cursor.getInt(3);
                boolean isWinn = cursor.getInt(4) == 1 ? true : false;
                UserRecords user = new UserRecords(rec, stage, isWinn, level);
                newLst.add(user);
            }
            while(cursor.moveToNext());

        }
        else
        {

        }
        cursor.close();
        db.close();
        return newLst;
    }
}
