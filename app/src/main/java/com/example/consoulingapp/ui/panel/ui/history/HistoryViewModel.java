package com.example.consoulingapp.ui.panel.ui.history;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.consoulingapp.HistoryRepository;
import com.example.consoulingapp.models.Course;
import com.example.consoulingapp.models.History;

import java.util.List;

public class HistoryViewModel extends AndroidViewModel {
    public MutableLiveData<List<History>> history = new MutableLiveData<>();
    public HistoryRepository historyRepository;

    public HistoryViewModel(Application application) {
        super(application);
        historyRepository = new HistoryRepository(application);
        this.history = historyRepository.history;
    }

    public void getHistory() {
        historyRepository.getHistory();
    }



}