package com.mahrous.mgtrackingdemo.ui.allDevices;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.mahrous.mgtrackingdemo.R;
import com.mahrous.mgtrackingdemo.adapters.DevicesAdapter;
import com.mahrous.mgtrackingdemo.data.Device;
import com.mahrous.mgtrackingdemo.databinding.ActivityAllDeviceBinding;

import java.util.ArrayList;

public class AllDevice extends AppCompatActivity {
    ActivityAllDeviceBinding binding;
    AllDeviceViewModel viewModel;
    ArrayList<Device> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_all_device);
        binding.setModel(this);
        setUpAPIs();

    }

    private void setUpAPIs() {
        viewModel = ViewModelProviders.of(this).get(AllDeviceViewModel.class);
        viewModel.getAllDevices(1);
        viewModel.mutableLiveData.observeForever(new Observer<Object>() {
            /**
             * Called when the data is changed.
             *
             * @param o The new data
             */
            @Override
            public void onChanged(Object o) {
                list = (ArrayList<Device>) o;

                DevicesAdapter devicesAdapter = new DevicesAdapter(AllDevice.this , list);
                binding.rv.setLayoutManager(new LinearLayoutManager(AllDevice.this , RecyclerView.VERTICAL , false));
                binding.rv.setAdapter(devicesAdapter);


            }
        });
    }
}