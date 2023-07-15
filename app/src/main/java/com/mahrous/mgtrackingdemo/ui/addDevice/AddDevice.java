package com.mahrous.mgtrackingdemo.ui.addDevice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mahrous.mgtrackingdemo.R;
import com.mahrous.mgtrackingdemo.Utility.Param;
import com.mahrous.mgtrackingdemo.data.AccessTokenGetResponse;
import com.mahrous.mgtrackingdemo.data.GeneralModel;
import com.mahrous.mgtrackingdemo.data.TrackingResponse;
import com.mahrous.mgtrackingdemo.databinding.ActivityAddDeviceBinding;
import com.mahrous.mgtrackingdemo.ui.allDevices.AllDevice;

public class AddDevice extends AppCompatActivity {
    ActivityAddDeviceBinding binding;
    AddDeviceViewModel viewModel;
    String accessToken = "";
    int expireIn = 0;
    int userId = 1;
    SharedPreferences pref;
    Intent intent ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_device);
        binding.setModel(this);
        setUpData();
        setUpComponent();
        setUpAPI();
    }


    private void setUpComponent() {
        binding.addDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.tracking(accessToken, binding.serial.getText().toString().trim());

            }
        });

    }

    private void setUpAPI() {
        viewModel = ViewModelProviders.of(this).get(AddDeviceViewModel.class);
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
                        binding.serial.setError("Access Token is invalid, please restart app");

                    } else if (((TrackingResponse) o).getCode()==0){
                        //  password,  account,  serial,  firstName,  lastName , lat, lon, token, expireDate, userId
                        viewModel.addDevice(binding.password.getText().toString().trim(),
                                binding.userName.getText().toString().trim(),
                                binding.serial.getText().toString().trim(),
                                binding.firstName.getText().toString().trim(),
                                binding.lastName.getText().toString().trim(),
                                ((TrackingResponse) o).getRecord().get(0).getLatitude(),
                                ((TrackingResponse) o).getRecord().get(0).getLongitude(),
                                accessToken,
                                expireIn,
                                userId);

                        Log.e("TAG", "onChanged: " + "  I am At Check Serial");
                    }
                    else {
                        binding.serial.setError("Check Serial");
                    }
                }
            }
        });
        viewModel.mutableLiveData.observeForever(new Observer<Object>() {
            /**
             * Called when the data is changed.
             *
             * @param o The new data
             */
            @Override
            public void onChanged(Object o) {
                if (o instanceof GeneralModel) {
                    if (((GeneralModel) o).getStatusCode() == 1) {

                        startActivity(new Intent(AddDevice.this, AllDevice.class));
                        finish();
                    }
                }
            }
        });
    }

    private void setUpData() {
        pref = getSharedPreferences(Param.NAME_SHARED_PREFERENCE, Context.MODE_PRIVATE);
        userId = pref.getInt(Param.id, 1);
        intent = getIntent();
        accessToken = intent.getStringExtra("accessToken");
        Log.e("TAG", "setUpData: accessToken = "+accessToken );
    }





}