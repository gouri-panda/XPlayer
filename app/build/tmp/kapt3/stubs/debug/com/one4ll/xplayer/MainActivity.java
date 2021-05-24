package com.one4ll.xplayer;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005B\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u001f\u001a\u00020 H\u0002J\b\u0010!\u001a\u00020 H\u0002J\u0012\u0010\"\u001a\u00020 2\b\u0010#\u001a\u0004\u0018\u00010$H\u0002J\b\u0010%\u001a\u00020 H\u0002J\b\u0010&\u001a\u00020 H\u0002J\u0010\u0010\'\u001a\u00020 2\u0006\u0010(\u001a\u00020\u0010H\u0016J\u0012\u0010)\u001a\u00020 2\b\u0010*\u001a\u0004\u0018\u00010+H\u0014J\b\u0010,\u001a\u00020 H\u0014J\u0012\u0010-\u001a\u00020.2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\u0012\u0010/\u001a\u00020.2\b\u00100\u001a\u0004\u0018\u00010$H\u0016J\u0012\u00101\u001a\u00020.2\b\u00100\u001a\u0004\u0018\u00010$H\u0016J,\u00102\u001a\u00020.2\b\u00103\u001a\u0004\u0018\u00010$2\b\u00104\u001a\u0004\u0018\u00010$2\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u000206H\u0016J\u0012\u00108\u001a\u00020 2\b\u00100\u001a\u0004\u0018\u00010$H\u0016J\b\u00109\u001a\u00020 H\u0014J,\u0010:\u001a\u00020.2\b\u0010;\u001a\u0004\u0018\u00010$2\b\u0010<\u001a\u0004\u0018\u00010$2\u0006\u0010=\u001a\u0002062\u0006\u0010>\u001a\u000206H\u0017J\u0012\u0010?\u001a\u00020 2\b\u00100\u001a\u0004\u0018\u00010$H\u0016J\u0012\u0010@\u001a\u00020.2\b\u00100\u001a\u0004\u0018\u00010$H\u0016J\u0012\u0010A\u001a\u00020.2\b\u00100\u001a\u0004\u0018\u00010$H\u0016J\u001c\u0010B\u001a\u00020.2\b\u0010C\u001a\u0004\u0018\u00010D2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\b\u0010E\u001a\u00020 H\u0002J\b\u0010F\u001a\u00020 H\u0002R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0013\u0010\f\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082.\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u0019R\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u0019R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006G"}, d2 = {"Lcom/one4ll/xplayer/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Landroid/view/View$OnTouchListener;", "Landroid/view/GestureDetector$OnDoubleTapListener;", "Landroid/view/GestureDetector$OnGestureListener;", "Landroid/media/AudioManager$OnAudioFocusChangeListener;", "()V", "audioManager", "Landroid/media/AudioManager;", "getAudioManager", "()Landroid/media/AudioManager;", "audioManager$delegate", "Lkotlin/Lazy;", "binding", "Lcom/one4ll/xplayer/databinding/ActivityMainBinding;", "brightness", "", "getBrightness", "()I", "brightness$delegate", "eventListener", "Lcom/google/android/exoplayer2/Player$EventListener;", "gestureDetector", "Landroid/view/GestureDetector;", "screenHeight", "Ljava/lang/Integer;", "screenWidth", "simpleExoPlayer", "Lcom/google/android/exoplayer2/SimpleExoPlayer;", "videoUriPath", "", "decreaseBrightness", "", "decreaseSound", "forwardAndBackWardVideoOnDoubleClick", "event", "Landroid/view/MotionEvent;", "increaseBrightness", "increaseSound", "onAudioFocusChange", "focusChange", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onDoubleTap", "", "onDoubleTapEvent", "e", "onDown", "onFling", "e1", "e2", "velocityX", "", "velocityY", "onLongPress", "onPause", "onScroll", "motionEvent1", "motionEvent2", "distanceX", "distanceY", "onShowPress", "onSingleTapConfirmed", "onSingleTapUp", "onTouch", "view", "Landroid/view/View;", "setVideoTitle", "setViews", "app_debug"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity implements android.view.View.OnTouchListener, android.view.GestureDetector.OnDoubleTapListener, android.view.GestureDetector.OnGestureListener, android.media.AudioManager.OnAudioFocusChangeListener {
    private android.view.GestureDetector gestureDetector;
    private java.lang.String videoUriPath;
    private java.lang.Integer screenWidth;
    private java.lang.Integer screenHeight;
    private final kotlin.Lazy audioManager$delegate = null;
    private final kotlin.Lazy brightness$delegate = null;
    private com.one4ll.xplayer.databinding.ActivityMainBinding binding;
    private com.google.android.exoplayer2.SimpleExoPlayer simpleExoPlayer;
    private final com.google.android.exoplayer2.Player.EventListener eventListener = null;
    private java.util.HashMap _$_findViewCache;
    
    private final android.media.AudioManager getAudioManager() {
        return null;
    }
    
    private final int getBrightness() {
        return 0;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setViews() {
    }
    
    private final void setVideoTitle() {
    }
    
    @java.lang.Override()
    public void onAudioFocusChange(int focusChange) {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    @java.lang.Override()
    public boolean onTouch(@org.jetbrains.annotations.Nullable()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.view.MotionEvent event) {
        return false;
    }
    
    @java.lang.Override()
    public boolean onDoubleTap(@org.jetbrains.annotations.Nullable()
    android.view.MotionEvent event) {
        return false;
    }
    
    private final void forwardAndBackWardVideoOnDoubleClick(android.view.MotionEvent event) {
    }
    
    @java.lang.Override()
    public boolean onDoubleTapEvent(@org.jetbrains.annotations.Nullable()
    android.view.MotionEvent e) {
        return false;
    }
    
    @java.lang.Override()
    public boolean onSingleTapConfirmed(@org.jetbrains.annotations.Nullable()
    android.view.MotionEvent e) {
        return false;
    }
    
    @java.lang.Override()
    public void onShowPress(@org.jetbrains.annotations.Nullable()
    android.view.MotionEvent e) {
    }
    
    @java.lang.Override()
    public boolean onSingleTapUp(@org.jetbrains.annotations.Nullable()
    android.view.MotionEvent e) {
        return false;
    }
    
    @java.lang.Override()
    public boolean onDown(@org.jetbrains.annotations.Nullable()
    android.view.MotionEvent e) {
        return false;
    }
    
    @java.lang.Override()
    public boolean onFling(@org.jetbrains.annotations.Nullable()
    android.view.MotionEvent e1, @org.jetbrains.annotations.Nullable()
    android.view.MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    @java.lang.Override()
    public boolean onScroll(@org.jetbrains.annotations.Nullable()
    android.view.MotionEvent motionEvent1, @org.jetbrains.annotations.Nullable()
    android.view.MotionEvent motionEvent2, float distanceX, float distanceY) {
        return false;
    }
    
    private final void decreaseSound() {
    }
    
    private final void increaseSound() {
    }
    
    private final void decreaseBrightness() {
    }
    
    private final void increaseBrightness() {
    }
    
    @java.lang.Override()
    public void onLongPress(@org.jetbrains.annotations.Nullable()
    android.view.MotionEvent e) {
    }
    
    @java.lang.Override()
    protected void onPause() {
    }
    
    public MainActivity() {
        super();
    }
}