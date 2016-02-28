package com.dnsoftindia.swiperefreshlayoutdemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Ganesha on 2/28/2016.
 */
public class AppHelper {

    private static final String SHARED_PREFERENCES_NAME = "SwipeRefreshLayoutDemoPreferences";
    public static final String BASE_URL ="http://www.omdbapi.com/";

    public static void removeSystemValues(Context p_context) {
        SharedPreferences myPrefs = p_context
                .getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        if (myPrefs.getAll().size() > 0) {
            SharedPreferences.Editor prefsEditor = myPrefs.edit();
            prefsEditor.clear();
            prefsEditor.commit();
        }
    }

    public static void setSystemValue(String key, String value, Context p_context) {
        SharedPreferences myPrefs = p_context
                .getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = myPrefs.edit();
        prefsEditor.putString(key, value);
        prefsEditor.commit();

    }

    public static String getSystemValue(String key, Context p_context) {
        String value = null;
        SharedPreferences myPrefs = p_context
                .getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        value = myPrefs.getString(key, null);

        return value;
    }

}
