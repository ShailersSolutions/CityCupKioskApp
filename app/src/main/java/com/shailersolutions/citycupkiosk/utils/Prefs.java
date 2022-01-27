package com.shailersolutions.citycupkiosk.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {
    private static Prefs instance;
    private static final String PREF_NAME = "CityCupKiosk";

    public static Prefs getInstance() {
        if (instance == null) {
            instance = new Prefs();
        }
        return instance;
    }

    public static void setSharedPreferenceString(Context context, String key, String value) {
        SharedPreferences info = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = info.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getSharedPreferenceString(Context context, String key, String defValue) {
        SharedPreferences info = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return info.getString(key, defValue);
    }

    public static void logout(Context context, String Key) {
        SharedPreferences info = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = info.edit();
        editor.remove(Key);
        editor.commit();

    }

}
