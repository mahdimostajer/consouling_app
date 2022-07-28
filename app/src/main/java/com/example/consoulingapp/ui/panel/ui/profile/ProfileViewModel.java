package com.example.consoulingapp.ui.panel.ui.profile;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.consoulingapp.LoginRepository;
import com.example.consoulingapp.ProfileRepository;
import com.example.consoulingapp.models.Course;
import com.example.consoulingapp.models.ProfileResponse;

import okhttp3.RequestBody;

public class ProfileViewModel extends ViewModel {

    public MutableLiveData<ProfileResponse> profileResponse;

    public ProfileRepository profileRepository;

    public ProfileViewModel() {
        this.profileRepository = new ProfileRepository();
        this.profileResponse = profileRepository.profileResponse;
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
            String parent_phone_number,
            String ranked_in_country,
            String ranked_in_area
    ){
        profileRepository.changeDetails(
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
                parent_phone_number,
                ranked_in_country,
                ranked_in_area
        );
    }


    public void setProfile(RequestBody requestBody){

    }
}
