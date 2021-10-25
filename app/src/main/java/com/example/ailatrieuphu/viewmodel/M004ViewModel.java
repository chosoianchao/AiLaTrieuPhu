package com.example.ailatrieuphu.viewmodel;


import androidx.lifecycle.MutableLiveData;

import com.example.ailatrieuphu.databases.entities.HighScore;

import java.util.ArrayList;
import java.util.List;

public class M004ViewModel extends MainViewModel {
    public MutableLiveData<List<HighScore>> highScore = new MutableLiveData<>(new ArrayList<>());
}
