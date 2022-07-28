package com.example.consoulingapp.ui.panel.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.consoulingapp.HomeRepository;
import com.example.consoulingapp.models.DashboardResponse;
import com.example.consoulingapp.models.ProfileResponse;

public class HomeViewModel extends AndroidViewModel {

    public MutableLiveData<ProfileResponse> profileResponse;
    public MutableLiveData<DashboardResponse> dashboardResponse;
    public HomeRepository homeRepository;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        this.homeRepository = new HomeRepository(application);
        this.profileResponse = homeRepository.profileResponse;
        this.dashboardResponse = homeRepository.dashboardResponse;

    }
    public void getProfile() {
        homeRepository.getProfile();
    }
    public void getDashboard(){
        homeRepository.getDashboard();
    }

}