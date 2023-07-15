package com.mahrous.mgtrackingdemo.ui.allDevices;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.mahrous.mgtrackingdemo.R;
import com.mahrous.mgtrackingdemo.Utility.Param;
import com.mahrous.mgtrackingdemo.adapters.DevicesAdapter;
import com.mahrous.mgtrackingdemo.data.Device;
import com.mahrous.mgtrackingdemo.databinding.ActivityAllDeviceBinding;
import com.mahrous.mgtrackingdemo.ui.map.MapsActivity;

import java.util.ArrayList;

public class AllDevice extends AppCompatActivity implements DevicesAdapter.ItemClick {
    ActivityAllDeviceBinding binding;
    AllDeviceViewModel viewModel;
    ArrayList<Device> list;
    int userId = 1;
    SharedPreferences pref;
    String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_all_device);
        binding.setModel(this);

        setUpData();
        setUpAPIs();

    }

    private void setUpData() {
        pref = getSharedPreferences(Param.NAME_SHARED_PREFERENCE, Context.MODE_PRIVATE);
        userId = pref.getInt(Param.id, 1);
        Intent intent = getIntent();
        accessToken = intent.getStringExtra("accessToken");
    }

    private void setUpAPIs() {
        viewModel = ViewModelProviders.of(this).get(AllDeviceViewModel.class);
        viewModel.getAllDevices(userId);
        viewModel.mutableLiveData.observeForever(new Observer<Object>() {
            /**
             * Called when the data is changed.
             *
             * @param o The new data
             */
            @Override
            public void onChanged(Object o) {
                list = (ArrayList<Device>) o;

                DevicesAdapter devicesAdapter = new DevicesAdapter(AllDevice.this, list , AllDevice.this);
                binding.rv.setLayoutManager(new LinearLayoutManager(AllDevice.this, RecyclerView.VERTICAL, false));
                binding.rv.setAdapter(devicesAdapter);


            }
        });
    }

    @Override
    public void updateLocation(String serial) {

        Intent intent = new Intent(AllDevice.this, MapsActivity.class);
        intent.putExtra("accessToken", accessToken);
        intent.putExtra("serial", serial);
        Log.e("TAG", "updateLocation: accessToken = " +  accessToken  + "  serial"  + serial);
        startActivity(intent);
    }
}