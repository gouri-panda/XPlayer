package com.one4ll.xplayer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class TaskDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="movies.db";
    public static final int DATABASE_VERSION =1;
    final String SQL_CREATE_TABLE ="CREATE TABLE "+
            TaskContract.TaskEntry.TABLE_NAME+" ("+
            TaskContract.TaskEntry._ID +"INTEGER PRIMARY KEY AUTOINCREMENT,"+
            TaskContract.TaskEntry.COLUMN_ID + "TEXT NOT NULL,"+
            TaskContract.TaskEntry.COLUMN_JSON +"TEXT NOT NULL"+");";
    final String SQL_UPGRADE_TABLE ="DROP TABLE IF EXISTS " + TaskContract.TaskEntry.TABLE_NAME +";";
    public TaskDbHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_UPGRADE_TABLE);
        onCreate(db);
    }
}
