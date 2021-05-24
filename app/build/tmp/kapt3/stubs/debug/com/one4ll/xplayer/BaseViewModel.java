package com.one4ll.xplayer;

import java.lang.System;

@kotlinx.coroutines.ExperimentalCoroutinesApi()
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\"J\u0011\u0010#\u001a\u00020$H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\"R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00070\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R&\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0014X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R&\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u001aX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR&\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00070\u0014X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0016\"\u0004\b!\u0010\u0018\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006%"}, d2 = {"Lcom/one4ll/xplayer/BaseViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "app", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_imageUri", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/one4ll/xplayer/Media;", "_streamList", "Lcom/one4ll/xplayer/models/Streams;", "getApp", "()Landroid/app/Application;", "db", "Lcom/one4ll/xplayer/database/MediaDatabase;", "getDb", "()Lcom/one4ll/xplayer/database/MediaDatabase;", "setDb", "(Lcom/one4ll/xplayer/database/MediaDatabase;)V", "imageUri", "Lkotlinx/coroutines/flow/StateFlow;", "getImageUri", "()Lkotlinx/coroutines/flow/StateFlow;", "setImageUri", "(Lkotlinx/coroutines/flow/StateFlow;)V", "musicUriList", "Landroidx/lifecycle/MutableLiveData;", "getMusicUriList", "()Landroidx/lifecycle/MutableLiveData;", "setMusicUriList", "(Landroidx/lifecycle/MutableLiveData;)V", "streamsList", "getStreamsList", "setStreamsList", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMusicListFromStorage", "", "app_debug"})
public final class BaseViewModel extends androidx.lifecycle.AndroidViewModel {
    private kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.one4ll.xplayer.Media>> _imageUri;
    @org.jetbrains.annotations.NotNull()
    private kotlinx.coroutines.flow.StateFlow<? extends java.util.List<com.one4ll.xplayer.Media>> imageUri;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.MutableLiveData<java.util.List<com.one4ll.xplayer.Media>> musicUriList;
    private kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.one4ll.xplayer.models.Streams>> _streamList;
    @org.jetbrains.annotations.NotNull()
    private kotlinx.coroutines.flow.StateFlow<? extends java.util.List<com.one4ll.xplayer.models.Streams>> streamsList;
    @org.jetbrains.annotations.NotNull()
    private com.one4ll.xplayer.database.MediaDatabase db;
    @org.jetbrains.annotations.NotNull()
    private final android.app.Application app = null;
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.one4ll.xplayer.Media>> getImageUri() {
        return null;
    }
    
    public final void setImageUri(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.flow.StateFlow<? extends java.util.List<com.one4ll.xplayer.Media>> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.util.List<com.one4ll.xplayer.Media>> getMusicUriList() {
        return null;
    }
    
    public final void setMusicUriList(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<java.util.List<com.one4ll.xplayer.Media>> p0) {
    }
    
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
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getMusicListFromStorage(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p0) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.app.Application getApp() {
        return null;
    }
    
    public BaseViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application app) {
        super(null);
    }
}