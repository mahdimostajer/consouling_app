package com.example.consoulingapp.ui.panel.ui.shop;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.consoulingapp.models.ProfileResponse;

public class ShopViewModel extends ViewModel {

    public MutableLiveData<ProfileResponse> profileResponse;

//    public ShopRepository shopRepository;

    public ShopViewModel() {
//        super(application);
//        this.shopRepository = new ShopRepository(application);
//        this.profileResponse = shopRepository.profileResponse;
    }



    public void getProfile(){
//        shopRepository.getProfile();
    }
}