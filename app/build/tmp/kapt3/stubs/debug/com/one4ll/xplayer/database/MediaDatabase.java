package com.one4ll.xplayer.database;

import java.lang.System;

@androidx.room.Database(entities = {com.one4ll.xplayer.models.Video.class, com.one4ll.xplayer.models.Image.class, com.one4ll.xplayer.models.Music.class, com.one4ll.xplayer.models.Streams.class, com.one4ll.xplayer.models.Favorite.class}, version = 2)
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&\u00a8\u0006\u000e"}, d2 = {"Lcom/one4ll/xplayer/database/MediaDatabase;", "Landroidx/room/RoomDatabase;", "()V", "favoritesDao", "Lcom/one4ll/xplayer/interfaces/FavoritesDao;", "imageDao", "Lcom/one4ll/xplayer/interfaces/ImageDao;", "musicDao", "Lcom/one4ll/xplayer/interfaces/MusicDao;", "streamsDao", "Lcom/one4ll/xplayer/interfaces/StreamsDao;", "videoDao", "Lcom/one4ll/xplayer/interfaces/VideoDao;", "Companion", "app_debug"})
public abstract class MediaDatabase extends androidx.room.RoomDatabase {
    private static com.one4ll.xplayer.database.MediaDatabase db;
    public static final com.one4ll.xplayer.database.MediaDatabase.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.one4ll.xplayer.interfaces.VideoDao videoDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.one4ll.xplayer.interfaces.ImageDao imageDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.one4ll.xplayer.interfaces.MusicDao musicDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.one4ll.xplayer.interfaces.StreamsDao streamsDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.one4ll.xplayer.interfaces.FavoritesDao favoritesDao();
    
    public MediaDatabase() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/one4ll/xplayer/database/MediaDatabase$Companion;", "", "()V", "db", "Lcom/one4ll/xplayer/database/MediaDatabase;", "destroyInstance", "", "getInstance", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final com.one4ll.xplayer.database.MediaDatabase getInstance(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
        
        public final void destroyInstance() {
        }
        
        private Companion() {
            super();
        }
    }
}