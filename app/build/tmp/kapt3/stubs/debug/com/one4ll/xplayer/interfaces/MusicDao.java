package com.one4ll.xplayer.interfaces;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\'J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\'J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u0016\u0010\u000b\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\bH\'\u00a8\u0006\f"}, d2 = {"Lcom/one4ll/xplayer/interfaces/MusicDao;", "", "deleteAll", "", "deleteMusic", "image", "Lcom/one4ll/xplayer/models/Music;", "getAll", "", "Lcom/one4ll/xplayer/models/Video;", "insertMusic", "insertMusics", "app_debug"})
public abstract interface MusicDao {
    
    @androidx.room.Insert()
    public abstract void insertMusic(@org.jetbrains.annotations.NotNull()
    com.one4ll.xplayer.models.Music image);
    
    @androidx.room.Delete()
    public abstract void deleteMusic(@org.jetbrains.annotations.NotNull()
    com.one4ll.xplayer.models.Music image);
    
    @androidx.room.Insert()
    public abstract void insertMusics(@org.jetbrains.annotations.NotNull()
    java.util.List<com.one4ll.xplayer.models.Music> image);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM music")
    public abstract java.util.List<com.one4ll.xplayer.models.Video> getAll();
    
    @androidx.room.Query(value = "DELETE  FROM music")
    public abstract void deleteAll();
}