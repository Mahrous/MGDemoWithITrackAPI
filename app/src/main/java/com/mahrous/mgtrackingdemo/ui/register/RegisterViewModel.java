package com.mahrous.mgtrackingdemo.ui.register;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.mahrous.mgtrackingdemo.Utility.GlobalViewModel;
import com.mahrous.mgtrackingdemo.ui.addDevice.AddDeviceRepo;

public class RegisterViewModel extends GlobalViewModel {

    public MutableLiveData<Object> check_email = new MutableLiveData<>();
    public MutableLiveData<Object> check_phone = new MutableLiveData<>();
    MutableLiveData<Object> MutableLiveDataCompany = new MutableLiveData<>();
    public MutableLiveData<Object> mutableLiveDataAccessToken =  new MutableLiveData<>();

    public void checkEmail(String email) {
        RegisterRepo repo = new RegisterRepo();
        setShowProgress(true);
        repo.checkEmail(email).observeForever(new Observer<Object>() {
            @Override
            public void onChanged(Object o) {
                setShowProgress(false);
//                if (o instanceof GeneralModel) {
//                    GeneralModel response = (GeneralModel) o;
                check_email.setValue(o);

//                    int status = response.getStatusCode();
////                    Utilities.print("SignUp", "checkEmail : " + status);
//                    if (status == 1)
//                        check_email.setValue(o);
//                    else if (status == 0) {
//                        check_email.setValue(Codes.EMAIL_EXIST);
//                    }
//                } else if (o instanceof IOException)
//                    check_email.setValue(Codes.INTERNET_ERROR);
//                else
//                    check_email.setValue(Codes.FAILED);
            }
        });
    }

    public void checkPhone(String phone) {
        RegisterRepo repo = new RegisterRepo();
        setShowProgress(true);
        repo.checkPhone(phone).observeForever(new Observer<Object>() {
            @Override
            public void onChanged(Object o) {
                setShowProgress(false);

                check_phone.setValue(o);

//                if (o instanceof GeneralModel) {
//
//
//                    GeneralModel response = (GeneralModel) o;
//                    int status = response.getStatusCode();
////                    Utilities.print("SignUp", "checkPhone : " + status);
//                    if (status == 1)
//                        check_phone.setValue(Codes.EMAIL_NOT_EXIST);
//                    else if (status == 0) {
//                        check_phone.setValue(Codes.EMAIL_EXIST);
//                    }
//                } else if (o instanceof IOException)
//                    check_phone.setValue(Codes.INTERNET_ERROR);
//                else
//                    check_phone.setValue(Codes.FAILED);
            }
        });
    }

    public void signUp(String name, String email, String phone, String password) {
        setShowProgress(true);
        RegisterRepo repo = new RegisterRepo();
        repo.SignUpStudent(name, email, phone, password).observeForever(new Observer<Object>() {
            @Override
            public void onChanged(Object o) {
                setShowProgress(false);
                mutableLiveData.setValue(o);

            }
        });
    }





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


    public MutableLiveData<Object> getCheck_email() {
        return check_email;
    }

    public MutableLiveData<Object> getCheck_phone() {
        return check_phone;
    }

    public MutableLiveData<Object> getMutableLiveDataCompany() {
        return MutableLiveDataCompany;
    }
}
