package com.example.consoulingapp;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.consoulingapp.models.Course;
import com.example.consoulingapp.models.History;
import com.example.consoulingapp.network.HistoryUtils;
import com.example.consoulingapp.network.ShopUtils;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class HistoryRepository {
    public MutableLiveData<List<History>> history = new MutableLiveData<>();
    public Application application;

    public HistoryRepository(Application application) {
        this.application = application;
    }

    public void getHistory(){
        new fetchHistory(history).execute();
    }


    public class fetchHistory extends AsyncTask<String, Void, String> {
        public MutableLiveData<List<History>> history;

        public fetchHistory(MutableLiveData<List<History>> courses) {
            this.history = courses;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Gson gson = new Gson();
            try {
                History[]  resp = gson.fromJson(s, History[].class);
                Log.d("gholam" , resp.toString());
                history.setValue(Arrays.asList(resp));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(String... strings) {
            HistoryUtils historyUtils = new HistoryUtils();
            return historyUtils.getHistory();
        }
    }
}
