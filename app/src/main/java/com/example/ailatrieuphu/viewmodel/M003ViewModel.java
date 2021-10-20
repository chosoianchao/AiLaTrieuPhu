package com.example.ailatrieuphu.viewmodel;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.ailatrieuphu.App;
import com.example.ailatrieuphu.databases.dao.QuestionDAO;
import com.example.ailatrieuphu.databases.entities.HighScore;

import java.util.ArrayList;

public class M003ViewModel extends MainViewModel {

    public void addCoin(ArrayList<String> coin) {
        coin.add("0");
        coin.add("0");
        coin.add("200000");
        coin.add("400000");
        coin.add("600000");
        coin.add("1000000");
        coin.add("20000000");
        coin.add("30000000");
        coin.add("60000000");
        coin.add("1000000000");
        coin.add("140000000");
        coin.add("220000000");
        coin.add("300000000");
        coin.add("400000000");
        coin.add("600000000");
        coin.add("850000000");
        coin.add("1500000000");
    }

    public void insertHighScore(Object data, Object tag) {
        String name = (String) data;
        String score = (String) tag;
        if (score == null) {
            score = "0";
        }
        HighScore highScore = new HighScore(name, score);
        new Thread() {
            @Override
            public void run() {
                QuestionDAO dao = App.getInstance().getDb().getDAO();
                dao.insertAll(highScore);
            }
        }.start();
    }
}

