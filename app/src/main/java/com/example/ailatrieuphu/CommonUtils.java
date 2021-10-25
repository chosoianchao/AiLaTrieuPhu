package com.example.ailatrieuphu;

import android.content.Context;
import android.content.SharedPreferences;

public final class CommonUtils {
    private static final String FILE_SAVE = "pref_save";
    private static CommonUtils instance;

    public static CommonUtils getInstance() {
        if (instance == null) {
            instance = new CommonUtils();
        }
        return instance;
    }

    public void savePref(String key, boolean value) {
        SharedPreferences pref = App.getInstance().getSharedPreferences(FILE_SAVE, Context.MODE_PRIVATE);
        pref.edit().putBoolean(key, value).apply();
    }

    public Boolean getPref(String key) {
        SharedPreferences pref = App.getInstance().getSharedPreferences(FILE_SAVE, Context.MODE_PRIVATE);
        return pref.getBoolean(key, true);
    }
}
