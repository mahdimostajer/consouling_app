package com.example.consoulingapp;

import android.os.AsyncTask;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.consoulingapp.models.Course;
import com.example.consoulingapp.models.LoginResp;
import com.example.consoulingapp.models.ShopResp;
import com.example.consoulingapp.network.Client;
import com.example.consoulingapp.network.ShopUtils;
import com.google.gson.Gson;

import java.util.List;

public class ShopRepository {
    public MutableLiveData<List<Course>> courses = new MutableLiveData<>();

    public ShopRepository() {
    }

    public class fetchCourses extends AsyncTask<String, Void, String> {
        public MutableLiveData<List<Course>> courses;

        public fetchCourses(MutableLiveData<List<Course>> courses) {
            this.courses = courses;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Gson gson = new Gson();
            try {
                ShopResp resp = gson.fromJson(s, ShopResp.class);
                courses.setValue(resp.courses);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(String... strings) {
            ShopUtils shopUtils = new ShopUtils();
            return shopUtils.getCourses(strings[0]);
        }
    }
}
