package com.example.ailatrieuphu.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.example.ailatrieuphu.R;


public abstract class BaseActivity<B extends ViewBinding, V extends BaseViewModel> extends AppCompatActivity implements View.OnClickListener {
    protected B mBinding;
    protected V mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(getLayOutId(), null);
        mBinding = initViewBinding(view);
        mViewModel = new ViewModelProvider(this).get(initViewModel());
        setContentView(view);
        initViews();
    }

    protected abstract void initViews();

    protected abstract B initViewBinding(View view);

    protected abstract int getLayOutId();

    protected abstract Class<V> initViewModel();


    @Override
    public void onClick(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, androidx.appcompat.R.anim.abc_fade_in));
        clickView();
    }

    private void clickView() {

    }

    public void showFrg(String tag) {
        showFrg(R.id.ln_main, tag, null, false);
    }


    public void showFrg(String tag, Boolean isBacked) {
        showFrg(R.id.ln_main, tag, null, isBacked);
    }

    public void showFrg(int layOutId, String tag, Object data, Boolean isBacked) {
        try {
            Class<?> clazz = Class.forName(tag);
            BaseFragment<?, ?> frg = (BaseFragment<?, ?>) clazz.newInstance();
            frg.setData(data);
            FragmentManager frgMgr = getSupportFragmentManager();
            FragmentTransaction frgTrans = frgMgr.beginTransaction();
            frgTrans.replace(layOutId, frg).setCustomAnimations(androidx.appcompat.R.animator.fragment_fade_enter,
                    androidx.appcompat.R.animator.fragment_fade_exit);
            if (isBacked) {
                frgTrans.addToBackStack(null);
            }
            frgTrans.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
