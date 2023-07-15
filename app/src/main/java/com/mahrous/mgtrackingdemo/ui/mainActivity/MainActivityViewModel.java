package com.mahrous.mgtrackingdemo.ui.mainActivity;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.mahrous.mgtrackingdemo.Utility.GlobalViewModel;
import com.mahrous.mgtrackingdemo.ui.register.RegisterRepo;

public class MainActivityViewModel extends GlobalViewModel {

    public MutableLiveData<Object> mutableLiveDataAccessToken =  new MutableLiveData<>();




    public void getAccessToken(String password, String account){
        RegisterRepo repo = new RegisterRepo();
        repo.getAccessToken(password, account).observeForever(new Observer<Object>() {
            /**
             * Called when the data is changed.
             *
             * @param o The new data
             */
            @Override
            public void onChanged(Object o) {
                mutableLiveDataAccessToken.setValue(o);
            }
        });

    }


}
