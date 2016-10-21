package com.example.savingdata;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private final String filename = "myfile";

    public void save(View view) {
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            String string = "Hello Android! at " + System.currentTimeMillis();
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void restore(View view) {
        FileInputStream inputStream;
        try {
            inputStream = openFileInput(filename);
            BufferedReader br =
                    new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            TextView textView = (TextView)findViewById(R.id.hello_text);
            textView.setText(sb.toString());
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(View view) {

    }
}