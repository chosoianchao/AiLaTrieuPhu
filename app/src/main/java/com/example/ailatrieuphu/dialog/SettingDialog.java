package com.example.ailatrieuphu.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.example.ailatrieuphu.MediaManager;
import com.example.ailatrieuphu.OnActionCallBack;
import com.example.ailatrieuphu.R;


public class SettingDialog extends Dialog implements View.OnClickListener {
    public static final String KEY_MUSIC_ON = "KEY_MUSIC_ON";
    ImageView ivMusic, ivSound;

    public SettingDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.view_setting);
        initViews();

    }

    private void initViews() {
        ivMusic = findViewById(R.id.iv_on_music);
        ivMusic.setOnClickListener(this);


        ivSound = findViewById(R.id.iv_on_sound);
        findViewById(R.id.iv_on_sound).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_on_music) {
            ivMusic.setImageLevel(ivMusic.getDrawable().getLevel() == 0 ? 1 : 0);
            if (ivMusic.getDrawable().getLevel() == 0) {
                MediaManager.getInstance().playSong();
            } else {
                MediaManager.getInstance().pauseSong();
            }
        } else if (view.getId() == R.id.iv_on_sound) {
            ivSound.setImageLevel(ivSound.getDrawable().getLevel() == 0 ? 1 : 0);
            if (ivSound.getDrawable().getLevel() == 0) {
                MediaManager.getInstance().playSong();
            } else {
                MediaManager.getInstance().pauseSong();
            }
        }
    }
}
