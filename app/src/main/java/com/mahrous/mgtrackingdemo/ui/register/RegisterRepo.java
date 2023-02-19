package com.mahrous.mgtrackingdemo.ui.register;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.mahrous.mgtrackingdemo.data.GeneralModel;
import com.mahrous.mgtrackingdemo.network.ApiInterface;
import com.mahrous.mgtrackingdemo.network.ApiUtils;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegisterRepo {
    public ApiInterface apiInterface;
    public MutableLiveData<Object> mutableLiveData;
    public MutableLiveData<Object> checkEmail;

    public RegisterRepo() {
        this.apiInterface = ApiUtils.getAPIService();
        this.mutableLiveData = new MutableLiveData<>();
        this.checkEmail = new MutableLiveData<>();
    }

    public MutableLiveData<Object> checkEmail(String email) {
        apiInterface.checkEmail(email).subscribeOn(Schedulers.io()).subscribe(new Observer<GeneralModel>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull GeneralModel r) {
                checkEmail.postValue(r);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                checkEmail.postValue(e);
            }

            @Override
            public void onComplete() {

            }
        });
        return checkEmail;
    }

    public MutableLiveData<Object> checkPhone(String phone) {
        apiInterface.checkPhone(phone).subscribeOn(Schedulers.io()).subscribe(new Observer<GeneralModel>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull GeneralModel r) {
                checkEmail.postValue(r);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                checkEmail.postValue(e);
            }

            @Override
            public void onComplete() {

            }
        });
        return checkEmail;
    }



    public MutableLiveData<Object> SignUpStudent(String name, String email, String phone, String password) {
        apiInterface.register(name, email, phone, password).subscribeOn(Schedulers.io()).subscribe(new Observer<GeneralModel>() {
            @Override
            public void onSubscribe(@androidx.annotation.NonNull Disposable d) {

            }

            @Override
            public void onNext(@androidx.annotation.NonNull GeneralModel generalModel) {
                mutableLiveData.postValue(generalModel);
                Log.e("TAG", "onError: " + generalModel.getMessage());

            }

            @Override
            public void onError(@androidx.annotation.NonNull Throwable e) {
                Log.e("TAG", "onError: " + e.getMessage());

            }

            @Override
            public void onComplete() {

            }
        });
        return mutableLiveData;
    }


}
