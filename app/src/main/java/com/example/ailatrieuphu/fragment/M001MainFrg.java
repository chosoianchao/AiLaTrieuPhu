package com.example.ailatrieuphu.fragment;

import android.util.Log;
import android.view.View;

import com.example.ailatrieuphu.CommonUtils;
import com.example.ailatrieuphu.MediaManager;
import com.example.ailatrieuphu.R;
import com.example.ailatrieuphu.activity.MainActivity;
import com.example.ailatrieuphu.base.BaseFragment;
import com.example.ailatrieuphu.databinding.FrgM001MainBinding;
import com.example.ailatrieuphu.dialog.InfoDialog;
import com.example.ailatrieuphu.dialog.SettingDialog;
import com.example.ailatrieuphu.viewmodel.M001ViewModel;


public class M001MainFrg extends BaseFragment<FrgM001MainBinding, M001ViewModel> {
    public static final String TAG = M001MainFrg.class.getName();

    @Override
    protected void initViews() {
        MediaManager.getInstance().playBG(R.raw.song_intro);
        mBinding.ivPlay.setOnClickListener(this);
        mBinding.ivInfo.setOnClickListener(this);
        mBinding.ivCup.setOnClickListener(this);
        mBinding.ivSetting.setOnClickListener(this);

    }


    @Override
    protected void clickView(View view) {
        MainActivity act = (MainActivity) mContext;
        if (view.getId() == R.id.iv_play) {
            MediaManager.getInstance().stopBG();
            act.showFrg(M002RuleFrg.TAG, true);
        } else if (view.getId() == R.id.iv_cup) {
            act.showFrg(M004HighScoreFrg.TAG, true);
        } else if (view.getId() == R.id.iv_info) {
            InfoDialog dialog = new InfoDialog(mContext);
            dialog.show();
        } else if (view.getId() == R.id.iv_setting) {
            SettingDialog dialog = new SettingDialog(mContext);
            dialog.show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
        boolean stateOfMusic = CommonUtils.getInstance().getPref(SettingDialog.STATE_OF_MUSIC);
        if(!stateOfMusic ){
            MediaManager.getInstance().pauseSong();
        }
    }

    @Override
    protected Class<M001ViewModel> initViewModel() {
        return M001ViewModel.class;
    }

    @Override
    protected FrgM001MainBinding initViewBinding(View view) {
        return FrgM001MainBinding.bind(view);
    }

    @Override
    protected int getLayOutId() {
        return R.layout.frg_m001_main;
    }
}
