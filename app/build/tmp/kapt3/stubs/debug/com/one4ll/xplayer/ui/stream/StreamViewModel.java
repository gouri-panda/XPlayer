package com.one4ll.xplayer.ui.stream;

import java.lang.System;

@kotlinx.coroutines.ExperimentalCoroutinesApi()
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR&\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014\u00a8\u0006\u0015"}, d2 = {"Lcom/one4ll/xplayer/ui/stream/StreamViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_streamList", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/one4ll/xplayer/models/Streams;", "db", "Lcom/one4ll/xplayer/database/MediaDatabase;", "getDb", "()Lcom/one4ll/xplayer/database/MediaDatabase;", "setDb", "(Lcom/one4ll/xplayer/database/MediaDatabase;)V", "streamsList", "Lkotlinx/coroutines/flow/StateFlow;", "getStreamsList", "()Lkotlinx/coroutines/flow/StateFlow;", "setStreamsList", "(Lkotlinx/coroutines/flow/StateFlow;)V", "app_debug"})
public final class StreamViewModel extends androidx.lifecycle.AndroidViewModel {
    private kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.one4ll.xplayer.models.Streams>> _streamList;
    @org.jetbrains.annotations.NotNull()
    private kotlinx.coroutines.flow.StateFlow<? extends java.util.List<com.one4ll.xplayer.models.Streams>> streamsList;
    @org.jetbrains.annotations.NotNull()
    private com.one4ll.xplayer.database.MediaDatabase db;
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.one4ll.xplayer.models.Streams>> getStreamsList() {
        return null;
    }
    
    public final void setStreamsList(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.flow.StateFlow<? extends java.util.List<com.one4ll.xplayer.models.Streams>> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.one4ll.xplayer.database.MediaDatabase getDb() {
        return null;
    }
    
    public final void setDb(@org.jetbrains.annotations.NotNull()
    com.one4ll.xplayer.database.MediaDatabase p0) {
    }
    
    public StreamViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
}