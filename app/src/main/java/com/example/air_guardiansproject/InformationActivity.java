package com.example.air_guardiansproject;

import android.os.StrictMode;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Objects;

public class InformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        Objects.requireNonNull(getSupportActionBar()).hide();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
}
