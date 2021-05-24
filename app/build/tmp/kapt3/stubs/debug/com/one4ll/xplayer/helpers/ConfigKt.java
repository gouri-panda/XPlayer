package com.one4ll.xplayer.helpers;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 2, d1 = {"\u00006\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0006\u0010\b\u001a\u00020\t\u001a\u0006\u0010\n\u001a\u00020\t\u001a\u0006\u0010\u000b\u001a\u00020\t\u001a\u0006\u0010\f\u001a\u00020\t\u001a\u0006\u0010\r\u001a\u00020\t\u001a\u0006\u0010\u000e\u001a\u00020\t\u001a/\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0012\u0010\u0013\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0014\"\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0016\u001a\u001e\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0006\u001a\u0016\u0010\u0019\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0018\u001a\u00020\u0001\u001a\u0006\u0010\u001c\u001a\u00020\t\u001a\u0006\u0010\u001d\u001a\u00020\t\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"IMAGE_PATH", "", "IS_GRID_LAYOUT", "MUSIC_PATH", "SHARED_PREF_SETTINGS", "SKIP_DURATION", "", "VIDEO_PATH", "IS_MARSHMALLOW_OR_LETTER", "", "IS_NOUGHOT_MR1_OR_LETTER", "IS_NOUGHOT_OR_LETTER", "IS_OREO_OR_LETTER", "IS_PIE_OR_LETTER", "IS_Q_OR_LETTER", "askPermission", "", "activity", "Landroid/app/Activity;", "permissions", "", "permissionId", "(Landroid/app/Activity;[Ljava/lang/String;I)V", "handlePermission", "permission", "havePermission", "context", "Landroid/content/Context;", "isExternalMounted", "isExternalOnlyMounted", "app_debug"})
public final class ConfigKt {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String VIDEO_PATH = "video_path";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String IMAGE_PATH = "image_path";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String MUSIC_PATH = "music_path";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SHARED_PREF_SETTINGS = "shared_pref_settings";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String IS_GRID_LAYOUT = "is_grid_layout";
    public static final int SKIP_DURATION = 5000;
    
    public static final boolean IS_MARSHMALLOW_OR_LETTER() {
        return false;
    }
    
    public static final boolean IS_NOUGHOT_OR_LETTER() {
        return false;
    }
    
    public static final boolean IS_NOUGHOT_MR1_OR_LETTER() {
        return false;
    }
    
    public static final boolean IS_OREO_OR_LETTER() {
        return false;
    }
    
    public static final boolean IS_PIE_OR_LETTER() {
        return false;
    }
    
    public static final boolean IS_Q_OR_LETTER() {
        return false;
    }
    
    public static final boolean havePermission(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String permission) {
        return false;
    }
    
    public static final void askPermission(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    java.lang.String[] permissions, int permissionId) {
    }
    
    public static final boolean handlePermission(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    java.lang.String permission, int permissionId) {
        return false;
    }
    
    public static final boolean isExternalMounted() {
        return false;
    }
    
    public static final boolean isExternalOnlyMounted() {
        return false;
    }
}