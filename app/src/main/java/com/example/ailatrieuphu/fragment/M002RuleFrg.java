package com.example.ailatrieuphu.fragment;


import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.example.ailatrieuphu.CommonUtils;
import com.example.ailatrieuphu.MediaManager;
import com.example.ailatrieuphu.R;
import com.example.ailatrieuphu.activity.MainActivity;
import com.example.ailatrieuphu.base.BaseFragment;
import com.example.ailatrieuphu.databinding.FrgM002RuleBinding;
import com.example.ailatrieuphu.dialog.InfoReadyDialog;
import com.example.ailatrieuphu.dialog.SettingDialog;
import com.example.ailatrieuphu.viewmodel.M002ViewModel;


public class M002RuleFrg extends BaseFragment<FrgM002RuleBinding, M002ViewModel> {
    public static final String TAG = M002RuleFrg.class.getName();

    @Override
    protected void initViews() {

        MediaManager.getInstance().playGame(R.raw.song_rule, mediaPlayer -> MediaManager.getInstance().playGame(R.raw.song_ready, mediaPlayer1 -> M002RuleFrg.this.showReadyDialog()));
        mBinding.milestone.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_left));

        stateMusic();
    }

    private void stateMusic() {
        if (CommonUtils.getInstance().getPref(SettingDialog.STATE_OF_MUSIC)) {
            MediaManager.getInstance().playSong();
        } else {
            MediaManager.getInstance().pauseSong();
            new Handler().postDelayed(this::showReadyDialog, 3000);
        }
    }


    private void showReadyDialog() {

        InfoReadyDialog inform = new InfoReadyDialog(mContext, (data, key) -> {
            if (key.equals(InfoReadyDialog.KEY_BACK)) {
                doBack();
            } else if (key.equals(InfoReadyDialog.KEY_READY)) {
                doReady();
            }
        });
        inform.setCanceledOnTouchOutside(false);
        inform.setCancelable(false);
        inform.show();
    }

    private void doReady() {
        if (!CommonUtils.getInstance().getPref(SettingDialog.STATE_OF_MUSIC)) {
            MainActivity act = (MainActivity) mContext;
            act.showFrg(M003PlayFrg.TAG);
        }else {
            MediaManager.getInstance().playGame(R.raw.song_gofind, mediaPlayer -> {
                MainActivity act = (MainActivity) mContext;
                act.showFrg(M003PlayFrg.TAG);
            });
        }
    }

    private void doBack() {
        MainActivity act  = (MainActivity) mContext;
        act.onBackPressed();
    }

    @Override
    protected void clickView(View view) {
    }

    @Override
    protected Class<M002ViewModel> initViewModel() {
        return M002ViewModel.class;
    }

    @Override
    protected FrgM002RuleBinding initViewBinding(View view) {
        return FrgM002RuleBinding.bind(view);
    }

    @Override
    protected int getLayOutId() {
        return R.layout.frg_m002_rule;
    }
}
