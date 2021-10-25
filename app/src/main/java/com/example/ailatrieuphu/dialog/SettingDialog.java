package com.example.ailatrieuphu.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.example.ailatrieuphu.CommonUtils;
import com.example.ailatrieuphu.MediaManager;
import com.example.ailatrieuphu.R;


public class SettingDialog extends Dialog implements View.OnClickListener {
    public static final String STATE_OF_MUSIC = "state_of_music";
    public static final String STATE_OF_SOUND = "sate_of_sound";
    ImageView ivMusic;

    public SettingDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.view_setting);
        initViews();

    }

    private void initViews() {
        ivMusic = findViewById(R.id.iv_on_music);
        ivMusic.setOnClickListener(this);
        boolean stateOfMusic = CommonUtils.getInstance().getPref(SettingDialog.STATE_OF_MUSIC);

        if(stateOfMusic){
            ivMusic.setImageLevel(0);
        }else{
            ivMusic.setImageLevel(1);
        }
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_on_music) {
            ivMusic.setImageLevel(ivMusic.getDrawable().getLevel() == 0 ? 1 : 0);
            if (ivMusic.getDrawable().getLevel() == 0) {
                MediaManager.getInstance().playSong();
                CommonUtils.getInstance().savePref(STATE_OF_MUSIC, true);
            } else {
                MediaManager.getInstance().pauseSong();
                CommonUtils.getInstance().savePref(STATE_OF_MUSIC, false);
            }
        }
    }
}
