package com.one4ll.xplayer.interfaces;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\'J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\bH\'J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u0016\u0010\n\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\bH\'\u00a8\u0006\u000b"}, d2 = {"Lcom/one4ll/xplayer/interfaces/ImageDao;", "", "deleteAll", "", "deleteImage", "image", "Lcom/one4ll/xplayer/models/Image;", "getAll", "", "insertImage", "insertImages", "app_debug"})
public abstract interface ImageDao {
    
    @androidx.room.Insert()
    public abstract void insertImage(@org.jetbrains.annotations.NotNull()
    com.one4ll.xplayer.models.Image image);
    
    @androidx.room.Delete()
    public abstract void deleteImage(@org.jetbrains.annotations.NotNull()
    com.one4ll.xplayer.models.Image image);
    
    @androidx.room.Insert()
    public abstract void insertImages(@org.jetbrains.annotations.NotNull()
    java.util.List<com.one4ll.xplayer.models.Image> image);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM image")
    public abstract java.util.List<com.one4ll.xplayer.models.Image> getAll();
    
    @androidx.room.Query(value = "DELETE  FROM image")
    public abstract void deleteAll();
}