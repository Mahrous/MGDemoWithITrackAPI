package com.mahrous.mgtrackingdemo.ui.addDevice;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.mahrous.mgtrackingdemo.data.AccessTokenGetResponse;
import com.mahrous.mgtrackingdemo.data.GeneralModel;
import com.mahrous.mgtrackingdemo.data.TrackingResponse;
import com.mahrous.mgtrackingdemo.data.UserResponse;
import com.mahrous.mgtrackingdemo.network.ApiInterface;
import com.mahrous.mgtrackingdemo.network.ApiUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.Field;
import retrofit2.http.Query;

public class AddDeviceRepo {
    public ApiInterface apiInterface;
    public MutableLiveData<Object> mutableLiveData;
    public MutableLiveData<Object> mutableLiveDataAccessToken;
    public MutableLiveData<Object> mutableLiveDataTracking;

    public AddDeviceRepo() {
        this.apiInterface = ApiUtils.getAPIService();
        this.mutableLiveData = new MutableLiveData<>();
        this.mutableLiveDataAccessToken = new MutableLiveData<>();
        this.mutableLiveDataTracking = new MutableLiveData<>();
    }


    public MutableLiveData<Object> addDevice(String password, String account, String serial, String firstName, String lastName,
                                             double lat,
                                             double lon,
                                             String token,
                                             int expireDate,
                                             int userId) {

        apiInterface.addDevice(password, account, serial, firstName, lastName, lat, lon, token, expireDate, userId).subscribeOn(Schedulers.io()).subscribe(new Observer<GeneralModel>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(GeneralModel addDeviceResponse) {
                Log.e("TAG", "onError: ");

                mutableLiveData.postValue(addDeviceResponse);
            }

            @Override
            public void onError(Throwable e) {
                mutableLiveData.postValue(e);
                Log.e("TAG", "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });

        return mutableLiveData;
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












    public MutableLiveData<Object> tracking(String access_token, String serial) {

        apiInterface.tracking(access_token , serial).subscribeOn(Schedulers.io()).subscribe(new Observer<TrackingResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(TrackingResponse addDeviceResponse) {
                Log.e("TAG", "onError: ");

                mutableLiveDataTracking.postValue(addDeviceResponse);
            }

            @Override
            public void onError(Throwable e) {
                mutableLiveDataTracking.postValue(e);
                Log.e("TAG", "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });

        return mutableLiveDataTracking;
    }

}
