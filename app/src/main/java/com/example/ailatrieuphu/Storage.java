package com.example.ailatrieuphu;

import androidx.lifecycle.MutableLiveData;

import com.example.ailatrieuphu.databases.entities.HighScore;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    public MutableLiveData<List<HighScore>> highScore = new MutableLiveData<>(new ArrayList<>());
}
