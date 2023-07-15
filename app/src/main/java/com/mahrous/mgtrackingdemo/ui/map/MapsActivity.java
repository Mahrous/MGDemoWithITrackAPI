package com.mahrous.mgtrackingdemo.ui.map;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mahrous.mgtrackingdemo.R;
import com.mahrous.mgtrackingdemo.data.TrackingResponse;
import com.mahrous.mgtrackingdemo.databinding.ActivityMapsBinding;
import com.mahrous.mgtrackingdemo.ui.addDevice.AddDeviceViewModel;

import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    Intent intent;
    double lat;
    double lon;
    String accessToken;
    String serial;
    MapsActivityViewModel viewModel ;
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

        accessToken=intent.getStringExtra("accessToken");
        serial=intent.getStringExtra("serial");

        setUpComponent();




    }

    private void setUpComponent() {
        viewModel = ViewModelProviders.of(this).get(MapsActivityViewModel.class);
        viewModel.tracking(accessToken, serial);
        viewModel.mutableLiveDataTracking.observeForever(new Observer<Object>() {
            /**
             * Called when the data is changed.
             *
             * @param o The new data
             */
            @Override
            public void onChanged(Object o) {
                if (o instanceof TrackingResponse) {
                    if (((TrackingResponse) o).getCode()==10012){
                        Log.e("TAG","Access Token is invalid, please restart app");

                    } else if (((TrackingResponse) o).getCode()==0){


                        lat=((TrackingResponse) o).getRecord().get(0).getLatitude();
                        lon= ((TrackingResponse) o).getRecord().get(0).getLongitude();

                        LatLng sydney = new LatLng(lat, lon);

                        CameraPosition INIT = new CameraPosition.Builder().target(sydney).zoom(14.5F).bearing(300F) // orientation
                                .tilt(50F) // viewing angle
                                .build();

                        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(INIT));
                        String currentPlace = getAddressFromLatLng(MapsActivity.this, sydney);

                        mMap.addMarker(new MarkerOptions().position(sydney).title(currentPlace));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


                        Log.e("TAG", "onChanged: " + "  I am At Check Serial -> la =" +  lat +" lon = " +lon );
                    }
                    else {
                        Log.e("TAG", "Check Serial");
                    }
                }
            }
        });


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;




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