package com.nulldreams.beweather.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.nulldreams.beweather.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Properties properties = new Properties();
        try {
            InputStream is = getAssets().open("token");
            properties.load(is);
            String token = properties.getProperty("token");
            Log.v(TAG, "token=" + token);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
