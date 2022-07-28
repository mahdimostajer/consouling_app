package com.example.consoulingapp;

import android.app.Application;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.consoulingapp.models.LoginResp;
import com.example.consoulingapp.models.ProfileResponse;
import com.example.consoulingapp.network.Client;
import com.example.consoulingapp.network.LoginUtils;
import com.example.consoulingapp.network.ProfileUtils;
import com.google.gson.Gson;

import okhttp3.RequestBody;

public class ProfileRepository {
    public MutableLiveData<ProfileResponse> profileResponse = new MutableLiveData<>();
    public Application application;

    public ProfileRepository() {
        this.application = application;
    }

    public void changeDetails(
            String grade,
            String state,
            String field,
            String school_name,
            String school_type,
            String average_level,
            String extra_curricular_exam,
            String latest_grade,
            String description,
            String parent_career,
            String parent_phone_number
    ) {
        new fetchUser(profileResponse, application).execute(
                grade,
                state,
                field,
                school_name,
                school_type,
                average_level,
                extra_curricular_exam,
                latest_grade,
                description,
                parent_career,
                parent_phone_number
        );
    }

    public class fetchUser extends AsyncTask<String, Void, String> {
        MutableLiveData<ProfileResponse> profileResponse;
        Application application;

        public fetchUser(MutableLiveData<ProfileResponse> profileResponse, Application application
        ) {
            this.profileResponse = profileResponse;
            this.application = application;
        }

        @Override
        protected String doInBackground(String... strings) {
            ProfileUtils loginUtils = new ProfileUtils();
            return loginUtils.changeDetails(
                    strings[0],
                    strings[1],
                    strings[2],
                    strings[3],
                    strings[4],
                    strings[5],
                    strings[6],
                    strings[7],
                    strings[8],
                    strings[9],
                    strings[10]
            );
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s == null) {
                Toast.makeText(application, "اطلاعات وارد شده صحیح نیست", Toast.LENGTH_LONG).show();
                return;
            }
            Gson gson = new Gson();
            try {
                ProfileResponse resp = gson.fromJson(s, ProfileResponse.class);
                profileResponse.setValue(resp);
            }
            catch (Exception e) {
                Toast.makeText(application, "اطلاعات وارد شده صحیح نیست", Toast.LENGTH_LONG).show();
            }
        }
    }
}
