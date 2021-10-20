package com.example.ailatrieuphu.dialog;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

import com.example.ailatrieuphu.R;


public class InfoDialog extends Dialog {

    public InfoDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.view_info_game);
    }
}
