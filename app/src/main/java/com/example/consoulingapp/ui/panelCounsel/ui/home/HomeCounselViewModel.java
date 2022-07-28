package com.example.consoulingapp.ui.panelCounsel.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeCounselViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeCounselViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}