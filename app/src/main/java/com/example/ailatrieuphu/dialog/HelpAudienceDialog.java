package com.example.ailatrieuphu.dialog;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.ailatrieuphu.OnActionCallBack;
import com.example.ailatrieuphu.R;
import com.example.ailatrieuphu.databases.entities.Question;

import java.util.Random;


public class HelpAudienceDialog extends Dialog {
    private static final String TAG = HelpAudienceDialog.class.getName();
    public static final String KEY_BUTTON_CANCEL = "KEY_BUTTON_CANCEL";
    private final OnActionCallBack callBack;
    private final Question question;

    public HelpAudienceDialog(@NonNull Context context, OnActionCallBack callBack, Question question) {
        super(context);
        setContentView(R.layout.view_call_audiance);
        this.callBack = callBack;
        this.question = question;
        initViews();
    }

    private void initViews() {
        Random random = new Random();
        int x = random.nextInt(100) + 1;
        int y = random.nextInt(100) + 1;
        int z = random.nextInt(100) + 1;
        Log.i(TAG, "initViews: " + question.trueCase + " " + x + y + z);
        View chartA = findViewById(R.id.chart_A);
        View chartB = findViewById(R.id.chart_B);
        View chartC = findViewById(R.id.chart_C);
        View chartD = findViewById(R.id.chart_D);
        findViewById(R.id.bt_cancel).setOnClickListener(view -> {
            callBack.callBack(null, KEY_BUTTON_CANCEL);
            dismiss();
        });

        if (question.trueCase == 1) {
            chartA.getLayoutParams().height = 100;
            chartB.getLayoutParams().height = x;
            chartC.getLayoutParams().height = y;
            chartD.getLayoutParams().height = z;
        }
        if (question.trueCase == 2) {
            chartA.getLayoutParams().height = x;
            chartB.getLayoutParams().height = 100;
            chartC.getLayoutParams().height = y;
            chartD.getLayoutParams().height = z;
        }
        if (question.trueCase == 3) {
            chartA.getLayoutParams().height = x;
            chartB.getLayoutParams().height = y;
            chartC.getLayoutParams().height = 100;
            chartD.getLayoutParams().height = z;
        }
        if (question.trueCase == 4) {
            chartA.getLayoutParams().height = x;
            chartB.getLayoutParams().height = y;
            chartC.getLayoutParams().height = z;
            chartD.getLayoutParams().height = 100;
        }
    }
}

