package com.mahrous.mgtrackingdemo.ui.addDevice;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.mahrous.mgtrackingdemo.Utility.GlobalViewModel;
import com.mahrous.mgtrackingdemo.ui.login.LoginRepo;

public class AddDeviceViewModel extends GlobalViewModel {
    public MutableLiveData<Object> mutableLiveDataTracking =  new MutableLiveData<>();


    public void addDevice (String password, String account, String serial, String firstName, String lastName ,
                           double lat,
                           double lon,
                           String token,
                           int expireDate,
                           int userId) {
        AddDeviceRepo repo = new AddDeviceRepo();
        repo.addDevice(password, account, serial, firstName, lastName, lat, lon, token, expireDate, userId).observeForever(new Observer<Object>() {
            /**
             * Called when the data is changed.
             *
             * @param o The new data
             */
            @Override
            public void onChanged(Object o) {
                mutableLiveData.setValue(o);
            }
        });

    }


    public void tracking(String access_token, String serial){
        AddDeviceRepo repo = new AddDeviceRepo();
        repo.tracking(access_token, serial).observeForever(new Observer<Object>() {
            /**
             * Called when the data is changed.
             *
             * @param o The new data
             */
            @Override
            public void onChanged(Object o) {
                mutableLiveDataTracking.setValue(o);
            }
        });

    }
}
