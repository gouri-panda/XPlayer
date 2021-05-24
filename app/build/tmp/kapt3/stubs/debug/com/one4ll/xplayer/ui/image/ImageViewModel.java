package com.one4ll.xplayer.ui.image;

import java.lang.System;

@kotlinx.coroutines.ExperimentalCoroutinesApi()
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR&\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0012"}, d2 = {"Lcom/one4ll/xplayer/ui/image/ImageViewModel;", "Lcom/one4ll/xplayer/BaseViewModel;", "app", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_imageUri", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/one4ll/xplayer/Media;", "getApp", "()Landroid/app/Application;", "imageUri", "Lkotlinx/coroutines/flow/StateFlow;", "getImageUri", "()Lkotlinx/coroutines/flow/StateFlow;", "setImageUri", "(Lkotlinx/coroutines/flow/StateFlow;)V", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class ImageViewModel extends com.one4ll.xplayer.BaseViewModel {
    private kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.one4ll.xplayer.Media>> _imageUri;
    @org.jetbrains.annotations.NotNull()
    private kotlinx.coroutines.flow.StateFlow<? extends java.util.List<com.one4ll.xplayer.Media>> imageUri;
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
    public final android.app.Application getApp() {
        return null;
    }
    
    public ImageViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application app) {
        super(null);
    }
}