package com.guide.remotecontrole;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static java.lang.reflect.Array.getInt;

public class MainActivity extends AppCompatActivity {
    Button set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(getUserTheme());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        set = findViewById(R.id.settings);

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SetActivity.class);
                startActivity(intent);
            }
        });
    }
    private int getUserTheme() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String userTheme = preferences.getString("pref_theme","Light");
        if (userTheme.equals("Light")) {
            return R.style.AppTheme;
        } else {
            return R.style.AppTheme_Dark;
        }
    }
}
