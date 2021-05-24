package com.one4ll.xplayer;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0003\u0010\u0005\"\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lcom/one4ll/xplayer/ActionModeCallback;", "Landroidx/appcompat/view/ActionMode$Callback;", "()V", "isSelectAble", "", "()Z", "setSelectAble", "(Z)V", "app_debug"})
public abstract class ActionModeCallback implements androidx.appcompat.view.ActionMode.Callback {
    private boolean isSelectAble;
    
    public final boolean isSelectAble() {
        return false;
    }
    
    public final void setSelectAble(boolean p0) {
    }
    
    public ActionModeCallback() {
        super();
    }
}