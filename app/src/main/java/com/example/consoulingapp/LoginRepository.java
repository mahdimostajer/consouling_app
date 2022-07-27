package com.example.consoulingapp;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.consoulingapp.models.LoginResp;
import com.example.consoulingapp.models.User;
import com.example.consoulingapp.network.LoginUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

public class LoginRepository {
    public MutableLiveData<LoginResp> user = new MutableLiveData<>();
    public Application application;

    public LoginRepository(Application application) {
        this.application = application;
    }

    public void authenticate(String username, String password) {
        new fetchUser(user, application).execute(username, password);
    }

    public class fetchUser extends AsyncTask<String, Void, String> {
        MutableLiveData<LoginResp> user;
        Application application;

        public fetchUser(MutableLiveData<LoginResp> user, Application application
        ) {
            this.user = user;
            this.application = application;
        }

        @Override
        protected String doInBackground(String... strings) {
            LoginUtils loginUtils = new LoginUtils();
            return loginUtils.authenticate(strings[0], strings[1]);
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
                LoginResp resp = gson.fromJson(s, LoginResp.class);
                user.setValue(resp);
            }
            catch (Exception e) {
                Toast.makeText(application, "اطلاعات وارد شده صحیح نیست", Toast.LENGTH_LONG).show();
            }
        }
    }
}
