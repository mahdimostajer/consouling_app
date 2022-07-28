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

import java.util.Arrays;
import java.util.List;

public class ShopRepository {
    public MutableLiveData<List<Course>> courses = new MutableLiveData<>();

    public ShopRepository() {
    }

    public void buy(int id){

    }

    public void getCourses() {
        new fetchCourses(courses).execute();
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
                Course[]  resp = gson.fromJson(s, Course[].class);
                courses.setValue(Arrays.asList(resp));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(String... strings) {
            ShopUtils shopUtils = new ShopUtils();
            return shopUtils.getCourses();
        }
    }

    public class buyCourse extends AsyncTask<Integer, Void, String>{

        @Override
        protected String doInBackground(Integer... integers) {
            ShopUtils shopUtils = new ShopUtils();
            return shopUtils.buy(integers[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}


