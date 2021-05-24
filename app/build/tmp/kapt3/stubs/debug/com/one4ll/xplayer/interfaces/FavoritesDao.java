package com.one4ll.xplayer.interfaces;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H\'J\b\u0010\n\u001a\u00020\bH\'J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u0014\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\r0\u0003H\'J\u0014\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\r0\u0003H\'J\u0010\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H\'\u00a8\u0006\u0010"}, d2 = {"Lcom/one4ll/xplayer/interfaces/FavoritesDao;", "", "getAllById", "Lkotlinx/coroutines/flow/Flow;", "Lcom/one4ll/xplayer/models/Favorite;", "id", "", "insertFavorite", "", "favorite", "removeAll", "removeById", "selectAll", "", "selectAllByTimeInDesc", "updateFavorites", "app_debug"})
public abstract interface FavoritesDao {
    
    @androidx.room.Update(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract void updateFavorites(@org.jetbrains.annotations.NotNull()
    com.one4ll.xplayer.models.Favorite favorite);
    
    @androidx.room.Insert()
    public abstract void insertFavorite(@org.jetbrains.annotations.NotNull()
    com.one4ll.xplayer.models.Favorite favorite);
    
    @androidx.room.Query(value = "DELETE FROM favorite")
    public abstract void removeAll();
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM favorite")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.one4ll.xplayer.models.Favorite>> selectAll();
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM favorite ORDER BY time DESC")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.one4ll.xplayer.models.Favorite>> selectAllByTimeInDesc();
    
    @androidx.room.Query(value = "DELETE FROM favorite WHERE id = :id")
    public abstract void removeById(long id);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM favorite WHERE id = :id")
    public abstract kotlinx.coroutines.flow.Flow<com.one4ll.xplayer.models.Favorite> getAllById(long id);
}