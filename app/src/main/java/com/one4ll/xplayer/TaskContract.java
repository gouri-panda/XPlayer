package com.one4ll.xplayer;

import android.net.Uri;
import android.provider.BaseColumns;

class TaskContract {
    public static final String authority = "com.one4all.xplayer";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+ authority);
    public static final String PATH_FAVOURITES = "favourites";
    public static class TaskEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_FAVOURITES).build();
        public static final String TABLE_NAME ="favourites";
        public static final String COLUMN_ID ="mid";
        public static final String COLUMN_JSON ="json";
    }
}
