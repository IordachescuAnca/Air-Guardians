package com.example.air_guardiansproject;

import android.app.AlertDialog;
import android.content.Intent;
import android.hardware.camera2.CameraDevice;
import android.os.StrictMode;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class InformationActivity extends AppCompatActivity {

    CardView team;
    CardView motivation;
    CardView technologies;
    TextView exit_team;
    TextView exit_motivation;
    TextView exit_technologies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        Objects.requireNonNull(getSupportActionBar()).hide();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //Init and Assign Variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.info);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home1:
                        startActivity(new Intent(getApplicationContext(), Home.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.favourites:
                        startActivity(new Intent(getApplicationContext(), FavouritesActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.exit:
                        startActivity(new Intent(getApplicationContext(), LogInActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.info:
                        return true;
                }
                return false;
            }
        });

        team = (CardView)findViewById(R.id.team);
        team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(InformationActivity.this);
                View myView = getLayoutInflater().inflate(R.layout.custom_popup_team, null);
                builder.setView(myView);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        motivation = (CardView)findViewById(R.id.motivation);
        motivation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(InformationActivity.this);
                View myView = getLayoutInflater().inflate(R.layout.custom_popup_motivation, null);
                builder.setView(myView);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        technologies = (CardView)findViewById(R.id.technology);
        technologies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(InformationActivity.this);
                View myView = getLayoutInflater().inflate(R.layout.custom_popup_technologies, null);
                builder.setView(myView);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
}
