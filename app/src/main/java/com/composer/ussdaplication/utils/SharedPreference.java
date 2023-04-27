package com.composer.ussdaplication.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.composer.ussdaplication.R;


public class SharedPreference {
    SharedPreferences prefs;
    private static SharedPreference sharePreference;
    SharedPreferences.Editor editor;

    public static SharedPreference getInstance(Context context) {
        if (sharePreference != null)
            return sharePreference;
        else return sharePreference = new SharedPreference(context);
    }

    private SharedPreference(Context context) {
        prefs = context.getSharedPreferences(getClass().getName(), Context.MODE_PRIVATE);
    }

    public void setLang(String lang) {
        editor = prefs.edit();
        editor.putString("lang", lang);
        editor.apply();
    }

    public String getLang() {
        return prefs.getString("lang", "uz");
    }

    public void setSecondLang(String lang) {
        editor = prefs.edit();
        editor.putString("langSecond", lang);
        editor.apply();
    }

    public String getSecondLang() {
        return prefs.getString("langSecond", "uz");
    }


    public void setToken(String token) {
        editor = prefs.edit();
        editor.putString("token", token);
        editor.apply();
    }

    public String getToken() {
        return prefs.getString("token", "");
    }

    public void setHasLang(boolean hasLang) {
        editor = prefs.edit();
        editor.putBoolean("hasLang", hasLang);
        editor.apply();
    }

    public boolean getHasLang() {
        return prefs.getBoolean("hasLang", false);
    }

    public void setOperator(String operator) {
        editor = prefs.edit();
        editor.putString("operator", operator);
        editor.apply();
    }

    public String getOperator() {
        return prefs.getString("operator", "beeline");
    }

    public void setOperatorBalanceCode(String operator) {
        editor = prefs.edit();
        editor.putString("balance", operator);
        editor.apply();
    }

    public String getOperatorBalanceCode() {
        return prefs.getString("balance", "balance");
    }

    public void setOperatorColor(Integer color) {
        editor = prefs.edit();
        editor.putInt("color", color);
        editor.apply();
    }

    public Integer getOperatorColor() {
        return prefs.getInt("color", R.color.beeline);
    }
}
