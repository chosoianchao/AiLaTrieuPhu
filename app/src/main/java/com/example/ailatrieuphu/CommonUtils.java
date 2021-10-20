package com.example.ailatrieuphu;

import android.content.Context;
import android.content.SharedPreferences;

public class CommonUtils {
    private static CommonUtils instance;

    public static CommonUtils getInstance() {
        if (instance == null) {
            instance = new CommonUtils();
        }
        return instance;
    }
    public String getPref(String key, boolean isDelete) {
        SharedPreferences pref = App.getInstance().getSharedPreferences("My pref", Context.MODE_PRIVATE);
        String value = pref.getString(key, null);
        if (value != null && isDelete) {
            pref.edit().remove(key).apply();
        }
        return value;
    }

    public String getPref(String key) {
        return getPref(key,false);
    }

    public void savePref(String key, String data) {
        SharedPreferences pref = App.getInstance().getSharedPreferences("My pref", Context.MODE_PRIVATE);
        pref.edit().putString(key, data).apply();
    }
}
