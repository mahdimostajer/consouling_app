package com.example.consoulingapp;

import android.app.Application;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.consoulingapp.models.DashboardResponse;
import com.example.consoulingapp.models.ProfileResponse;
import com.example.consoulingapp.network.HomeUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

public class HomeRepository {
    public MutableLiveData<ProfileResponse> profileResponse = new MutableLiveData<>();
    private final Application application;

    public HomeRepository(Application application) {
        this.application = application;
    }

    public void getProfile() {
        new getProfile(profileResponse).execute();
    }
    public void getDashboard() {
        new getDashboard(DashboardResponse).execute();
    }

    public class getProfile extends AsyncTask<String, Void, String> {
        MutableLiveData<ProfileResponse> profileResponse;

        public getProfile(MutableLiveData<ProfileResponse> profileResponse) {
            this.profileResponse = profileResponse;
        }

        @Override
        protected String doInBackground(String... strings) {
            HomeUtils homeUtils = new HomeUtils();
            return homeUtils.getProfile();
        }

        @Override
        protected void onPostExecute(String s) {
            if (s == null) {
                Toast.makeText(application, "خطا", Toast.LENGTH_LONG).show();
                return;
            }
            super.onPostExecute(s);
            Gson gson = new Gson();
            Type mapType = new TypeToken<Map<String, Object>>() {
            }.getType();
            try {
                Map<String, Object> map = new Gson().fromJson(s, mapType);
                if (map.size() == 1) {
                    Toast.makeText(application, (map.values().toArray()[0]).toString(), Toast.LENGTH_LONG).show();
                } else {
                    ProfileResponse resp = gson.fromJson(s, ProfileResponse.class);
                    ProfileResponse.profileResponse = resp;
                    profileResponse.setValue(resp);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(application, "خطایی رخ داده است", Toast.LENGTH_LONG).show();
            }
        }
    }


    public class getDashboard extends AsyncTask<String, Void, String> {
        MutableLiveData<DashboardResponse> dashboardResponse;

        public getDashboard(MutableLiveData<DashboardResponse> dashboardResponse) {
            this.dashboardResponse = dashboardResponse;
        }

        @Override
        protected String doInBackground(String... strings) {
            HomeUtils homeUtils = new HomeUtils();
            return homeUtils.getDashboard();
        }

        @Override
        protected void onPostExecute(String s) {
            if (s == null) {
                Toast.makeText(application, "خطا", Toast.LENGTH_LONG).show();
                return;
            }
            super.onPostExecute(s);
            Gson gson = new Gson();
            Type mapType = new TypeToken<Map<String, Object>>() {
            }.getType();
            try {
                Map<String, Object> map = new Gson().fromJson(s, mapType);
                if (map.size() == 1) {
                    Toast.makeText(application, (map.values().toArray()[0]).toString(), Toast.LENGTH_LONG).show();
                } else {
                    DashboardResponse resp = gson.fromJson(s, DashboardResponse.class);
//                    DashboardResponse.dashboardResponse = resp;
                    dashboardResponse.setValue(resp);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(application, "خطایی رخ داده است", Toast.LENGTH_LONG).show();
            }
        }
    }
}