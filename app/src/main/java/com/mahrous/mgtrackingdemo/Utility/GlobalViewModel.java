package com.mahrous.mgtrackingdemo.Utility;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GlobalViewModel extends ViewModel {

    public MutableLiveData<Object> mutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> showProgress = new MutableLiveData<>();


    public MutableLiveData<Object> getMutableLiveData() {
        return mutableLiveData;
    }

    public MutableLiveData<Boolean> showProgress() {
        return showProgress;
    }

    public void setShowProgress(Boolean item) {
        showProgress.setValue(item);
    }



}
