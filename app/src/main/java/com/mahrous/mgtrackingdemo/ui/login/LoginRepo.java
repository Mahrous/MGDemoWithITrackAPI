package com.mahrous.mgtrackingdemo.ui.login;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.mahrous.mgtrackingdemo.data.UserResponse;
import com.mahrous.mgtrackingdemo.network.ApiInterface;
import com.mahrous.mgtrackingdemo.network.ApiUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginRepo {
    public ApiInterface apiInterface;
    public MutableLiveData<Object> mutableLiveData;

    public LoginRepo() {
        this.apiInterface = ApiUtils.getAPIService();
        this.mutableLiveData = new MutableLiveData<>();
    }



    public MutableLiveData<Object> login(String email , String password) {

        apiInterface.login(email ,  password).subscribeOn(Schedulers.io()).subscribe(new Observer<UserResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(UserResponse drDataResponse) {
                Log.e("TAG", "onError: " );

                mutableLiveData.postValue(drDataResponse);
            }

            @Override
            public void onError(Throwable e) {
                mutableLiveData.postValue(e);
                Log.e("TAG", "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        })       ;

        return mutableLiveData;
    }

}
