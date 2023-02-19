package com.mahrous.mgtrackingdemo.ui.login;

import androidx.lifecycle.Observer;

import com.mahrous.mgtrackingdemo.Utility.GlobalViewModel;

public class LoginViewModel extends GlobalViewModel {



    public void login (String email , String password) {
        LoginRepo repo = new LoginRepo();
        repo.login(email, password).observeForever(new Observer<Object>() {
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
