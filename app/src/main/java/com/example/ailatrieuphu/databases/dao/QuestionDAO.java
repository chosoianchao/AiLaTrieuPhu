package com.example.ailatrieuphu.databases.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.ailatrieuphu.databases.entities.HighScore;
import com.example.ailatrieuphu.databases.entities.Question;

import java.util.List;

@Dao
public interface QuestionDAO {

    @Query("SELECT*FROM Question WHERE level =:level ORDER BY RANDOM() LIMIT 1")
    Question getQuestion(int level);

    @Insert
    void insertAll(HighScore... highScores);

    @Query("SELECT *FROM HighScore")
    List<HighScore> getAll();
    @Delete
    void delete(HighScore item);
}
