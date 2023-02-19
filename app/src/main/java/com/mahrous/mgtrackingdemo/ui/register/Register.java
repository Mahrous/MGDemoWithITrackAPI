package com.mahrous.mgtrackingdemo.ui.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.common.AccountPicker;
import com.google.android.material.snackbar.Snackbar;
import com.mahrous.mgtrackingdemo.R;
import com.mahrous.mgtrackingdemo.Utility.Codes;
import com.mahrous.mgtrackingdemo.Utility.Param;
import com.mahrous.mgtrackingdemo.data.GeneralModel;
import com.mahrous.mgtrackingdemo.databinding.ActivityRegisterBinding;
import com.mahrous.mgtrackingdemo.network.InternetConnection;
import com.mahrous.mgtrackingdemo.ui.login.Login;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Register extends AppCompatActivity {

    ActivityRegisterBinding binding;
    boolean email_exist = true;
    boolean phone_exist = true;
    RegisterViewModel viewModel;
    SharedPreferences preferences;
    DatePickerDialog picker;
    String img = "";
    Handler handler = new Handler();
    Runnable runnable2;
    String name;
    String email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        binding.setModel(this);
        viewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);

        if (getIntent().hasExtra("process")) {
            Intent intent = getIntent();
            img = intent.getStringExtra(Param.IMG);
            binding.nameTf.setText(intent.getStringExtra(Param.username));
            binding.emailTf.setText(intent.getStringExtra(Param.email));
        } else {
            try {
                Intent intent = AccountPicker.newChooseAccountIntent(null, null,
                        new String[]{GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE}, false, null, null, null, null);
                startActivityForResult(intent, 10100);
            } catch (ActivityNotFoundException e) {

            }
        }
        setUpComponents();


        binding.continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!InternetConnection.checkInternetConnection(getApplicationContext())) {
                    Snackbar.make(v, "Please Check internet connection", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
                Log.e("TAG", "onclick: " + "done");
                continueRegistration();

            }
        });
        setObservers();



    }



    void continueRegistration() {
        checkValidation();
        String student_name = binding.nameTf.getText().toString().trim();
        String student_email = binding.email.getEditText().getText().toString().trim();
        String password = binding.password.getEditText().getText().toString().trim();
        String phone = binding.phone.getEditText().getText().toString().trim();

        if (
                checkName() ||
                        checkPhone() ||
                        checkEmail() ||
                        checkPass()
        ) {
        } else if (email_exist) {
            checkEmail();
        } else if (phone_exist) {
            checkPhone();
        } else {
            Log.e("TAG", "onclick: " + "Arrive");
            viewModel.signUp(student_name, student_email, phone, password);
            setSharedPreferences(preferences, student_email, student_name);
        }
    }




    private void checkValidation() {
        if (binding.nameTf.getText().toString().trim().length() > 0 && binding.emailTf.getText().toString().trim().length() > 0 && binding.passwordTf.getText().toString().trim().length() > 0  && binding.phoneTf.getText().toString().trim().length() > 0 ) {
            binding.continueBtn.setEnabled(true);
        } else {
            binding.continueBtn.setEnabled(false);
        }
    }

    boolean checkName() {
        String student_name = binding.name.getEditText().getText().toString().trim();
        if (student_name.isEmpty()) {
            binding.name.setError("Enter Name");
            //  name.requestFocus();
            return true;

        } else {
            binding.name.setError(null);
            binding.name.setErrorEnabled(false);
            return false;

        }
    }

    boolean checkEmail() {
        String student_email = binding.email.getEditText().getText().toString().trim();
        if (student_email.isEmpty()) {
            binding.email.setError("Enter Email");
            //    email.requestFocus();
            return true;

        } else if (!student_email.matches(Param.EMAIL_PATTERN)) {
            binding.email.setError("Invalid Email");
            //   email.requestFocus();
            return true;
        } else {
            binding.email.setError(null);
            binding.email.setErrorEnabled(false);
            checkEmailIfExist();
            return false;
        }
    }


    // true if not matching , false if matching
    boolean checkPass() {
        String password = binding.password.getEditText().getText().toString().trim();
        if (password.length() < 8) {
            binding.password.setError("Invalid Password");
            return true;

        }  else {
            binding.password.setError(null);
            binding.password.setErrorEnabled(false);

            //     pass.requestFocus();
            return false;

        }
    }


    boolean checkPhone() {
        String p = binding.phone.getEditText().getText().toString().trim();
        if (p.isEmpty() || p.length() < 9) {
            binding.phone.setError( "Phone Not Valid" );
            return true;
        } else {
            binding.phone.setError(null);
            binding.phone.setErrorEnabled(false);
            checkPhoneIfExist();

            return false;
        }

    }





    void setUpComponents() {
        viewModel.mutableLiveData.observeForever(new Observer<Object>() {
            @Override
            public void onChanged(Object o) {
                Log.e("TAG", "onChanged: " + "Not Done done");
                if (o instanceof GeneralModel) {
                    Log.e("TAG", "onChanged: " + "done Again");
                    GeneralModel generalModel = (GeneralModel) o;

                    if (generalModel.getStatusCode() == 1) {
                        Intent intent = new Intent(Register.this, Login.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();

                    } else {

                        Toast.makeText(getApplicationContext(), "hi", Toast.LENGTH_SHORT).show();
                    }
                } else if (o instanceof IOException)
                    Toast.makeText(getApplicationContext(),"Check Network", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "Check Network", Toast.LENGTH_SHORT).show();

            }
        });

        binding.goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        binding.password.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                checkValidation();

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        binding.nameTf.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    binding.name.setError(null);
                    binding.name.setErrorEnabled(false);
                } else {
                    checkName();
                }
            }
        });
        binding.name.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkValidation();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        binding.emailTf.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    binding.email.setError(null);
                    binding.email.setErrorEnabled(false);

                } else {
                    // true : something wrong , false : all is right
                    checkEmail();

                }
            }
        });
        binding.phoneTf.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    binding.phone.setError(null);
                    binding.phone.setErrorEnabled(false);
                } else {
                    checkPhone();
                }
            }
        });

        binding.email.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                email_exist = true;
                checkValidation();
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkEmail();
            }
        });
        binding.phone.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                phone_exist = true;
                checkValidation();
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkPhone();
            }
        });
        binding.passwordTf.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    binding.password.setError(null);
                    binding.password.setErrorEnabled(false);


                } else {
                    checkPass();
                }
            }
        });

    }

    private void checkEmailIfExist() {
        String email = binding.email.getEditText().getText().toString().trim();
        viewModel.checkEmail(email);
    }

    private void checkPhoneIfExist() {
        String phone = binding.phone.getEditText().getText().toString().trim();
        viewModel.checkPhone(phone);
    }


    void setObservers() {
        viewModel.showProgress().observeForever(new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (!aBoolean) {
                    binding.continueBtn.revertAnimation();
                } else {
                    binding.continueBtn.startAnimation();
                }
            }
        });
        viewModel.getCheck_email().observeForever(new Observer<Object>() {
            @Override
            public void onChanged(Object o) {
                if (o instanceof GeneralModel) {
                    if (((GeneralModel) o).getStatusCode() == Codes.EMAIL_EXIST) {
                        Log.i("tag", "check email : exist");
                        email_exist = true;
                        binding.email.setError("Email Exist");
                    } else if (((GeneralModel) o).getStatusCode() == Codes.EMAIL_NOT_EXIST) {
                        Log.i("tag", "check email : not exist");

                        email_exist = false;
                        binding.email.setError(null);
                        binding.email.setErrorEnabled(false);

                    } else if (((GeneralModel) o).getStatusCode() == Codes.INTERNET_ERROR) {
                        email_exist = true;
                        Toast.makeText(getApplicationContext(), "Check Network", Toast.LENGTH_LONG).show();

                    } else {
                        email_exist = true;
                        Toast.makeText(getApplicationContext(),"Check Network", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        viewModel.getCheck_phone().observeForever(new Observer<Object>() {
            @Override
            public void onChanged(Object o) {
//                binding.phone.setError(getString(R.string.phone_exist));
                if (o instanceof GeneralModel) {

                    if (((GeneralModel) o).getStatusCode() == Codes.EMAIL_EXIST) {
                        Log.i("tag", "check phone : exist");

                        phone_exist = true;
                        binding.phone.setError("Phone Exist");
                    } else if (((GeneralModel) o).getStatusCode() == Codes.EMAIL_NOT_EXIST) {
                        Log.i("tag", "check phone : not exist");

                        phone_exist = false;
                        binding.phone.setError(null);
                        binding.phone.setErrorEnabled(false);

                    } else if (((GeneralModel) o).getStatusCode() == Codes.INTERNET_ERROR) {
                        phone_exist = true;
                        Toast.makeText(getApplicationContext(), "Check Network", Toast.LENGTH_LONG).show();

                    } else {
                        phone_exist = true;
                        Toast.makeText(getApplicationContext(),"Check Network", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10100 && resultCode == RESULT_OK) {
            String accountName = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);

            try {

                String name = accountName.split("@")[0];
                name = name.replaceAll("[^a-zA-Z]", " ");
                binding.nameTf.setText(name);
                binding.emailTf.setText(accountName);
                checkEmailIfExist();

            } catch (Exception e) {

            }
        }
    }


    private void setSharedPreferences(SharedPreferences sharedPref, String email, String name) {
        sharedPref = getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        if ( sharedPref.getString("email", "") == null)
        {
            editor = preferences.edit();
            editor.putString("name", name);
            editor.putString("email",email);
            editor.apply();
        }else {
            editor.putString("name", name);
            editor.putString("email", email);
            editor.commit();
        }

    }


}