package com.example.ailatrieuphu.dialog;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

import com.example.ailatrieuphu.R;


public class GameOverDialog extends Dialog {

    public GameOverDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.view_game_over);
    }
}
