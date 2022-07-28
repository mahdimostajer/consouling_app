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

        ((TextInputLayout)getView().findViewById(R.id.phone_number)).getEditText().setText(account.phone_number);
        ((TextInputLayout)getView().findViewById(R.id.province)).getEditText().setText(account.province);
        ((TextInputLayout)getView().findViewById(R.id.gender)).getEditText().setText(account.gender);
        ((TextInputLayout)getView().findViewById(R.id.first_name)).getEditText().setText(account.first_name);
        ((TextInputLayout)getView().findViewById(R.id.last_name)).getEditText().setText(account.last_name);
        ((TextInputLayout)getView().findViewById(R.id.credit_card_no)).getEditText().setText(account.credit_card_no);
        ((TextInputLayout)getView().findViewById(R.id.birth_date)).getEditText().setText(account.birth_date);

        ((TextInputLayout)getView().findViewById(R.id.grade)).getEditText().setText(profile.grade);
        ((TextInputLayout)getView().findViewById(R.id.state)).getEditText().setText(profile.state);
        ((TextInputLayout)getView().findViewById(R.id.field)).getEditText().setText(profile.field);
        ((TextInputLayout)getView().findViewById(R.id.school_name)).getEditText().setText(profile.school_name);
        ((TextInputLayout)getView().findViewById(R.id.school_type)).getEditText().setText(profile.school_type);
        ((TextInputLayout)getView().findViewById(R.id.average_level)).getEditText().setText(profile.average_level);
        ((TextInputLayout)getView().findViewById(R.id.extra_curricular_exam)).getEditText().setText(profile.extra_curricular_exam);
        ((TextInputLayout)getView().findViewById(R.id.latest_grade)).getEditText().setText(profile.latest_grade);
        ((TextInputLayout)getView().findViewById(R.id.description)).getEditText().setText(profile.description);
        ((TextInputLayout)getView().findViewById(R.id.parent_career)).getEditText().setText(profile.parent_career);
        ((TextInputLayout)getView().findViewById(R.id.parent_phone_number)).getEditText().setText(profile.parent_phone_number);
        ((TextInputLayout)getView().findViewById(R.id.ranked_in_country)).getEditText().setText(profile.ranked_in_country);
        ((TextInputLayout)getView().findViewById(R.id.ranked_in_area)).getEditText().setText(profile.ranked_in_area);
    }
    private void changeDetails(){
        profileViewModel.changeDetails(
                ((TextInputLayout)getView().findViewById(R.id.grade)).getEditText().getText().toString(),
                ((TextInputLayout)getView().findViewById(R.id.state)).getEditText().getText().toString(),
                ((TextInputLayout)getView().findViewById(R.id.field)).getEditText().getText().toString(),
                ((TextInputLayout)getView().findViewById(R.id.school_name)).getEditText().getText().toString(),
                ((TextInputLayout)getView().findViewById(R.id.school_type)).getEditText().getText().toString(),
                ((TextInputLayout)getView().findViewById(R.id.average_level)).getEditText().getText().toString(),
                ((TextInputLayout)getView().findViewById(R.id.extra_curricular_exam)).getEditText().getText().toString(),
                ((TextInputLayout)getView().findViewById(R.id.latest_grade)).getEditText().getText().toString(),
                ((TextInputLayout)getView().findViewById(R.id.description)).getEditText().getText().toString(),
                ((TextInputLayout)getView().findViewById(R.id.parent_career)).getEditText().getText().toString(),
                ((TextInputLayout)getView().findViewById(R.id.parent_phone_number)).getEditText().getText().toString(),
                ((TextInputLayout)getView().findViewById(R.id.ranked_in_country)).getEditText().getText().toString(),
                ((TextInputLayout)getView().findViewById(R.id.ranked_in_area)).getEditText().getText().toString()
        );
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}