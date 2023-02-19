package com.mahrous.mgtrackingdemo.ui.allDevices;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.mahrous.mgtrackingdemo.data.Device;
import com.mahrous.mgtrackingdemo.network.ApiInterface;
import com.mahrous.mgtrackingdemo.network.ApiUtils;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AllDeviceRepo {
    public ApiInterface apiInterface;
    public MutableLiveData<Object> mutableLiveData;


    public AllDeviceRepo() {
        this.apiInterface = ApiUtils.getAPIService();
        this.mutableLiveData = new MutableLiveData<>();
        this.mutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<Object> getAllDevices(int userID) {
        apiInterface.getDevices(userID).subscribeOn(Schedulers.io()).subscribe(new Observer<List<Device>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Device> devices) {
                Log.e("TAG", "onNext: size = " + devices.size());
                mutableLiveData.postValue(devices);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("TAG", "onError: err = " + e.getMessage());

            }

            @Override
            public void onComplete() {

            }
        });
        return mutableLiveData;

    }


}
