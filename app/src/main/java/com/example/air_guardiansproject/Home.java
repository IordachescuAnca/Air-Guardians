package com.example.air_guardiansproject;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import org.w3c.dom.Text;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class Home extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {


    private static final String TAG = "Map";

    private GoogleMap mMap;

    private final LatLng coordRomania = new LatLng(45.658, 25.6012);
    private final float DEFAULT_ZOOM = 6f;
    private Marker mMarker;



    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(this, "Map is ready", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onMapReady: map is ready");
        mMap = googleMap;
        mMap.setOnMarkerClickListener(this);
        moveCamera(coordRomania, DEFAULT_ZOOM);



        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://mds-project.mysql.database.azure.com:3306/mydb";
            String user = "mdsproject@mds-project";
            String pass = "qwer1234!@#$";
            Connection dbConn = getConnection(url, user, pass);

            try {
                Statement stmt = (Statement) dbConn.createStatement();
                ResultSet res = stmt.executeQuery("Select * from oras_date");
                boolean emailExists = false;
                while (res.next()) {
                    float Lat = res.getFloat("Latitudine");
                    float Long = res.getFloat("Longitudine");
                    int polution = res.getInt("Aqi");
                    String oras = res.getString("Oras");
                    LatLng coord = new LatLng(Lat, Long);
                    if (polution <= 25)
                        mMarker = mMap.addMarker(new MarkerOptions().
                                position(coord).
                                title(oras).
                                icon(BitmapDescriptorFactory.fromResource(R.drawable.zone_green)).
                                alpha(0.6f));
                    else if (polution <= 50)
                        mMarker = mMap.addMarker(new MarkerOptions().
                                position(coord).
                                title(oras).
                                icon(BitmapDescriptorFactory.fromResource(R.drawable.zone_yellow)).
                                alpha(0.6f));
                    else if (polution <= 75)
                        mMarker = mMap.addMarker(new MarkerOptions().
                                position(coord).
                                title(oras).
                                icon(BitmapDescriptorFactory.fromResource(R.drawable.zone_orange)).
                                alpha(0.6f));
                    else if (polution <= 100)
                        mMarker = mMap.addMarker(new MarkerOptions().
                                position(coord).
                                title(oras).
                                icon(BitmapDescriptorFactory.fromResource(R.drawable.zone_red)).
                                alpha(0.6f));


                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            dbConn.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getLocationPermission();

        //Init and Assign Variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.nav_home1);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.info:
                        startActivity(new Intent(getApplicationContext(), InformationActivity.class));
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
                    case R.id.nav_home1:
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
        View myView = getLayoutInflater().inflate(R.layout.popup_favorite, null);


        ImageView heartImg;
        heartImg = myView.findViewById(R.id.heartImg);


        TextView textOras;
        textOras = myView.findViewById(R.id.titleTv);

        textOras.setText(marker.getTitle());
        if(User.favorites.contains(marker.getTitle())) {
            heartImg.setImageResource(R.drawable.ic_favorite_black_24dp);
        }
        else{
            heartImg.setImageResource(R.drawable.ic_favorite_border_black_24dp);
        }

        heartImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(User.favorites.contains(marker.getTitle())) {
                    Toast.makeText(Home.this, "Removed from favorites", Toast.LENGTH_SHORT).show();
                    User.removeFromFavorites(marker.getTitle());
                    heartImg.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                }
                else {
                    Toast.makeText(Home.this, "Added to favorites", Toast.LENGTH_SHORT).show();
                    User.addToFavorites(marker.getTitle());
                    heartImg.setImageResource(R.drawable.ic_favorite_black_24dp);
                }
            }
        });
        builder.setView(myView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


        return false;
    }




    private void initMap() {
        Log.d(TAG, "initMap: initializint map");
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        assert mapFragment != null;
        mapFragment.getMapAsync(Home.this);


    }

    private void moveCamera(LatLng latLng, float zoom) {
        Log.d(TAG, "moveCamera: moving camera to: lat:" + latLng.latitude + ", lng:" + latLng.longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

    }


    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private Boolean mLocationPermissionGranted = false;

    private void getLocationPermission() {
        Log.d(TAG, "getLocationPermission: getting location permissions");
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(), COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionGranted = true;
                initMap();
            } else {
                ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: called.");
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    for (int grantResult : grantResults) {
                        if (grantResult != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionGranted = false;
                            Log.d(TAG, "onRequestPermissionsResult: permission failed");
                            return;
                        }
                    }
                    Log.d(TAG, "onRequestPermissionsResult: permission granted");
                    mLocationPermissionGranted = true;
                    //initialize our amp
                    initMap();
                }
            }
        }

    }
    /*
    public void ShowPopup(){
        dialogBox.setContentView(R.layout.popup_favorite);
        closePopup = (ImageView) dialogBox.findViewById(R.id.closePopupImg);
        favoriteBtn = (Button) dialogBox.findViewById(R.id.btnFavorite);
        titleTv = (TextView) dialogBox.findViewById(R.id.titleTv);

        closePopup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dialogBox.dismiss();
            }
        });
    }*/


}