package com.example.ailatrieuphu.fragment;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.ailatrieuphu.CommonUtils;
import com.example.ailatrieuphu.MTask;
import com.example.ailatrieuphu.MediaManager;
import com.example.ailatrieuphu.R;
import com.example.ailatrieuphu.activity.MainActivity;
import com.example.ailatrieuphu.base.BaseFragment;
import com.example.ailatrieuphu.databases.QuestionManager;
import com.example.ailatrieuphu.databases.entities.Question;
import com.example.ailatrieuphu.databinding.FrgM003PlayBinding;
import com.example.ailatrieuphu.dialog.CallDialog1;
import com.example.ailatrieuphu.dialog.CallDialog2;
import com.example.ailatrieuphu.dialog.ChangeQuestionDialog;
import com.example.ailatrieuphu.dialog.GameOverDialog;
import com.example.ailatrieuphu.dialog.Help5050Dialog;
import com.example.ailatrieuphu.dialog.HelpAudienceDialog;
import com.example.ailatrieuphu.dialog.HighScoreDialog;
import com.example.ailatrieuphu.dialog.SettingDialog;
import com.example.ailatrieuphu.dialog.StopGameDialog;
import com.example.ailatrieuphu.viewmodel.M003ViewModel;

import java.util.ArrayList;


public class M003PlayFrg extends BaseFragment<FrgM003PlayBinding, M003ViewModel> implements MTask.MTaskListener {
    public static final String TAG = M003PlayFrg.class.getName();
    private static final String KEY_TASK_COUNTING = "KEY_TASK_COUNTING";
    private static final String KEY_TASK_5050 = "KEY_TASK_5050";
    private static final String KEY_TASK_AUDIENCE = "KEY_TASK_AUDIENCE";
    private static final String KEY_TASK_STOP = "KEY_TASK_STOP";
    private static final String KEY_TASK_CHANGE = "KEY_TASK_CHANGE";
    private static final String KEY_TASK_CALL = "KEY_TASK_CALL";
    private final Handler handler = new Handler();
    private final ArrayList<String> coin = new ArrayList<>();
    private MTask task, task5050, taskCall, taskStop, taskChange, taskAudience;
    private Question question;

    @Override
    protected void initViews() {
        mViewModel.addCoin(coin);
        Log.i(TAG, "initViews: " + coin);
        MediaManager.getInstance().playBG(R.raw.song_bg_music);
        mBinding.ivStopGame.setOnClickListener(this);
        mBinding.ivChangeQuestion.setOnClickListener(this);
        mBinding.iv5050.setOnClickListener(this);
        mBinding.ivHelpAudiance.setOnClickListener(this);
        mBinding.ivCall.setOnClickListener(this);
        mBinding.include.ivInfo.setOnClickListener(this);
        mBinding.drawer.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
            }
        });
        mBinding.include.tvCoin.setText("0");
        stateMusic();
        startAsyncTask();
        question1();
    }

    private void stateMusic() {
        if (CommonUtils.getInstance().getPref(SettingDialog.STATE_OF_MUSIC)) {
            MediaManager.getInstance().playSong();
        } else {
            MediaManager.getInstance().pauseSong();
        }
    }

    private void question1() {
        MediaManager.getInstance().playGame(R.raw.song_ques1, mediaPlayer -> {
        });
        getQuestion(1);
    }


    private void getQuestion(int i) {
        QuestionManager.getQuestionManager().getQuestion(i, data -> {
            MainActivity act = (MainActivity) mContext;
            act.runOnUiThread(() -> M003PlayFrg.this.initQuestion(data));
        });
    }

    private void initQuestion(Object data) {
        question = (Question) data;
        String level = String.valueOf(question.level);

        mBinding.ivCaseA.setOnClickListener(view -> M003PlayFrg.this.selectQuestionA());
        mBinding.ivCaseB.setOnClickListener(view -> selectQuestionB());
        mBinding.ivCaseC.setOnClickListener(view -> M003PlayFrg.this.selectQuestionC());
        mBinding.ivCaseD.setOnClickListener(view -> selectQuestionD());

        mBinding.tvQuestion.setText(question.question);
        mBinding.tvLevel.setText(String.format("Câu %s", level));
        mBinding.tvCaseA.setText(String.format("A. %s", question.caseA));
        mBinding.tvCaseB.setText(String.format("B. %s", question.caseB));
        mBinding.tvCaseC.setText(String.format("C. %s", question.caseC));
        mBinding.tvCaseD.setText(String.format("D. %s", question.caseD));
        initScore(question.level);
        mBinding.tvLevel.setTag(question.level);

    }

    private void checkAnswer() {
        if (question.trueCase == 1) {
            mBinding.ivCaseA.setImageLevel(1);
            MediaManager.getInstance().playGame(R.raw.song_lose_a, mediaPlayer -> {
            });
        } else if (question.trueCase == 2) {
            mBinding.ivCaseB.setImageLevel(1);
            MediaManager.getInstance().playGame(R.raw.song_lose_b, mediaPlayer -> {
            });
        } else if (question.trueCase == 3) {
            mBinding.ivCaseC.setImageLevel(1);
            MediaManager.getInstance().playGame(R.raw.song_lose_c, mediaPlayer -> {
            });
        } else if (question.trueCase == 4) {
            mBinding.ivCaseD.setImageLevel(1);
            MediaManager.getInstance().playGame(R.raw.song_lose_d, mediaPlayer -> {
            });
        }
    }

    private void nextQuestion(Question question) {
        mBinding.ivCaseA.setImageLevel(0);
        mBinding.ivCaseB.setImageLevel(0);
        mBinding.ivCaseC.setImageLevel(0);
        mBinding.ivCaseD.setImageLevel(0);

        startAsyncTask();

        int index = question.level;
        getQuestion(++index);
        getSoundQuestion(index);

        mBinding.include.tvCoin.setText(coin.get(index));
        Log.i(TAG, "nextQuestion: " + (index));

        mBinding.include.tvCoin.setTag(coin.get(index));
    }

    private void getSoundQuestion(int index) {
        if (index == 2) {
            MediaManager.getInstance().playGame(R.raw.ques2, mediaPlayer -> {
            });
        } else if (index == 3) {
            MediaManager.getInstance().playGame(R.raw.ques3, mediaPlayer -> {
            });
        } else if (index == 4) {
            MediaManager.getInstance().playGame(R.raw.ques4, mediaPlayer -> {
            });
        } else if (index == 5) {
            MediaManager.getInstance().playGame(R.raw.ques5, mediaPlayer -> {
            });
        } else if (index == 6) {
            MediaManager.getInstance().playGame(R.raw.ques6, mediaPlayer -> {
            });
        } else if (index == 7) {
            MediaManager.getInstance().playGame(R.raw.ques7, mediaPlayer -> {
            });
        } else if (index == 8) {
            MediaManager.getInstance().playGame(R.raw.ques8, mediaPlayer -> {
            });
        } else if (index == 9) {
            MediaManager.getInstance().playGame(R.raw.ques9, mediaPlayer -> {
            });
        } else if (index == 10) {
            MediaManager.getInstance().playGame(R.raw.ques10, mediaPlayer -> {
            });
        } else if (index == 11) {
            MediaManager.getInstance().playGame(R.raw.ques11, mediaPlayer -> {
            });
        } else if (index == 12) {
            MediaManager.getInstance().playGame(R.raw.ques12, mediaPlayer -> {
            });
        } else if (index == 13) {
            MediaManager.getInstance().playGame(R.raw.ques13, mediaPlayer -> {
            });
        } else if (index == 14) {
            MediaManager.getInstance().playGame(R.raw.ques14, mediaPlayer -> {
            });
        } else if (index == 15) {
            MediaManager.getInstance().playGame(R.raw.ques15, mediaPlayer -> {
            });
        }
    }

    private void winGame() {
        MediaManager.getInstance().playGame(R.raw.song_best_player, mediaPlayer -> {
            HighScoreDialog dialog = new HighScoreDialog(mContext, (data, key) -> {
                if (key.equals(HighScoreDialog.KEY_YES_GAME)) {
                    mViewModel.insertHighScore(question, data, mBinding.include.tvCoin.getTag());
                    MainActivity act = (MainActivity) mContext;
                    act.showFrg(M001MainFrg.TAG);
                }
            }, mBinding.include.tvCoin.getTag(), question);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        });
    }

    private void selectQuestionA() {
        stopAsync();
        MediaManager.getInstance().playGame(R.raw.song_ans_a, mediaPlayer -> {
            if (M003PlayFrg.this.checkQuestionA(question)) {
                mBinding.ivCaseA.setImageLevel(2);
                MediaManager.getInstance().playGame(R.raw.song_true_a, mediaPlayer1 -> {
                });
                handler.postDelayed(() -> {
                    if (question.level == 15) {
                        mBinding.include.tvCoin.setText(coin.get(16));
                        winGame();
                        return;
                    }
                    M003PlayFrg.this.nextQuestion(question);
                }, 3000);
            } else {
                checkAnswer();
                mBinding.ivCaseA.setImageLevel(3);
                handler.postDelayed(M003PlayFrg.this::gameOver, 3000);
            }
        });
        mBinding.ivCaseA.setImageLevel(1);
        mBinding.ivCaseB.setClickable(false);
        mBinding.ivCaseC.setClickable(false);
        mBinding.ivCaseD.setClickable(false);
    }

    @SuppressLint("SetTextI18n")
    private void selectQuestionB() {
        stopAsync();
        MediaManager.getInstance().playGame(R.raw.song_ans_b, mediaPlayer -> {
            if (M003PlayFrg.this.checkQuestionB(question)) {
                mBinding.ivCaseB.setImageLevel(2);
                MediaManager.getInstance().playGame(R.raw.song_true_b, mediaPlayer1 -> {
                });
                handler.postDelayed(() -> {
                    if (question.level == 15) {
                        mBinding.include.tvCoin.setText(coin.get(16));
                        winGame();
                        return;
                    }
                    nextQuestion(question);
                }, 3000);
            } else {
                checkAnswer();
                mBinding.ivCaseB.setImageLevel(3);
                handler.postDelayed(this::gameOver, 3000);
            }
        });
        mBinding.ivCaseB.setImageLevel(1);
        mBinding.ivCaseA.setClickable(false);
        mBinding.ivCaseC.setClickable(false);
        mBinding.ivCaseD.setClickable(false);
    }

    private void selectQuestionC() {
        stopAsync();
        MediaManager.getInstance().playGame(R.raw.song_ans_c, mediaPlayer -> {
            if (M003PlayFrg.this.checkQuestionC(question)) {
                mBinding.ivCaseC.setImageLevel(2);
                MediaManager.getInstance().playGame(R.raw.song_true_c, mediaPlayer1 -> {
                });
                handler.postDelayed(() -> {
                    if (question.level == 15) {
                        mBinding.include.tvCoin.setText(coin.get(16));
                        winGame();
                        return;
                    }
                    M003PlayFrg.this.nextQuestion(question);
                }, 3000);
            } else {
                checkAnswer();
                mBinding.ivCaseC.setImageLevel(3);
                handler.postDelayed(M003PlayFrg.this::gameOver, 3000);
            }
        });
        mBinding.ivCaseC.setImageLevel(1);
        mBinding.ivCaseB.setClickable(false);
        mBinding.ivCaseA.setClickable(false);
        mBinding.ivCaseD.setClickable(false);
    }

    private void selectQuestionD() {
        stopAsync();
        MediaManager.getInstance().playGame(R.raw.song_ans_d, mediaPlayer -> {
            if (M003PlayFrg.this.checkQuestionD(question)) {
                mBinding.ivCaseD.setImageLevel(2);
                MediaManager.getInstance().playGame(R.raw.song_true_d, mediaPlayer1 -> {
                });
                handler.postDelayed(() -> {
                    if (question.level == 15) {
                        mBinding.include.tvCoin.setText(coin.get(16));
                        winGame();
                        return;
                    }
                    nextQuestion(question);
                }, 3000);
            } else {
                checkAnswer();
                mBinding.ivCaseD.setImageLevel(3);
                handler.postDelayed(this::gameOver, 3000);
            }
        });
        mBinding.ivCaseD.setImageLevel(1);
        mBinding.ivCaseB.setClickable(false);
        mBinding.ivCaseC.setClickable(false);
        mBinding.ivCaseA.setClickable(false);
    }

    private void gameOver() {
        GameOverDialog dialog = new GameOverDialog(mContext);
        dialog.show();
        final Runnable runnable = () -> {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        };
        dialog.setOnDismissListener(dialogInterface -> handler.removeCallbacks(runnable));
        handler.postDelayed(runnable, 3000);
        MediaManager.getInstance().playGame(R.raw.song_out_of_time, mediaPlayer -> {
            HighScoreDialog dialog1 = new HighScoreDialog(mContext, (data, key) -> {
                if (key.equals(HighScoreDialog.KEY_YES_GAME)) {
                    mViewModel.insertHighScore(question, data, mBinding.include.tvCoin.getTag());
                    MediaManager.getInstance().playGame(R.raw.lose, mediaPlayer1 -> {
                        MainActivity act = (MainActivity) mContext;
                        act.showFrg(M001MainFrg.TAG);
                    });
                }
            }, mBinding.include.tvCoin.getTag(), question);
            dialog1.setCancelable(false);
            dialog1.setCanceledOnTouchOutside(false);
            dialog1.show();
        });
    }

    private void initScore(int level) {
        Log.i(TAG, "initScore: " + level);
        if (level == 1) {
            mBinding.includeInfo.tv200k.setBackgroundResource(R.drawable.ic_current_answer);
        } else if (level == 2) {
            mBinding.includeInfo.tv400k.setBackgroundResource(R.drawable.ic_current_answer);
        } else if (level == 3) {
            mBinding.includeInfo.tv600k.setBackgroundResource(R.drawable.ic_current_answer);
        } else if (level == 4) {
            mBinding.includeInfo.tv1m.setBackgroundResource(R.drawable.ic_current_answer);
        } else if (level == 5) {
            mBinding.includeInfo.iv2m.setImageLevel(1);
        } else if (level == 6) {
            mBinding.includeInfo.tv3m.setBackgroundResource(R.drawable.ic_current_answer);
        } else if (level == 7) {
            mBinding.includeInfo.tv6m.setBackgroundResource(R.drawable.ic_current_answer);
        } else if (level == 8) {
            mBinding.includeInfo.tv10m.setBackgroundResource(R.drawable.ic_current_answer);
        } else if (level == 9) {
            mBinding.includeInfo.tv14m.setBackgroundResource(R.drawable.ic_current_answer);
        } else if (level == 10) {
            mBinding.includeInfo.iv22m.setImageLevel(1);
        } else if (level == 11) {
            mBinding.includeInfo.tv30m.setBackgroundResource(R.drawable.ic_current_answer);
        } else if (level == 12) {
            mBinding.includeInfo.tv40m.setBackgroundResource(R.drawable.ic_current_answer);
        } else if (level == 13) {
            mBinding.includeInfo.tv60m.setBackgroundResource(R.drawable.ic_current_answer);
        } else if (level == 14) {
            mBinding.includeInfo.tv85m.setBackgroundResource(R.drawable.ic_current_answer);
        } else if (level == 15) {
            mBinding.includeInfo.iv150m.setImageLevel(1);
        }
    }


    private boolean checkQuestionD(Question question) {
        return question.trueCase == 4;
    }

    private boolean checkQuestionC(Question question) {
        return question.trueCase == 3;
    }

    private boolean checkQuestionB(Question question) {
        return question.trueCase == 2;
    }

    private boolean checkQuestionA(Question question) {
        return question.trueCase == 1;
    }

    @Override
    protected void clickView(View view) {
        if (view.getId() == R.id.iv_stop_game) {
            stopAsync();
            StopGameDialog dialog = new StopGameDialog(mContext, (data, key) -> {
                if (key.equals(StopGameDialog.KEY_YES_GAME)) {
                    MediaManager.getInstance().playGame(R.raw.lose, mediaPlayer -> gameOver());
                } else if (key.equals(StopGameDialog.KEY_BACK_GAME)) {
                    startStop();
                }
            });
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        } else if (view.getId() == R.id.iv_change_question) {
            stopAsync();
            ChangeQuestionDialog dialog = new ChangeQuestionDialog(mContext, (data, key) -> {
                if (key.equals(ChangeQuestionDialog.KEY_YES_CHANGE)) {
                    startChange();
                    doYesChange();
                } else if (key.equals(ChangeQuestionDialog.KEY_BACK_CHANGE)) {
                    startChange();
                }
            });
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        } else if (view.getId() == R.id.iv_50_50) {
            stopAsync();
            Help5050Dialog dialog = new Help5050Dialog(mContext, (data, key) -> {
                if (key.equals(Help5050Dialog.KEY_YES_5050)) {
                    MediaManager.getInstance().playGame(R.raw.song_50_50, mediaPlayer -> {
                        start5050();
                        M003PlayFrg.this.doYes5050();
                    });
                } else if (key.equals(Help5050Dialog.KEY_BACK_5050)) {
                    start5050();
                }
            });
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();

        } else if (view.getId() == R.id.iv_help_audiance) {
            stopAsync();
            MediaManager.getInstance().playGame(R.raw.song_help_audiance, mediaPlayer -> {
                HelpAudienceDialog dialog = new HelpAudienceDialog(mContext, (data, key) -> {
                    if (key.equals(HelpAudienceDialog.KEY_BUTTON_CANCEL)) {
                        startAudience();
                    }
                }, question);
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                mBinding.ivHelpAudiance.setImageLevel(1);
                mBinding.ivHelpAudiance.setClickable(false);
            });
        } else if (view.getId() == R.id.iv_call) {
            stopAsync();
            MediaManager.getInstance().playGame(R.raw.song_help_call, mediaPlayer -> {
                CallDialog1 dialog1 = new CallDialog1(mContext, (data, key) -> {
                    switch (key) {
                        case CallDialog1.KEY_CALL_ARCHITECT:
                        case CallDialog1.KEY_CALL_INTERVIEW:
                        case CallDialog1.KEY_CALL_DOCTOR:
                        case CallDialog1.KEY_CALL_PROFESSOR:
                            mBinding.ivCall.setImageLevel(1);
                            mBinding.ivCall.setClickable(false);
                            break;
                    }
                }, (id, path, name) -> {
                    CallDialog2 mDialog2 = new CallDialog2(mContext, id, path, name, question, (data, key) -> {
                        if (key.equals(CallDialog2.KEY_BUTTON_BACK)) {
                            startCall();
                        }
                    });
                    mDialog2.setCancelable(false);
                    mDialog2.setCanceledOnTouchOutside(false);
                    mDialog2.show();
                });
                dialog1.show();
            });

        } else if (view.getId() == R.id.iv_info) {
            openDrawer();
        }
    }

    private void stopAsync() {
        stopAsyncTask();
        stop5050AsyncTask();
        stopCallAsyncTask();
        stopChangeAsyncTask();
        stopStopAsyncTask();
        stopCallAudienceAsyncTask();
    }

    private void openDrawer() {
        mBinding.drawer.openDrawer(GravityCompat.START);
    }

    private void doYesChange() {
        mBinding.ivChangeQuestion.setImageLevel(1);
        getQuestion((Integer) mBinding.tvLevel.getTag());
        mBinding.ivChangeQuestion.setClickable(false);
    }

    private void doYes5050() {
        mBinding.iv5050.setImageLevel(1);
        mBinding.iv5050.setClickable(false);
        if (question.trueCase == 1) {
            mBinding.tvCaseB.setText("B. ");
            mBinding.tvCaseB.setClickable(false);
            mBinding.tvCaseC.setText("C. ");
            mBinding.ivCaseC.setClickable(false);
        }
        if (question.trueCase == 2) {
            mBinding.tvCaseA.setText("A. ");
            mBinding.tvCaseA.setClickable(false);
            mBinding.tvCaseD.setText("D. ");
            mBinding.ivCaseD.setClickable(false);
        }
        if (question.trueCase == 3) {
            mBinding.tvCaseB.setText("B. ");
            mBinding.tvCaseB.setClickable(false);
            mBinding.tvCaseD.setText("D. ");
            mBinding.ivCaseD.setClickable(false);
        }
        if (question.trueCase == 4) {
            mBinding.tvCaseA.setText("A. ");
            mBinding.tvCaseA.setClickable(false);
            mBinding.tvCaseC.setText("C. ");
            mBinding.ivCaseC.setClickable(false);
        }
    }

    @Override
    protected Class<M003ViewModel> initViewModel() {
        return M003ViewModel.class;
    }

    @Override
    protected FrgM003PlayBinding initViewBinding(View view) {
        return FrgM003PlayBinding.bind(view);
    }

    @Override
    protected int getLayOutId() {
        return R.layout.frg_m003_play;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void startExecute() {
    }

    private void startAsyncTask() {
        task = new MTask(KEY_TASK_COUNTING, this);
        task.execute(30);
    }

    private void start5050() {
        task5050 = new MTask(KEY_TASK_5050, this);
        task5050.execute(Integer.parseInt(mBinding.include.tvTime.getText().toString()));
    }

    private void startStop() {
        taskStop = new MTask(KEY_TASK_STOP, this);
        taskStop.execute(Integer.parseInt(mBinding.include.tvTime.getText().toString()));
    }

    private void startChange() {
        taskChange = new MTask(KEY_TASK_CHANGE, this);
        taskChange.execute(Integer.parseInt(mBinding.include.tvTime.getText().toString()));
    }

    private void startCall() {
        task5050 = new MTask(KEY_TASK_CALL, this);
        task5050.execute(Integer.parseInt(mBinding.include.tvTime.getText().toString()));
    }

    private void startAudience() {
        taskAudience = new MTask(KEY_TASK_AUDIENCE, this);
        taskAudience.execute(Integer.parseInt(mBinding.include.tvTime.getText().toString()));
    }

    @Override
    public Object executeStart(Object dataInput, String key, MTask task) {
        if (key.equals(KEY_TASK_COUNTING)) return doCounting(dataInput, task);
        if (key.equals(KEY_TASK_5050)) return doCounting(dataInput, task);
        if (key.equals(KEY_TASK_STOP)) return doCounting(dataInput, task);
        if (key.equals(KEY_TASK_CALL)) return doCounting(dataInput, task);
        if (key.equals(KEY_TASK_CHANGE)) return doCounting(dataInput, task);
        if (key.equals(KEY_TASK_AUDIENCE)) return doCounting(dataInput, task);
        return dataInput;
    }

    private Object doCounting(Object dataInput, MTask task) {
        for (int i = (int) dataInput; i >= 0; i--) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Log.e(TAG, "doCounting: Thread bị gián đoạn");
                return false;
            }
            task.requestUpdateUI(i);
        }
        return true;
    }

    @Override
    public void updateUI(Object dataUpdate, String key) {
        switch (key) {
            case KEY_TASK_COUNTING:
                updateCounting((int) dataUpdate);
                break;
            case KEY_TASK_5050:
                updateCounting5050((int) dataUpdate);
                break;
            case KEY_TASK_STOP:
                updateCountingStop((int) dataUpdate);
                break;
            case KEY_TASK_CHANGE:
                updateCountingChange((int) dataUpdate);
                break;
            case KEY_TASK_CALL:
                updateCountingCall((int) dataUpdate);
                break;
            case KEY_TASK_AUDIENCE:
                updateCountingAudience((int) dataUpdate);
                break;
        }
    }

    private void updateCountingChange(int count) {
        mBinding.include.tvTime.setText(String.format("%s", count));
    }

    private void updateCountingStop(int count) {
        mBinding.include.tvTime.setText(String.format("%s", count));
    }

    private void updateCountingCall(int count) {
        mBinding.include.tvTime.setText(String.format("%s", count));
    }

    private void updateCountingAudience(int count) {
        mBinding.include.tvTime.setText(String.format("%s", count));
    }

    private void updateCounting5050(int count) {
        mBinding.include.tvTime.setText(String.format("%s", count));
    }

    private void stopAsyncTask() {
        if (task == null) return;
        task.cancel(true);
        task = null;

    }

    private void stopStopAsyncTask() {
        if (taskStop == null) return;
        taskStop.cancel(true);
        taskStop = null;
    }

    private void stopChangeAsyncTask() {
        if (taskChange == null) return;
        taskChange.cancel(true);
        taskChange = null;
    }

    private void stop5050AsyncTask() {
        if (task5050 == null) return;
        task5050.cancel(true);
        task5050 = null;
    }

    private void stopCallAsyncTask() {
        if (taskCall == null) return;
        taskCall.cancel(true);
        taskCall = null;
    }

    private void stopCallAudienceAsyncTask() {
        if (taskAudience == null) return;
        taskAudience.cancel(true);
        taskAudience = null;
    }


    private void updateCounting(int count) {
        mBinding.include.tvTime.setText(String.format("%s", count));
    }

    @Override
    public void completeTask(Object result, String key) {
        switch (key) {
            case KEY_TASK_COUNTING:
            case KEY_TASK_5050:
            case KEY_TASK_CALL:
            case KEY_TASK_CHANGE:
            case KEY_TASK_STOP:
            case KEY_TASK_AUDIENCE:
                gameOver();
                break;
        }
    }
}
