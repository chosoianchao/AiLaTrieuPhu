package com.example.ailatrieuphu.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

public abstract class BaseFragment<B extends ViewBinding, V extends BaseViewModel> extends Fragment implements View.OnClickListener {
    protected B mBinding;
    protected V mViewModel;
    protected Context mContext;
    protected Object data;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayOutId(), container, false);
        mBinding = initViewBinding(view);
        mViewModel = new ViewModelProvider(this).get(initViewModel());
        initViews();
        return view;
    }

    protected abstract void initViews();

    @Override
    public void onClick(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(mContext, androidx.appcompat.R.anim.abc_fade_in));
        clickView(view);
    }

    protected abstract void clickView(View view);


    protected abstract Class<V> initViewModel();

    protected abstract B initViewBinding(View view);

    protected abstract int getLayOutId();
}
