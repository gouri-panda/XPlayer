package com.one4ll.xplayer;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\u0012\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014J\b\u0010\u000e\u001a\u00020\nH\u0014J-\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112\u000e\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0016\u00a2\u0006\u0002\u0010\u0017J\b\u0010\u0018\u001a\u00020\u0019H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/one4ll/xplayer/AllFilesList;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "mediaDatabase", "Lcom/one4ll/xplayer/database/MediaDatabase;", "thumbnail", "Ljava/io/File;", "videoRecyclerViewAdapter", "Lcom/one4ll/xplayer/adapter/VideoRecyclerViewAdapter;", "getVideoList", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onRequestPermissionsResult", "requestCode", "", "permissions", "", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "readAndWriteExternalStoragePermission", "", "app_debug"})
public final class AllFilesList extends androidx.appcompat.app.AppCompatActivity {
    private java.io.File thumbnail;
    private com.one4ll.xplayer.adapter.VideoRecyclerViewAdapter videoRecyclerViewAdapter;
    private com.one4ll.xplayer.database.MediaDatabase mediaDatabase;
    private java.util.HashMap _$_findViewCache;
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    /**
     * Gets the all video list from internal storage and external storage
     * then it stores in the database
     */
    private final void getVideoList() {
    }
    
    /**
     * Asks permission about read and write  if The device is below marshmallow then  no need to ask
     * we already have permission
     * @return true if we already have permission otherwise it returns false
     */
    private final boolean readAndWriteExternalStoragePermission() {
        return false;
    }
    
    /**
     * After receiving permissions checks it checks  if the permission is granted or not, if we have permission then it gets video list
     */
    @java.lang.Override()
    public void onRequestPermissionsResult(int requestCode, @org.jetbrains.annotations.NotNull()
    java.lang.String[] permissions, @org.jetbrains.annotations.NotNull()
    int[] grantResults) {
    }
    
    /**
     * closes the database after this activity destroys
     */
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    public AllFilesList() {
        super();
    }
}