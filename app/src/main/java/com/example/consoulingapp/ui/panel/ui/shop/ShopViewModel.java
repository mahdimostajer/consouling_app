package com.example.consoulingapp.ui.panel.ui.shop;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.consoulingapp.ShopRepository;
import com.example.consoulingapp.models.Course;
import com.example.consoulingapp.models.ProfileResponse;

import java.util.List;

public class ShopViewModel extends ViewModel {

    public ShopRepository shopRepository;
    public MutableLiveData<List<Course>> courses = new MutableLiveData<>();
    public MutableLiveData<Integer> selectedId = new MutableLiveData<>();

    public ShopViewModel() {
        this.shopRepository = new ShopRepository();
        this.courses = shopRepository.courses;
    }

    public void getCourses() {
        shopRepository.getCourses();
    }

    public void buy(int id){
        shopRepository.buy(id);
    }
}
