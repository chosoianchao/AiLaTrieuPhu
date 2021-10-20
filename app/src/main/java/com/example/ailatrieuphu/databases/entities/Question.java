package com.example.ailatrieuphu.databases.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "Question", primaryKeys = "_id")
public class Question {
    @ColumnInfo(name = "_id")
    public int id;
    @ColumnInfo(name = "question")
    public String question;
    @ColumnInfo(name = "casea")
    public String caseA;
    @ColumnInfo(name = "caseb")
    public String caseB;
    @ColumnInfo(name = "casec")
    public String caseC;
    @ColumnInfo(name = "cased")
    public String caseD;
    @ColumnInfo(name = "truecase")
    public int trueCase;
    @ColumnInfo(name = "level")
    public int level;

    public Question() {
    }

    @NonNull
    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                "id=" + id + '\'' +
                ", level=" + level +
                ", caseA='" + caseA + '\'' +
                ", caseB='" + caseB + '\'' +
                ", caseC='" + caseC + '\'' +
                ", caseD='" + caseD + '\'' +
                ", truecase=" + trueCase +
                '}';
    }
}

