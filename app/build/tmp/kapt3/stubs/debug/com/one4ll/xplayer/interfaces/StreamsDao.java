package com.one4ll.xplayer.interfaces;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000b\bg\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\'J\u001c\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0007\u001a\u00020\bH\'J\u0019\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ\u0011\u0010\r\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u0019\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J\u0019\u0010\u0012\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ!\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0015"}, d2 = {"Lcom/one4ll/xplayer/interfaces/StreamsDao;", "", "getAllByTime", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/one4ll/xplayer/models/Streams;", "getValueBYId", "time", "", "insert", "", "stream", "(Lcom/one4ll/xplayer/models/Streams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "removeAll", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "removeById", "id", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "updateTime", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface StreamsDao {
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Insert()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.one4ll.xplayer.models.Streams stream, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p1);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Update(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.one4ll.xplayer.models.Streams stream, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p1);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM streams ORDER BY time DESC")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.one4ll.xplayer.models.Streams>> getAllByTime();
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Query(value = "DELETE  FROM streams")
    public abstract java.lang.Object removeAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p0);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Query(value = "DELETE  FROM streams WHERE id = :id")
    public abstract java.lang.Object removeById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p1);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM streams WHERE time = :time")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.one4ll.xplayer.models.Streams>> getValueBYId(long time);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Query(value = "UPDATE streams SET time = :time WHERE id = :id")
    public abstract java.lang.Object updateTime(long id, long time, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p2);
}