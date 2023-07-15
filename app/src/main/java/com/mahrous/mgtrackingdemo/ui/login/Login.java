package com.mahrous.mgtrackingdemo.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.mahrous.mgtrackingdemo.R;
import com.mahrous.mgtrackingdemo.Utility.Param;
import com.mahrous.mgtrackingdemo.data.UserResponse;
import com.mahrous.mgtrackingdemo.databinding.ActivityLoginBinding;
import com.mahrous.mgtrackingdemo.network.InternetConnection;
import com.mahrous.mgtrackingdemo.ui.mainActivity.MainActivity;
import com.mahrous.mgtrackingdemo.ui.register.Register;

public class Login extends AppCompatActivity {

    ActivityLoginBinding binding;

    String password, email;
    LoginViewModel viewModel;
    SharedPreferences preferences;
    String showConfirm = "Show";
    View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setModel(this);
        setUpComponents();

    }

    private void continueLogin() {
        email = binding.emailAddress.getText().toString().trim();
        password = binding.password.getText().toString().trim();

        if (checkPass() || checkEmail()) {
        } else {
            Log.e("TAG", "continueRegistration: i am done Email = " + email + "password = " + password);
            viewModel.login(email, password);
        }

    }

    boolean checkEmail() {
        String emailOrPgone = binding.emailAddress.getText().toString().trim();
        String phoneCode = "";

        if (emailOrPgone.isEmpty()) {
            binding.emailAddress.setError("Data is required");
            return true;

        } else {
            binding.emailAddress.setError(null);

            return false;
        }
    }

    boolean checkPass() {
        String email = binding.password.getText().toString().trim();
        if (email.isEmpty()) {
            binding.password.setError("Data is required");
            return true;

        } else {
            Log.e("TAG", "checkEmail: " + "here last");
            binding.password.setError(null);

            return false;
        }
    }

    void setUpComponents() {
        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        binding.logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login.this.view = view;
                if (InternetConnection.checkInternetConnection(Login.this)) {
                    continueLogin();
                } else {
                    Snackbar.make(view, "Please check internet", Snackbar.LENGTH_LONG).show();

                }
            }
        });
        binding.password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    binding.password.setError(null);


                } else {
                    checkPass();
                }
            }
        });
        binding.password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        binding.emailAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                checkEmail();
            }
        });
        binding.emailAddress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    binding.emailAddress.setError(null);
//                    binding.emailAddress.setErrorEnabled(false);

                } else {
                    // true : something wrong , false : all is right
                    checkEmail();

                }
            }
        });
        binding.confirmPasswordEye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (showConfirm.equals("Show")) {
                    binding.password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    showConfirm = "Hide";
                    binding.confirmPasswordEye.setImageDrawable(null);
                    binding.confirmPasswordEye.setBackgroundResource(R.drawable.ic_password_show_hide);
                } else {
                    binding.password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    showConfirm = "Show";
                    binding.confirmPasswordEye.setImageDrawable(null);
                    binding.confirmPasswordEye.setBackgroundResource(R.drawable.ic_hide_password);
                }
            }
        });

        binding.loginAsGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, MainActivity.class).putExtra("LOGIN_AS_A_GUEST", 1));
            }
        });
        viewModel.mutableLiveData.observeForever(new Observer<Object>() {
            /**
             * Called when the data is changed.
             *
             * @param o The new data
             */
            @Override
            public void onChanged(Object o) {

                if (o instanceof UserResponse) {
                    if (((UserResponse) o).isSucceeded()) {
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        setSharedPreferences(((UserResponse) o));
                        startActivity(intent);
                        finish();
//                        Toast.makeText(Login.this, ((UserResponse) o).isSucceeded() + " ", Toast.LENGTH_SHORT).show();
                    } else {
                        Snackbar.make(view, "Check email or password" + " ", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
//                        Toast.makeText(Login.this, ((UserResponse) o).errors.get(0) + " ", Toast.LENGTH_SHORT).show();

                    }

                } else {
                    Snackbar.make(view, "Password is incorrect", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
        binding.signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (InternetConnection.checkInternetConnection(Login.this)) {
                    startActivity(new Intent(Login.this, Register.class));

                } else {
                    Snackbar.make(view, "please check internet", Snackbar.LENGTH_LONG).show();

                }
            }
        });
    }

    private void setSharedPreferences(UserResponse model) {
        preferences = getSharedPreferences(Param.NAME_SHARED_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if (preferences.getInt(Param.id, 0) == 0) {
            editor = preferences.edit();
            editor.putString(Param.username, model.getUsername());
            editor.putString(Param.email, model.getEmail());
            editor.putString(Param.mobile, model.getMobile());
            editor.putString(Param.password, model.getPassword());
            editor.putInt(Param.id, model.getId());
            editor.apply();
        } else {
            editor.putString(Param.username, model.getUsername());
            editor.putString(Param.email, model.getEmail());
            editor.putString(Param.mobile, model.getMobile());
            editor.putString(Param.password, model.getPassword());
            editor.putInt(Param.id, model.getId());
            editor.apply();
        }

    }
}