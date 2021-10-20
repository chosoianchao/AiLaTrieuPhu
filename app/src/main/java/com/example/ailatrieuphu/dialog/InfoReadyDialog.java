package com.example.ailatrieuphu.dialog;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

import com.example.ailatrieuphu.OnActionCallBack;
import com.example.ailatrieuphu.R;


public class InfoReadyDialog extends Dialog {
    public static final String KEY_BACK = "KEY_BACK";
    public static final String KEY_READY = "KEY_READY";
    private final OnActionCallBack callBack;

    public InfoReadyDialog(@NonNull Context context, OnActionCallBack callBack) {
        super(context);
        setContentView(R.layout.view_ready);
        this.callBack = callBack;
        initViews();
    }

    private void initViews() {
        findViewById(R.id.bt_ready).setOnClickListener(view -> doReady());
        findViewById(R.id.bt_cancel).setOnClickListener(view -> InfoReadyDialog.this.doBack());
    }

    private void doBack() {
        callBack.callBack(null, KEY_BACK);
        dismiss();
    }

    private void doReady() {
        callBack.callBack(null, KEY_READY);
        dismiss();
    }
}
