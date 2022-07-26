package com.example.consoulingapp.ui.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.consoulingapp.LoginRepository;
import com.example.consoulingapp.models.LoginResp;
import com.example.consoulingapp.models.User;

public class LoginViewModel extends AndroidViewModel {
    public MutableLiveData<LoginResp> user;

    public LoginRepository loginRepository;
    public LoginViewModel(@NonNull Application application) {
        super(application);
        this.loginRepository = new LoginRepository(application);
        this.user = loginRepository.user;
    }
    public void authenticate(String username, String password){
        loginRepository.authenticate(username, password);
    }
}
