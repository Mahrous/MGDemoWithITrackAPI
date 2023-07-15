package com.mahrous.mgtrackingdemo.ui.map;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.mahrous.mgtrackingdemo.data.GeneralModel;
import com.mahrous.mgtrackingdemo.data.TrackingResponse;
import com.mahrous.mgtrackingdemo.network.ApiInterface;
import com.mahrous.mgtrackingdemo.network.ApiUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MapsActivityRepo {
    public ApiInterface apiInterface;
    public MutableLiveData<Object> mutableLiveDataTracking;
    public MapsActivityRepo() {
        this.apiInterface = ApiUtils.getAPIService();
        this.mutableLiveDataTracking = new MutableLiveData<>();
    }

    public MutableLiveData<Object> tracking(String access_token, String serial) {
        apiInterface.tracking(access_token , serial).subscribeOn(Schedulers.io()).subscribe(new Observer<TrackingResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(TrackingResponse addDeviceResponse) {

                mutableLiveDataTracking.postValue(addDeviceResponse);
            }

            @Override
            public void onError(Throwable e) {
                mutableLiveDataTracking.postValue(e);
                Log.e("TAG", "onError: err = " + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });

        return mutableLiveDataTracking;
    }

}
