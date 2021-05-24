package com.one4ll.xplayer.interfaces;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\'J\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\'J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0006H\'J\u0016\u0010\t\u001a\u00020\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\'\u00a8\u0006\n"}, d2 = {"Lcom/one4ll/xplayer/interfaces/VideoDao;", "", "deleteAll", "", "getAll", "", "Lcom/one4ll/xplayer/models/Video;", "insert", "video", "insertAll", "app_debug"})
public abstract interface VideoDao {
    
    @androidx.room.Insert()
    public abstract void insert(@org.jetbrains.annotations.NotNull()
    com.one4ll.xplayer.models.Video video);
    
    @androidx.room.Insert()
    public abstract void insertAll(@org.jetbrains.annotations.NotNull()
    java.util.List<com.one4ll.xplayer.models.Video> video);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM video")
    public abstract java.util.List<com.one4ll.xplayer.models.Video> getAll();
    
    @androidx.room.Query(value = "DELETE  FROM video")
    public abstract void deleteAll();
}