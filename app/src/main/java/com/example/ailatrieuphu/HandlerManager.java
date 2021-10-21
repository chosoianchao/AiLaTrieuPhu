package com.example.ailatrieuphu;

import android.os.Handler;
import android.os.HandlerThread;

public class HandlerManager {
    private static volatile HandlerManager INSTANCE;
    private Handler mHandler = null;
    private volatile boolean isCreated = false;

    private HandlerManager() {

    }

    public static HandlerManager getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (HandlerManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HandlerManager();
                }
            }
        }
        return INSTANCE;
    }

    private void init() {
        HandlerThread mHandlerThread = new HandlerThread("ai_la_trieu_phu");
        mHandlerThread.start();
        mHandler = new Handler(mHandlerThread.getLooper());
    }

    public void postNewRunnable(Runnable runnable) {
        if (!isCreated) {
            init();
            isCreated = true;
        }
        mHandler.post(runnable);
    }
}