package com.example.consoulingapp.ui.panel.ui.profile;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.consoulingapp.LoginRepository;
import com.example.consoulingapp.ProfileRepository;
import com.example.consoulingapp.models.Course;
import com.example.consoulingapp.models.ProfileResponse;

import okhttp3.RequestBody;

public class ProfileViewModel extends AndroidViewModel {

    public MutableLiveData<ProfileResponse> profileResponse;

    public ProfileRepository profileRepository;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        this.profileRepository = new ProfileRepository(application);
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
            String parent_phone_number
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
                parent_phone_number
        );
    }


    public void setProfile(RequestBody requestBody){

    }
}
