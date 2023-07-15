package com.mahrous.mgtrackingdemo.ui.map;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.mahrous.mgtrackingdemo.Utility.GlobalViewModel;

public class MapsActivityViewModel extends GlobalViewModel {
    public MutableLiveData<Object> mutableLiveDataTracking =  new MutableLiveData<>();


    public void tracking(String access_token, String serial){
        MapsActivityRepo repo = new MapsActivityRepo();
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
