package com.example.ailatrieuphu.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.ailatrieuphu.OnActionCallBack;
import com.example.ailatrieuphu.R;
import com.example.ailatrieuphu.databases.entities.Question;


public class CallDialog2 extends Dialog {
    private static final String TAG = CallDialog2.class.getName();
    public static final String KEY_BUTTON_BACK = "KEY_BUTTON_BACK";
    private final int id;
    private final int path;
    private final String name;
    private final Question question;
    private final OnActionCallBack callBack;

    public CallDialog2(@NonNull Context context, int id, int path, String name, Question question, OnActionCallBack callBack) {
        super(context);
        this.question = question;
        this.callBack = callBack;
        setContentView(R.layout.view_call_help_2);
        this.id = id;
        this.path = path;
        this.name = name;
        initViews();
    }


    @SuppressLint("SetTextI18n")
    private void initViews() {
        TextView tvAnswer = findViewById(R.id.tv_answer);
        TextView tvMajor = findViewById(R.id.tv_major);
        ImageView ivAnswer = findViewById(R.id.iv_answer);
        Log.d(TAG, "initViews() called" + id + "---" + path + "---" + name);
        tvMajor.setText(name);
        ivAnswer.setImageResource(path);
        if (question.trueCase == 1) {
            tvAnswer.setText("Theo tôi đáp án đúng là: A");
        } else if (question.trueCase == 2) {
            tvAnswer.setText("Theo tôi đáp án đúng là: B");
        } else if (question.trueCase == 3) {
            tvAnswer.setText("Theo tôi đáp án đúng là: C");
        } else if (question.trueCase == 4) {
            tvAnswer.setText("Theo tôi đáp án đúng là: D");
        }
        findViewById(R.id.bt_come_back).setOnClickListener(view -> doBack());
    }

    private void doBack() {
        callBack.callBack(null,KEY_BUTTON_BACK);
        dismiss();
    }
}
