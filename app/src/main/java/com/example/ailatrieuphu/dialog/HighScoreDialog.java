package com.example.ailatrieuphu.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.ailatrieuphu.App;
import com.example.ailatrieuphu.OnActionCallBack;
import com.example.ailatrieuphu.R;
import com.example.ailatrieuphu.databases.entities.Question;


public class HighScoreDialog extends Dialog {
    public static final String KEY_YES_GAME = "KEY_YES_GAME";
    private static final String TAG = HighScoreDialog.class.getName();
    private final OnActionCallBack callBack;
    private final Object coin;
    private final Question question;
    private EditText edtName;

    public HighScoreDialog(@NonNull Context context, OnActionCallBack callBack, Object coin, Question question) {
        super(context);
        setContentView(R.layout.view_high_score);
        this.callBack = callBack;
        this.question = question;
        this.coin = coin;
        initViews();
    }

    @SuppressLint("SetTextI18n")
    private void initViews() {
        edtName = findViewById(R.id.edt_name);
        TextView tvScore = findViewById(R.id.tv_score);
        if (coin == null) {
            tvScore.setText("0");
        } else if (question.level == 15) {
            tvScore.setText("150.000.000");
        } else {
            tvScore.setText((String) coin);
        }
        Log.i(TAG, "initViews: " + coin);
        findViewById(R.id.bt_yes_high_score).setOnClickListener(view -> doReady());
    }

    private void doReady() {
        String name = edtName.getText().toString();
        if (edtName.getText().toString().isEmpty()) {
            Toast.makeText(App.getInstance(), "Hãy nhập tên của bạn ", Toast.LENGTH_SHORT).show();
            return;
        }
        callBack.callBack(name, KEY_YES_GAME);
        dismiss();
    }

}

