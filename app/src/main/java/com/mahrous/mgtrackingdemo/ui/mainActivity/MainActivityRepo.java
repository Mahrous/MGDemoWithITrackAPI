package com.mahrous.mgtrackingdemo.ui.mainActivity;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.mahrous.mgtrackingdemo.data.AccessTokenGetResponse;
import com.mahrous.mgtrackingdemo.data.GeneralModel;
import com.mahrous.mgtrackingdemo.network.ApiInterface;
import com.mahrous.mgtrackingdemo.network.ApiUtils;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivityRepo {
    public ApiInterface apiInterface;
    public MutableLiveData<Object> mutableLiveDataAccessToken;

    public MainActivityRepo() {
        this.apiInterface = ApiUtils.getAPIService();

        this.mutableLiveDataAccessToken = new MutableLiveData<>();
    }

    public MutableLiveData<Object> getAccessToken(String password, String account) {

        apiInterface.getAccessToken(password, account).subscribeOn(Schedulers.io()).subscribe(new Observer<AccessTokenGetResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(AccessTokenGetResponse addDeviceResponse) {
                Log.e("TAG", "onError: ");

                mutableLiveDataAccessToken.postValue(addDeviceResponse);
            }

            @Override
            public void onError(Throwable e) {
                mutableLiveDataAccessToken.postValue(e);
                Log.e("TAG", "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });

        return mutableLiveDataAccessToken;
    }



}
