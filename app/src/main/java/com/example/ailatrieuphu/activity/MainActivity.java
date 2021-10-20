package com.example.ailatrieuphu.activity;

import android.os.Handler;
import android.view.View;

import com.example.ailatrieuphu.MediaManager;
import com.example.ailatrieuphu.R;
import com.example.ailatrieuphu.base.BaseActivity;
import com.example.ailatrieuphu.databinding.ActivityMainBinding;
import com.example.ailatrieuphu.fragment.M001MainFrg;
import com.example.ailatrieuphu.viewmodel.MainViewModel;


public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    @Override
    protected void initViews() {
        new Handler().postDelayed(() -> {
            mBinding.ivLogo.setVisibility(View.GONE);
            mBinding.progressBarLoading.setVisibility(View.GONE);
            showFrg(M001MainFrg.TAG);
        }, 2000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MediaManager.getInstance().pauseSong();
    }

    @Override
    protected void onStart() {
        super.onStart();
        MediaManager.getInstance().playSong();
    }


    @Override
    protected ActivityMainBinding initViewBinding(View view) {
        return ActivityMainBinding.bind(view);
    }

    @Override
    protected int getLayOutId() {
        return R.layout.activity_main;
    }

    @Override
    protected Class<MainViewModel> initViewModel() {
        return MainViewModel.class;
    }

}