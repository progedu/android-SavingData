package com.example.savingdata;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
            outputStream = new FileOutputStream(getStorageDocumentsDir(filename));
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
            inputStream = new FileInputStream(getStorageDocumentsDir(filename));
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

    public File getStorageDocumentsDir(String filename) {
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS), filename);
        return file;
    }

    public void delete(View view) {
        File file = getStorageDocumentsDir(filename);
        Log.d("MainActivity", "getFreeSpace: " + file.getFreeSpace());
        file.delete();
    }

    public void insertDb(View view) {
        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(this);
        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        long lastId = System.currentTimeMillis() / 1000;
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ENTRY_ID, lastId);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, "タイトル");
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_CONTENT, "内容");

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(
                FeedReaderContract.FeedEntry.TABLE_NAME,
                "",
                values);

        Log.d("newRowId", Long.toString(newRowId));
    }

    public void selectDb(View view) {
        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Cursor c = db.query(FeedReaderContract.FeedEntry.TABLE_NAME,
                new String[]{
                        FeedReaderContract.FeedEntry.COLUMN_NAME_ENTRY_ID,
                        FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE,
                        FeedReaderContract.FeedEntry.COLUMN_NAME_CONTENT
                },
                "",
                new String[0],
                "",
                "",
                "");


        List<String> records = new ArrayList<>();
        while(c.moveToNext()) {
            String record = c.getString(0) + "," +  c.getString(1)  + "," +  c.getString(2) + "\r\n";
            records.add(record);
        }

        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                records));
    }

    public void deleteDb(View view) {

    }

    public void updateDb(View view) {

    }
}
