package com.mahrous.mgtrackingdemo.ui.mainActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.mahrous.mgtrackingdemo.R;
import com.mahrous.mgtrackingdemo.Utility.Param;
import com.mahrous.mgtrackingdemo.data.AccessTokenGetResponse;
import com.mahrous.mgtrackingdemo.databinding.ActivityMainBinding;
import com.mahrous.mgtrackingdemo.ui.addDevice.AddDevice;
import com.mahrous.mgtrackingdemo.ui.allDevices.AllDevice;
import com.mahrous.mgtrackingdemo.ui.login.Login;
import com.mahrous.mgtrackingdemo.ui.register.RegisterViewModel;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    Intent intent;
    String accessToken;
    MainActivityViewModel viewModel;
    String password, name;
    final int LOGIN_AS_A_GUEST = 1;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setModel(this);
        setUpData () ;
        setUpComponent();
        setUpAPIs();


    }



    private void setUpComponent(){
        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLogoutBottomSheetDialog();
            }
        });
        binding.addDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("TAG", " AddDevice ->  AccessToken = " + accessToken);

                Intent intent = new Intent(MainActivity.this, AddDevice.class) ;
                intent.putExtra("accessToken",accessToken) ;
                startActivity(intent);

            }
        });
        binding.showDevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, AllDevice.class) ;
                Log.e("TAG", " AllDevice ->  AccessToken = " + accessToken);

                intent.putExtra("accessToken",accessToken) ;
                startActivity(intent);
            }
        });
    }
    private void setUpData (){
        intent = getIntent();
//        accessToken = intent.getStringExtra("accessToken");
        if (intent.getIntExtra("LOGIN_AS_A_GUEST" , 0) == LOGIN_AS_A_GUEST){
            name = "happy001";
            password = "Abc@34590";
        } else {
            getSharedPreferences();
        }
    }



    private void setUpAPIs() {
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        Log.e("TAG", "onChanged: password = " + password);
        Log.e("TAG", "onChanged: name = " + name);

        if (accessToken == null || accessToken.isEmpty()) {
            viewModel.getAccessToken(password, name);
        }
        viewModel.mutableLiveDataAccessToken.observeForever(new Observer<Object>() {
            /**
             * Called when the data is changed.
             *
             * @param o The new data
             */
            @Override
            public void onChanged(Object o) {
                if (o instanceof AccessTokenGetResponse) {
                    if (((AccessTokenGetResponse) o).getCode() == 0) {
                        accessToken = ((AccessTokenGetResponse) o).getRecord().getAccess_token();
                        Log.e("TAG", "onChanged: " + "  I am At Get AccessToken = " + accessToken);

                    } else {
                        Log.e("TAG", "onChanged: check userName or password");
                    }
                }
            }
        });

    }



    private void getSharedPreferences() {
            pref = getSharedPreferences(Param.NAME_SHARED_PREFERENCE, Context.MODE_PRIVATE);
            name = pref.getString(Param.username, null);
            password = pref.getString(Param.password, null);

        }


    private void showLogoutBottomSheetDialog() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
        bottomSheetDialog.setContentView(R.layout.alert_dialog);

        TextView yes = bottomSheetDialog.findViewById(R.id.yes);
        TextView no = bottomSheetDialog.findViewById(R.id.no);

        yes.setVisibility(View.VISIBLE);

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ApplySharedPref")
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getSharedPreferences(Param.NAME_SHARED_PREFERENCE, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.commit();


                Intent intent = new Intent(MainActivity.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);
                finish();

            }
        });

        bottomSheetDialog.show();


    }



}