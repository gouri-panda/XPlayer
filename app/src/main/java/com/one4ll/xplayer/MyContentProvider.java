package com.one4ll.xplayer;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyContentProvider extends ContentProvider {

    public static UriMatcher uriMatcher;
    private TaskDbHelper taskDbHelper;
    public Context context;
    public final int TASKS  =100;
    public final int TASKS_MID = 101;
    static {
    }

    @Override
    public boolean onCreate() {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(TaskContract.authority, TaskContract.PATH_FAVOURITES, TASKS);
        uriMatcher.addURI(TaskContract.authority,TaskContract.PATH_FAVOURITES+"/#",TASKS_MID);
        taskDbHelper = new TaskDbHelper(context);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        int match = uriMatcher.match(uri);
        SQLiteDatabase db = taskDbHelper.getReadableDatabase();
        Cursor returnCursor ;
        switch (match){
            case TASKS:
                returnCursor = db.query(TaskContract.TaskEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null, null, sortOrder);
                break;
            case TASKS_MID:
                String normalizedString = uri.getLastPathSegment();
                String[] selectionArguments = new String[]{normalizedString};
                returnCursor = db.query(TaskContract.TaskEntry.TABLE_NAME,
                        projection,
                        TaskContract.TaskEntry.COLUMN_ID +" = ? ",
                        selectionArguments,
                        null,
                        null,
                        sortOrder);
            default:
                throw new UnsupportedOperationException("Unknown uri " + uri);

        }
        return returnCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase db = taskDbHelper.getWritableDatabase();
        int match = uriMatcher.match(uri);
        Uri returnUri;
        switch (match){
            case TASKS:
                long id = db.insert(TaskContract.TaskEntry.TABLE_NAME, null, values);
                if (id > 0){
                    returnUri = ContentUris.withAppendedId(TaskContract.TaskEntry.CONTENT_URI,id );
                }else{
                    throw new SQLiteException("Failed "+ uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = taskDbHelper.getWritableDatabase();
        int match = uriMatcher.match(uri);
        int taskDeleted;
        switch(match){
            case TASKS:
                String id = uri.getPathSegments().get(1);
                taskDeleted = db.delete(TaskContract.TaskEntry.TABLE_NAME,
                        TaskContract.TaskEntry.COLUMN_ID+" = ?",
                        new String[]{id});
                break;
            default:
                throw new UnsupportedOperationException("Unknown "+ uri);
        }
        if (taskDeleted != 0){
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return taskDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
