package com.jm.jlog.simple;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.jm.jlog.LogLevel;
import com.jm.jlog.simple.utils.AppLog;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            logTest();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void logTest() throws JSONException {
        Integer[] array = {1, 2, 3};
        AppLog.v("---------------------------------- v test -------------------------------------------");
        AppLog.v(1);
        AppLog.v("111");
        AppLog.v(array);
        AppLog.v("Error1", new NullPointerException("log test"));
        AppLog.v("ame: %s, age = %d", "Jack", 18);
        AppLog.v(TAG, 1);
        AppLog.v(TAG, "111");
        AppLog.v(TAG, "Error2", new NullPointerException("log test"));
        AppLog.vb(1);
        AppLog.vb("111");
        AppLog.vb(array);
        AppLog.vb("Error3", new NullPointerException("log test"));
        AppLog.vb("ame: %s, age = %d", "Jack", 18);
        AppLog.vb(TAG, 1);
        AppLog.vb(TAG, "111");
        AppLog.vb(TAG, "Error4", new NullPointerException("log test"));

        AppLog.v("---------------------------------- d test -------------------------------------------");

        AppLog.d(1);
        AppLog.d("111");
        AppLog.d(array);
        AppLog.d("Error1", new NullPointerException("log test"));
        AppLog.d("ame: %s, age = %d", "Jack", 18);
        AppLog.d(TAG, 1);
        AppLog.d(TAG, "111");
        AppLog.db(1);
        AppLog.db("111");
        AppLog.db(array);
        AppLog.db("Error3", new NullPointerException("log test"));
        AppLog.db("ame: %s, age = %d", "Jack", 18);
        AppLog.db(TAG, 1);
        AppLog.db(TAG, "111");
        AppLog.db(TAG, "Error4", new NullPointerException("log test"));

        AppLog.v("---------------------------------- i test -------------------------------------------");

        AppLog.i(1);
        AppLog.i("111");
        AppLog.i(array);
        AppLog.i("Error1", new NullPointerException("log test"));
        AppLog.i("ame: %s, age = %d", "Jack", 18);
        AppLog.i(TAG, 1);
        AppLog.i(TAG, "111");
        AppLog.i(TAG, "Error2", new NullPointerException("log test"));
        AppLog.ib(1);
        AppLog.ib("111");
        AppLog.ib(array);
        AppLog.ib("Error3", new NullPointerException("log test"));
        AppLog.ib("ame: %s, age = %d", "Jack", 18);
        AppLog.ib(TAG, 1);
        AppLog.ib(TAG, "111");
        AppLog.ib(TAG, "Error4", new NullPointerException("log test"));

        AppLog.v("---------------------------------- w test -------------------------------------------");

        AppLog.w(1);
        AppLog.w("111");
        AppLog.w(array);
        AppLog.w("Error1", new NullPointerException("log test"));
        AppLog.w("ame: %s, age = %d", "Jack", 18);
        AppLog.w(TAG, 1);
        AppLog.w(TAG, "111");
        AppLog.w(TAG, "Error2", new NullPointerException("log test"));
        AppLog.wb(1);
        AppLog.wb("111");
        AppLog.wb(array);
        AppLog.wb("Error3", new NullPointerException("log test"));
        AppLog.wb("ame: %s, age = %d", "Jack", 18);
        AppLog.wb(TAG, 1);
        AppLog.wb(TAG, "111");
        AppLog.wb(TAG, "Error4", new NullPointerException("log test"));

        AppLog.v("---------------------------------- e test -------------------------------------------");

        AppLog.e(1);
        AppLog.e("111");
        AppLog.e(array);
        AppLog.e("Error1", new NullPointerException("log test"));
        AppLog.e("ame: %s, age = %d", "Jack", 18);
        AppLog.e(TAG, 1);
        AppLog.e(TAG, "111");
        AppLog.e(TAG, "Error2", new NullPointerException("log test"));
        AppLog.eb(1);
        AppLog.eb("111");
        AppLog.eb(array);
        AppLog.eb("Error3", new NullPointerException("log test"));
        AppLog.eb("ame: %s, age = %d", "Jack", 18);
        AppLog.eb(TAG, 1);
        AppLog.eb(TAG, "111");
        AppLog.eb(TAG, "Error4", new NullPointerException("log test"));

        AppLog.v("---------------------------------- b test -------------------------------------------");

        AppLog.b(LogLevel.INFO, 1);
        AppLog.b(LogLevel.INFO, "111");
        AppLog.b(LogLevel.INFO, array);
        AppLog.b(LogLevel.INFO, "Error1", new NullPointerException("log test"));
        AppLog.b(LogLevel.INFO, "ame: %s, age = %d", "Jack", 18);
        AppLog.b(LogLevel.INFO, TAG, 1);
        AppLog.b(LogLevel.INFO, TAG, "111");
        AppLog.b(LogLevel.INFO, TAG, "Error2", new NullPointerException("log test"));

        AppLog.v("---------------------------------- json test -------------------------------------------");

        String json = getResources().getString(R.string.json);
        AppLog.jsonb(TAG, json);

        AppLog.v("---------------------------------- xml test -------------------------------------------");

        String xml = getResources().getString(R.string.xml);
        AppLog.xmlb(TAG, xml);
    }
}