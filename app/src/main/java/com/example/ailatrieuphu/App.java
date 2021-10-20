package com.example.ailatrieuphu;

import android.app.Application;

import androidx.room.Room;

import com.example.ailatrieuphu.databases.AppDB;


public class App extends Application {
    private static App instance;
    private Storage storage;
    private AppDB db;

    public static App getInstance() {
        return instance;
    }

    public Storage getStorage() {
        return storage;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        storage = new Storage();
        configDB();
    }

    public AppDB getDb() {
        return db;
    }

    private void configDB() {
        db = Room.databaseBuilder(this, AppDB.class, "Question").createFromAsset("db/Question").build();
    }
}
