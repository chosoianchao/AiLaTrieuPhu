package com.example.ailatrieuphu.viewmodel;

import com.example.ailatrieuphu.App;
import com.example.ailatrieuphu.HandlerManager;
import com.example.ailatrieuphu.databases.dao.QuestionDAO;
import com.example.ailatrieuphu.databases.entities.HighScore;
import com.example.ailatrieuphu.databases.entities.Question;

import java.util.ArrayList;

public class M003ViewModel extends MainViewModel {

    public void addCoin(ArrayList<String> coin) {
        coin.add("0");
        coin.add("0");
        coin.add("200.000");
        coin.add("400.000");
        coin.add("600.000");
        coin.add("1.000.000");
        coin.add("2.000.000");
        coin.add("3.000.000");
        coin.add("6.000.000");
        coin.add("10.000.000");
        coin.add("14.000.000");
        coin.add("22.000.000");
        coin.add("30.000.000");
        coin.add("40.000.000");
        coin.add("60.000.000");
        coin.add("85.000.000");
        coin.add("150.000.000");
    }

    public void insertHighScore(Question question, Object data, Object tag) {
        String name = (String) data;
        String score = (String) tag;
        if (score == null) {
            score = "0";
        }
        HighScore highScore = new HighScore(name, score);
        if (question.level == 15) {
            highScore = new HighScore(name, "150.000.000");
        }
        HighScore finalHighScore = highScore;
        HandlerManager.getINSTANCE().postNewRunnable(() -> {
            QuestionDAO dao = App.getInstance().getDb().getDAO();
            dao.insertAll(finalHighScore);
        });
    }
}

