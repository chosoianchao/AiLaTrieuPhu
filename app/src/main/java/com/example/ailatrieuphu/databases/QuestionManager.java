package com.example.ailatrieuphu.databases;

import com.example.ailatrieuphu.App;
import com.example.ailatrieuphu.HandlerManager;
import com.example.ailatrieuphu.databases.entities.Question;

public class QuestionManager {
    private static QuestionManager questionManager;

    public QuestionManager() {
        //for singleton
    }

    public static QuestionManager getQuestionManager() {
        if (questionManager == null) {
            questionManager = new QuestionManager();
        }
        return questionManager;
    }

    public void getQuestion(int level, OnResultCallBack callBack) {
        HandlerManager.getINSTANCE().postNewRunnable(() -> {
            Question question = App.getInstance().getDb().getDAO().getQuestion(level);
            callBack.callBack(question);
        });
    }

    public interface OnResultCallBack {
        void callBack(Object data);
    }
}
