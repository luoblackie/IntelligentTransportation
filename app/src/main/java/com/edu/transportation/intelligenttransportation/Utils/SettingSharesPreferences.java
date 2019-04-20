package com.edu.transportation.intelligenttransportation.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SettingSharesPreferences {

    private final Context context;

    public SettingSharesPreferences(Context context) {
        this.context = context;
    }

    public void saveData(String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("test", MODE_PRIVATE);
        sharedPreferences.edit().putString(key, value).apply();
    }

    public String loadData(String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("test", MODE_PRIVATE);
        return sharedPreferences.getString(key, value);
    }
}
