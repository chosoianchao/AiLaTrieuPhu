package com.example.ailatrieuphu.fragment;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ailatrieuphu.App;
import com.example.ailatrieuphu.CommonUtils;
import com.example.ailatrieuphu.HandlerManager;
import com.example.ailatrieuphu.R;
import com.example.ailatrieuphu.activity.MainActivity;
import com.example.ailatrieuphu.adapter.HighScoreAdapter;
import com.example.ailatrieuphu.base.BaseFragment;
import com.example.ailatrieuphu.databases.dao.QuestionDAO;
import com.example.ailatrieuphu.databases.entities.HighScore;
import com.example.ailatrieuphu.databinding.FrgM004HighscoreBinding;
import com.example.ailatrieuphu.dialog.SettingDialog;
import com.example.ailatrieuphu.viewmodel.M004ViewModel;

import java.util.List;
import java.util.Objects;


public class M004HighScoreFrg extends BaseFragment<FrgM004HighscoreBinding, M004ViewModel> {
    public static final String TAG = M004HighScoreFrg.class.getName();

    @Override
    protected void initViews() {
        updateRecycleView();
        showListHighScore();
        mViewModel.highScore.observe(this, highScores -> {
            HighScoreAdapter adapter = (HighScoreAdapter) mBinding.rvListHighScore.getAdapter();
            assert adapter != null;
            adapter.setData(highScores);
        });
        CommonUtils.getInstance().getPref(SettingDialog.STATE_OF_MUSIC);
        CommonUtils.getInstance().getPref(SettingDialog.STATE_OF_SOUND);
    }

    private void updateRecycleView() {
        if (mViewModel.highScore.getValue() != null) {
            HighScoreAdapter adapter = new HighScoreAdapter(mContext, mViewModel.highScore.getValue());
            mBinding.rvListHighScore.setAdapter(adapter);
            attachSwipeToRV(adapter);
        }
    }

    private void attachSwipeToRV(HighScoreAdapter adapter) {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                HighScore delItem = Objects.requireNonNull(mViewModel.highScore.getValue()).get(position);

                AlertDialog dialog = new AlertDialog.Builder(mContext).create();
                dialog.setTitle("Th??ng b??o");
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
                dialog.setMessage("B???n c?? mu???n x??a d??? li???u: \n" + delItem.name + " " + delItem.score + " " + "?");

                dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Yes", (dialogInterface, i) -> {
                    HandlerManager.getINSTANCE().postNewRunnable(() -> {
                        QuestionDAO dao = App.getInstance().getDb().getDAO();
                        dao.delete(delItem);
                    });
                    Objects.requireNonNull(mViewModel.highScore.getValue()).remove(position);
                    adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                });
                dialog.setButton(AlertDialog.BUTTON_POSITIVE, "No", (dialogInterface, i) -> adapter.notifyItemChanged(position));
                dialog.show();
            }
        }).attachToRecyclerView(mBinding.rvListHighScore);
    }

    private void showListHighScore() {
        HandlerManager.getINSTANCE().postNewRunnable(() -> {
            List<HighScore> listData = App.getInstance().getDb().getDAO().getAll();
            MainActivity act = (MainActivity) mContext;
            act.runOnUiThread(() -> mViewModel.highScore.setValue(listData));
        });
    }


    @Override
    protected void clickView(View view) {

    }

    @Override
    protected Class<M004ViewModel> initViewModel() {
        return M004ViewModel.class;
    }

    @Override
    protected FrgM004HighscoreBinding initViewBinding(View view) {
        return FrgM004HighscoreBinding.bind(view);
    }

    @Override
    protected int getLayOutId() {
        return R.layout.frg_m004_highscore;
    }
}
