package com.example.consoulingapp.ui.panel.ui.profile;

import android.graphics.Color;
import static android.content.Context.MODE_PRIVATE;
import static com.example.consoulingapp.ui.login.LoginActivity.ACCESS_TOKEN;
import static com.example.consoulingapp.ui.login.LoginActivity.sharedPrefFile;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.consoulingapp.ProfileRepository;
import com.example.consoulingapp.R;
import com.example.consoulingapp.databinding.FragmentProfileBinding;
import com.example.consoulingapp.databinding.FragmentShopBinding;
import com.example.consoulingapp.models.ProfileResponse;
import com.example.consoulingapp.models.RegisterResponse;
import com.example.consoulingapp.ui.register.RegisterActivity;
import com.google.android.material.card.MaterialCardView;
import com.example.consoulingapp.models.Course;
import com.google.android.material.textfield.TextInputLayout;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    @Nullable
    private SharedPreferences mPreferences;
    ProfileResponse profile;
    ProfileViewModel profileViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        setFields();
        binding.changeDetailsButton.setOnClickListener(view -> changeDetails());

        mPreferences = requireContext().getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        String token = mPreferences.getString(ACCESS_TOKEN, null);
        profileViewModel.profileResponse.observe(requireActivity(), profileResponse -> {

        });
        return root;
    }
    private void setFields(){
        profile = ProfileResponse.profileResponse;
        RegisterResponse account = profile.account;

        setRadioButtons();

        ((TextInputLayout)getView().findViewById(R.id.phone_number)).getEditText().setText(account.phone_number);
        ((TextInputLayout)getView().findViewById(R.id.province)).getEditText().setText(account.province);
        ((TextInputLayout)getView().findViewById(R.id.gender)).getEditText().setText(account.gender);
        ((TextInputLayout)getView().findViewById(R.id.first_name)).getEditText().setText(account.first_name);
        ((TextInputLayout)getView().findViewById(R.id.last_name)).getEditText().setText(account.last_name);
        ((TextInputLayout)getView().findViewById(R.id.credit_card_no)).getEditText().setText(account.credit_card_no);
        ((TextInputLayout)getView().findViewById(R.id.birth_date)).getEditText().setText(account.birth_date);


        ((TextInputLayout)getView().findViewById(R.id.school_name)).getEditText().setText(profile.school_name);
        ((TextInputLayout)getView().findViewById(R.id.average_level)).getEditText().setText(profile.average_level);
        ((TextInputLayout)getView().findViewById(R.id.latest_grade)).getEditText().setText(profile.latest_grade);
        ((TextInputLayout)getView().findViewById(R.id.description)).getEditText().setText(profile.description);
        ((TextInputLayout)getView().findViewById(R.id.parent_career)).getEditText().setText(profile.parent_career);
        ((TextInputLayout)getView().findViewById(R.id.parent_phone_number)).getEditText().setText(profile.parent_phone_number);
    }

    private void setRadioButtons() {
        RadioGroup grade = getView().findViewById(R.id.grade);
        RadioGroup state = getView().findViewById(R.id.state);
        RadioGroup field = getView().findViewById(R.id.field);
        RadioGroup school_type = getView().findViewById(R.id.school_type);
        RadioGroup extra_curricular_exam = getView().findViewById(R.id.extra_curricular_exam);

        switch (profile.state){
            case "under_graduated": state.check(R.id.under_graduated);
            case "graduated": state.check(R.id.graduated);
        }
        switch (profile.grade){
            case "10": grade.check(R.id.g10);
            case "11": grade.check(R.id.g11);
            case "12": grade.check(R.id.g12);
            case "other": grade.check(R.id.grade_other);
        }
        switch (profile.field){
            case "experimental": field.check(R.id.experimental);
            case "mathmatics": field.check(R.id.mathmatics);
            case "humanities": field.check(R.id.humanities);
            case "other": field.check(R.id.field_other);
        }
        switch (profile.school_type){
            case "sampad": school_type.check(R.id.sampad);
            case "numouneh": school_type.check(R.id.numouneh);
            case "dolati": school_type.check(R.id.dolati);
            case "gheire_dolati": school_type.check(R.id.gheire_dolati);
            case "other": school_type.check(R.id.school_type_other);
        }
        switch (profile.extra_curricular_exam){
            case "ghalamchi": extra_curricular_exam.check(R.id.ghalamchi);
            case "gaj": extra_curricular_exam.check(R.id.gaj);
            case "sanjesh": extra_curricular_exam.check(R.id.sanjesh);
            case "gozine2": extra_curricular_exam.check(R.id.gozine2);
            case "other": extra_curricular_exam.check(R.id.extra_curricular_exam_other);
        }


    }

    private void changeDetails(){
        RadioGroup grade = getView().findViewById(R.id.grade);
        RadioGroup state = getView().findViewById(R.id.state);
        RadioGroup field = getView().findViewById(R.id.field);
        RadioGroup school_type = getView().findViewById(R.id.school_type);
        RadioGroup extra_curricular_exam = getView().findViewById(R.id.extra_curricular_exam);

        String grade_string = null;
        String state_string = null;
        String field_string = null;
        String school_type_string = null;
        String extra_curricular_exam_string = null;

        switch (state.getCheckedRadioButtonId()){
            case R.id.under_graduated: state_string = "under_graduated";
            case R.id.graduated: state_string = "graduated";
        }
        switch (grade.getCheckedRadioButtonId()){
            case R.id.g10: grade_string = "10";
            case R.id.g11: grade_string = "11";
            case R.id.g12: grade_string = "12";
            case R.id.other: grade_string = "other";
        }
        switch (field.getCheckedRadioButtonId()){
            case R.id.experimental: field_string = "experimental";
            case R.id.mathmatics: field_string = "mathmatics";
            case R.id.humanities: field_string = "humanities";
            case R.id.field_other: field_string = "other";
        }
        switch (school_type.getCheckedRadioButtonId()){
            case R.id.sampad: school_type_string = "sampad";
            case R.id.numouneh: school_type_string = "numouneh";
            case R.id.dolati: school_type_string = "dolati";
            case R.id.gheire_dolati: school_type_string = "gheire_dolati";
            case R.id.school_type_other: school_type_string = "other";
        }

        switch (extra_curricular_exam.getCheckedRadioButtonId()){
            case R.id.ghalamchi: extra_curricular_exam_string = "ghalamchi";
            case R.id.gaj: extra_curricular_exam_string = "gaj";
            case R.id.sanjesh: extra_curricular_exam_string = "sanjesh";
            case R.id.gozine2: extra_curricular_exam_string = "gozine2";
            case R.id.extra_curricular_exam_other: extra_curricular_exam_string = "other";
        }




        profileViewModel.changeDetails(
                grade_string,
                state_string,
                field_string,
                ((TextInputLayout)getView().findViewById(R.id.school_name)).getEditText().getText().toString(),
                school_type_string,
                ((TextInputLayout)getView().findViewById(R.id.average_level)).getEditText().getText().toString(),
                extra_curricular_exam_string,
                ((TextInputLayout)getView().findViewById(R.id.latest_grade)).getEditText().getText().toString(),
                ((TextInputLayout)getView().findViewById(R.id.description)).getEditText().getText().toString(),
                ((TextInputLayout)getView().findViewById(R.id.parent_career)).getEditText().getText().toString(),
                ((TextInputLayout)getView().findViewById(R.id.parent_phone_number)).getEditText().getText().toString()
        );
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}