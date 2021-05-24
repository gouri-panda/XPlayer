package com.one4ll.xplayer;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n\u00a8\u0006\u000f"}, d2 = {"Lcom/one4ll/xplayer/ScaleGestureListener;", "Landroid/view/ScaleGestureDetector$SimpleOnScaleGestureListener;", "scaleFactor", "", "view", "Landroid/view/View;", "(FLandroid/view/View;)V", "getView", "()Landroid/view/View;", "setView", "(Landroid/view/View;)V", "onScale", "", "detector", "Landroid/view/ScaleGestureDetector;", "app_debug"})
public final class ScaleGestureListener extends android.view.ScaleGestureDetector.SimpleOnScaleGestureListener {
    private float scaleFactor;
    @org.jetbrains.annotations.NotNull()
    private android.view.View view;
    
    @java.lang.Override()
    public boolean onScale(@org.jetbrains.annotations.Nullable()
    android.view.ScaleGestureDetector detector) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.view.View getView() {
        return null;
    }
    
    public final void setView(@org.jetbrains.annotations.NotNull()
    android.view.View p0) {
    }
    
    public ScaleGestureListener(float scaleFactor, @org.jetbrains.annotations.NotNull()
    android.view.View view) {
        super();
    }
}