package com.example.ailatrieuphu.databases;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.ailatrieuphu.databases.dao.QuestionDAO;
import com.example.ailatrieuphu.databases.entities.HighScore;
import com.example.ailatrieuphu.databases.entities.Question;


@Database(entities = {Question.class, HighScore.class}, version = 1)
public abstract class AppDB extends RoomDatabase {
    public abstract QuestionDAO getDAO();
}
