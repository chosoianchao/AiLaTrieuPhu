package com.example.ailatrieuphu.dialog;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

import com.example.ailatrieuphu.OnActionCallBack;
import com.example.ailatrieuphu.R;


public class ChangeQuestionDialog extends Dialog {
    public static final String KEY_BACK_CHANGE = "KEY_BACK_CHANGE";
    public static final String KEY_YES_CHANGE = "KEY_YES_CHANGE";
    private final OnActionCallBack callBack;

    public ChangeQuestionDialog(@NonNull Context context, OnActionCallBack callBack) {
        super(context);
        setContentView(R.layout.view_change_question);
        this.callBack = callBack;
        initViews();
    }

    private void initViews() {
        findViewById(R.id.bt_yes_change).setOnClickListener(view -> doYes());
        findViewById(R.id.bt_cancel_change).setOnClickListener(view -> ChangeQuestionDialog.this.doBack());
    }

    private void doBack() {
        callBack.callBack(null, KEY_BACK_CHANGE);
        dismiss();
    }

    private void doYes() {
        callBack.callBack(null, KEY_YES_CHANGE);
        dismiss();
    }
}
