package com.example.consoulingapp;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.consoulingapp.models.User;
import com.example.consoulingapp.network.LoginUtils;
import com.google.gson.Gson;

public class LoginRepository {
    public MutableLiveData<User> user = new MutableLiveData<>();


    public LoginRepository() {
    }

    public void authenticate(String username, String password){
        new fetchUser(user).execute(username, password);
    }

    public class fetchUser extends AsyncTask<String, Void, String>{
        MutableLiveData<User> user;

        public fetchUser(MutableLiveData<User> user) {
            this.user = user;
        }

        @Override
        protected String doInBackground(String... strings) {
            LoginUtils loginUtils = new LoginUtils();
            return loginUtils.authenticate(strings[0], strings[1]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Gson gson = new Gson();
            //Resp resp = gson.fromJson(s, Resp.class);
            User newUser = new User("ali", "akbari");
            user.setValue(newUser);
        }
    }
}
