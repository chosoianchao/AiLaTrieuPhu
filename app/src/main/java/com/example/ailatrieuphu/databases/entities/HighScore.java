package com.example.ailatrieuphu.databases.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "HighScore")
public class HighScore {
    @ColumnInfo(name = "ID")
    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int iD;

    @NonNull
    @ColumnInfo(name = "Name")
    public String name;

    @ColumnInfo(name = "Score")
    public String score;

    public HighScore() {
    }

    public HighScore(@NonNull String name,String score) {
        this.name = name;
        this.score = score;
    }
}
