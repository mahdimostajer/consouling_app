package com.example.consoulingapp.ui.register;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.consoulingapp.RegisterRepository;
import com.example.consoulingapp.models.RegisterResponse;
import com.example.consoulingapp.models.User;

public class RegisterViewModel extends AndroidViewModel {
    public MutableLiveData<RegisterResponse> registerResponse;

    public RegisterRepository registerRepository;
    public RegisterViewModel(@NonNull Application application) {
        super(application);
        this.registerRepository = new RegisterRepository(application);
        this.registerResponse = registerRepository.registerResponse;
    }
    public void register(
            String username,
            String password,
            String type,
            String gender,
            String firstName,
            String lastName,
            String province,
            String city,
            String phone,
            String creditCard,
            String date
    ){
        registerRepository.register(
                username,
                password,
                type,
                gender,
                firstName,
                lastName,
                province,
                city,
                phone,
                creditCard,
                date
        );
    }
}
