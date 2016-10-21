package com.example.savingdata;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void save(View view) {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong(getString(R.string.saved_time), System.currentTimeMillis());
        editor.commit();
    }

    public void restore(View view) {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        long savedTime = sharedPref.getLong(getString(R.string.saved_time), 0);
        TextView textView = (TextView)findViewById(R.id.hello_text);
        textView.setText(Long.toString(savedTime));
    }

    public void delete(View view) {

    }
}