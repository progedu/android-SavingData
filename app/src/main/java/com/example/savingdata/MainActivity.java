package com.example.savingdata;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void save(View view) {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);

    }

    public void restore(View view) {

    }

    public void delete(View view) {

    }
}