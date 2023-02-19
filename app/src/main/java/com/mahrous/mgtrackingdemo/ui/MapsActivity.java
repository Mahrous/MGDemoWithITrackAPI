package com.mahrous.mgtrackingdemo.ui;

import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mahrous.mgtrackingdemo.R;
import com.mahrous.mgtrackingdemo.databinding.ActivityMapsBinding;

import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    Intent intent;
    String lat, lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        intent = getIntent();
        lat=intent.getStringExtra("lat");
        lon=intent.getStringExtra("lon");
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(Double.parseDouble(lat), Double.parseDouble(lon));

        CameraPosition INIT = new CameraPosition.Builder().target(sydney).zoom(14.5F).bearing(300F) // orientation
                .tilt(50F) // viewing angle
                .build();

        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(INIT));
        String currentPlace = getAddressFromLatLng(MapsActivity.this, sydney);

        mMap.addMarker(new MarkerOptions().position(sydney).title(currentPlace));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }

    public static String getAddressFromLatLng(Context context, LatLng latLng) {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(context, Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            return addresses.get(0).getAddressLine(0);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}