package com.example.ailatrieuphu.dialog;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

import com.example.ailatrieuphu.OnActionCallBack;
import com.example.ailatrieuphu.R;


public class Help5050Dialog extends Dialog {
    public static final String KEY_BACK_5050 = "KEY_BACK_5050";
    public static final String KEY_YES_5050 = "KEY_YES_5050";
    private final OnActionCallBack callBack;

    public Help5050Dialog(@NonNull Context context, OnActionCallBack callBack) {
        super(context);
        setContentView(R.layout.view_change_5050);
        initViews();
        this.callBack = callBack;

    }

    private void initViews() {
        findViewById(R.id.bt_cancel_5050).setOnClickListener(view -> Help5050Dialog.this.doBack());
        findViewById(R.id.bt_yes_5050).setOnClickListener(view -> doReady());
    }

    private void doBack() {
        callBack.callBack(null, KEY_BACK_5050);
        dismiss();
    }

    private void doReady() {
        callBack.callBack(null, KEY_YES_5050);
        dismiss();
    }
}
