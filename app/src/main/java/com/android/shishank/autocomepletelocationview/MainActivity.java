package com.android.shishank.autocomepletelocationview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shishank.autocompletelocationview.LocationAutoCompleteView;
import com.shishank.autocompletelocationview.interfaces.OnQueryCompleteListener;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnQueryCompleteListener, OnMapReadyCallback {

    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onTextClear() {
        map.clear();
    }

    @Override
    public void onPlaceSelected(Place selectedPlace) {
        map.clear();
        map.addMarker(new MarkerOptions().position(selectedPlace.getLatLng()));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(selectedPlace.getLatLng(), 16));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;

        LatLng madrid = new LatLng(25.1685, 75.8447);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(madrid, 16));

        LocationAutoCompleteView autoCompleteLocation = findViewById(R.id.autocomplete_view);
        autoCompleteLocation.setOnQueryCompleteListener(this);
    }
}
