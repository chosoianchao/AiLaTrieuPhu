package com.example.ailatrieuphu.dialog;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

import com.example.ailatrieuphu.OnActionCallBack;
import com.example.ailatrieuphu.R;


public class StopGameDialog extends Dialog {
    public static final String KEY_BACK_GAME = "KEY_BACK_GAME";
    public static final String KEY_YES_GAME = "KEY_YES_GAME";
    private final OnActionCallBack callBack;

    public StopGameDialog(@NonNull Context context, OnActionCallBack callBack) {
        super(context);
        setContentView(R.layout.view_stop_game);
        initViews();
        this.callBack = callBack;

    }

    private void initViews() {
        findViewById(R.id.bt_yes_game).setOnClickListener(view -> doReady());
        findViewById(R.id.bt_cancel_game).setOnClickListener(view -> StopGameDialog.this.doBack());
    }

    private void doBack() {
        callBack.callBack(null, KEY_BACK_GAME);
        dismiss();
    }

    private void doReady() {
        callBack.callBack(null, KEY_YES_GAME);
        dismiss();
    }
}
