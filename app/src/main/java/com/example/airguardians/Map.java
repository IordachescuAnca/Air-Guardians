package com.example.airguardians;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
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

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;

public class Map extends AppCompatActivity implements OnMapReadyCallback {


    private static final String TAG = "Map";

    private GoogleMap mMap;

    private final LatLng coordBucharest = new LatLng(44.435180, 26.102720);
    private final LatLng coordUniversitate = new LatLng(44.43513,26.0988712);
    private final LatLng coordGrozavesti = new LatLng(44.4425814,26.0573709);
    private final LatLng coordAnca = new LatLng(44.4120789,26.1818769);
    private final LatLng coordRegie = new LatLng(44.447564,26.0500859);
    private final float DEFAULT_ZOOM = 11.5f;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(this, "Map is ready", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onMapReady: map is ready");
        mMap = googleMap;
        moveCamera(coordBucharest, DEFAULT_ZOOM);

        Marker zone1 = mMap.addMarker(new MarkerOptions().position(coordUniversitate).icon(BitmapDescriptorFactory.fromResource(R.drawable.zone_red)).alpha(0.7f));
        Marker zone2 = mMap.addMarker(new MarkerOptions().position(coordGrozavesti).icon(BitmapDescriptorFactory.fromResource(R.drawable.zone_orange)).alpha(0.7f));
        Marker zone3 = mMap.addMarker(new MarkerOptions().position(coordRegie).icon(BitmapDescriptorFactory.fromResource(R.drawable.zone_yellow)).alpha(0.7f));
        Marker zone4 = mMap.addMarker(new MarkerOptions().position(coordAnca).icon(BitmapDescriptorFactory.fromResource(R.drawable.zone_green)).alpha(0.7f));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_map);


        getLocationPermission();

    }

    private void initMap() {
        Log.d(TAG, "initMap: initializint map");
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        assert mapFragment != null;
        mapFragment.getMapAsync(Map.this);



    }
    private void moveCamera(LatLng latLng, float zoom){
        Log.d(TAG, "moveCamera: moving camera to: lat:" + latLng.latitude + ", lng:" + latLng.longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }


    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private Boolean mLocationPermissionGranted = false;

    private void getLocationPermission(){
        Log.d(TAG, "getLocationPermission: getting location permissions");
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(), FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(), COARSE_LOCATION)== PackageManager.PERMISSION_GRANTED){
                mLocationPermissionGranted = true;
                initMap();
            }
            else{
                ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
            }
        }
        else{
            ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: called.");
        mLocationPermissionGranted = false;

        switch (requestCode){
            case LOCATION_PERMISSION_REQUEST_CODE:{
                if(grantResults.length > 0 ){
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


}