package com.example.hellosharedpreferences;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    //SharedPreferences object
    private SharedPreferences mPreferences;
    //SharedPreferences file name
    private String sharedPrefFile =
            "com.example.android.hellosharedprefs";
    //Keys for color and count variables
    private final static String COUNT_KEY = "count";
    private final static String COLOR_KEY = "color";
    //Count variable
    private int count = 0;
    //Color variable
    private int color;
    //Count TextView
    private TextView mCountTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find count TextView
        mCountTV = findViewById(R.id.count_tv);

        //Initialize color
        color = ContextCompat.getColor(this,
                R.color.default_background);

        //Initialize shared preferences
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        //Restore preferences
        count = mPreferences.getInt(COUNT_KEY, 0);
        mCountTV.setText(String.format("%s", count));
        color = mPreferences.getInt(COLOR_KEY, color);
        mCountTV.setBackgroundColor(color);

    }

    @Override
    protected void onPause() {
        super.onPause();

        //Get an Editor for the SharedPreferences object
        SharedPreferences.Editor prefsEditor = mPreferences.edit();

        //Put count and color variable into the shared preferences
        prefsEditor.putInt(COUNT_KEY, count);
        prefsEditor.putInt(COLOR_KEY, color);

        //Apply the changes
        prefsEditor.apply();
    }

    public void changeBackground(View view) {
        //Get the background color of the button
        color = ((ColorDrawable) view.getBackground()).getColor();
        //Set the text view color
        mCountTV.setBackgroundColor(color);
    }

    public void addToCount(View v) {
        //Add 1 to counter and display that to the user
        mCountTV.setText(String.valueOf(++count));
    }

    public void resetCounter(View v) {
        //Reset counter to 0
        count = 0;

        //Set the text view text
        mCountTV.setText(String.valueOf(count));

        //Set text view background color to the default one (gray)
        color = ContextCompat.getColor(this,
                R.color.default_background);
        mCountTV.setBackgroundColor(color);

        //Get an Editor for the SharedPreferences object
        SharedPreferences.Editor prefsEditor = mPreferences.edit();

        //Delete all shared preferences
        prefsEditor.clear();

        //Apply changes
        prefsEditor.apply();
    }
}