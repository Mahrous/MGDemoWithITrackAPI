package com.mahrous.mgtrackingdemo.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mahrous.mgtrackingdemo.R;
import com.mahrous.mgtrackingdemo.databinding.ActivityMainBinding;
import com.mahrous.mgtrackingdemo.ui.addDevice.AddDevice;
import com.mahrous.mgtrackingdemo.ui.allDevices.AllDevice;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setModel(this);
        binding.checkoutUi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddDevice.class));
            }
        });
        binding.paymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AllDevice.class));
            }
        });

    }


}