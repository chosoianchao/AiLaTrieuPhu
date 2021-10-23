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

    public void savePref(String key, String value) {
        SharedPreferences pref = App.getInstance().getSharedPreferences(FILE_SAVE, Context.MODE_PRIVATE);
        pref.edit().putString(key, value).apply();
    }

    public String getPref(String key) {
        SharedPreferences pref = App.getInstance().getSharedPreferences(FILE_SAVE, Context.MODE_PRIVATE);
        return pref.getString(key, null);
    }

    public void clearPref(String key) {
        SharedPreferences pref = App.getInstance().getSharedPreferences(FILE_SAVE, Context.MODE_PRIVATE);
        pref.edit().remove(key).apply();
    }
}
