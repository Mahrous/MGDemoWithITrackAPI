package com.mahrous.mgtrackingdemo.ui.allDevices;

import androidx.lifecycle.Observer;

import com.mahrous.mgtrackingdemo.Utility.GlobalViewModel;

public class AllDeviceViewModel extends GlobalViewModel {


    public void getAllDevices(int userID) {
        AllDeviceRepo repo = new AllDeviceRepo();
        repo.getAllDevices(userID).observeForever(new Observer<Object>() {
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
}
