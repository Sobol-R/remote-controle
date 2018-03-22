package com.guide.remotecontrole;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;

/**
 * Created by User on 22.03.2018.
 */

public class Utils {
    public static int setColor(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int color = preferences.getInt("pref_color", Color.BLUE);
        return color;
    }
}
