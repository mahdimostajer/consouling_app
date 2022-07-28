package com.example.consoulingapp.ui.panel.ui.profile;

import static android.content.Context.MODE_PRIVATE;
import static com.example.consoulingapp.ui.login.LoginActivity.ACCESS_TOKEN;
import static com.example.consoulingapp.ui.login.LoginActivity.sharedPrefFile;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.consoulingapp.databinding.FragmentProfileBinding;
import com.example.consoulingapp.models.ProfileResponse;
import com.example.consoulingapp.models.RegisterResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    @Nullable
    private SharedPreferences mPreferences;
    ProfileResponse profile;
    ProfileViewModel profileViewModel;
    Map<Integer, String> ids;
    Map<String, Integer> swappedIds;

    @RequiresApi(api = Build.VERSION_CODES.R)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        setIdMaps();
        setFields();
        binding.changeDetailsButton.setOnClickListener(view -> changeDetails());


        mPreferences = requireContext().getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        String token = mPreferences.getString(ACCESS_TOKEN, null);
        profileViewModel.profileResponse.observe(requireActivity(), profileResponse -> {

        });
        return root;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setIdMaps() {
        ids = new HashMap<Integer, String>() {{
            put(binding.underGraduated.getId(), "under_graduated");
            put(binding.graduated.getId(), "graduated");
            put(binding.g10.getId(), "10");
            put(binding.g11.getId(), "11");
            put(binding.g12.getId(), "12");
            put(binding.gradeOther.getId(), "other_grade");
            put(binding.experimental.getId(), "experimental");
            put(binding.mathmatics.getId(), "mathmatics");
            put(binding.humanities.getId(), "humanities");
            put(binding.fieldOther.getId(), "other_field");
            put(binding.sampad.getId(), "sampad");
            put(binding.numouneh.getId(), "numouneh");
            put(binding.dolati.getId(), "dolati");
            put(binding.gheireDolati.getId(), "gheire_dolati");
            put(binding.schoolTypeOther.getId(), "other_school_type");
            put(binding.ghalamchi.getId(), "ghalamchi");
            put(binding.gaj.getId(), "gaj");
            put(binding.sanjesh.getId(), "sanjesh");
            put(binding.gozine2.getId(), "gozine2");
            put(binding.extraCurricularExamOther.getId(), "other_exam");

        }};
        swappedIds = ids.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setFields(){
        profile = ProfileResponse.profileResponse;
        RegisterResponse account = profile.account;

        setRadioButtons();

        binding.phoneNumber.getEditText().setText(account.phone_number);
        binding.province.getEditText().setText(account.province);
        binding.gender.getEditText().setText(account.gender);
        binding.firstName.getEditText().setText(account.first_name);
        binding.lastName.getEditText().setText(account.last_name);
        binding.creditCardNo.getEditText().setText(account.credit_card_no);
        binding.birthDate.getEditText().setText(account.birth_date);


        binding.schoolName.getEditText().setText(profile.school_name);
        binding.averageLevel.getEditText().setText(profile.average_level);
        binding.latestGrade.getEditText().setText(profile.latest_grade);
        binding.description.getEditText().setText(profile.description);
        binding.parentCareer.getEditText().setText(profile.parent_career);
        binding.parentPhoneNumber.getEditText().setText(profile.parent_phone_number);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setRadioButtons() {
        RadioGroup grade = binding.grade;
        RadioGroup state = binding.state;
        RadioGroup field = binding.field;
        RadioGroup school_type = binding.schoolType;
        RadioGroup extra_curricular_exam = binding.extraCurricularExam;

        Integer stateCheck = swappedIds.getOrDefault(profile.state,null);
        if (stateCheck != null) {
            state.check(stateCheck);
        }

        Integer gradeCheck = swappedIds.getOrDefault(profile.grade,null);
        if (gradeCheck != null) {
            grade.check(gradeCheck);
        }

        Integer fieldCheck = swappedIds.getOrDefault(profile.field,null);
        if (fieldCheck != null) {
            field.check(fieldCheck);
        }

        Integer school_typeCheck = swappedIds.getOrDefault(profile.school_type,null);
        if (school_typeCheck != null) {
            school_type.check(school_typeCheck);
        }

        Integer extra_curricular_examCheck = swappedIds.getOrDefault(profile.extra_curricular_exam,null);
        if (extra_curricular_examCheck != null) {
            extra_curricular_exam.check(extra_curricular_examCheck);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    private void changeDetails(){
        RadioGroup grade = binding.grade;
        RadioGroup state = binding.state;
        RadioGroup field = binding.field;
        RadioGroup school_type = binding.schoolType;
        RadioGroup extra_curricular_exam = binding.extraCurricularExam;

        String grade_string;
        String state_string;
        String field_string;
        String school_type_string;
        String extra_curricular_exam_string;

        state_string = ids.get(state.getCheckedRadioButtonId());
        grade_string = ids.get(grade.getCheckedRadioButtonId());
        field_string = ids.get(field.getCheckedRadioButtonId());
        school_type_string = ids.get(school_type.getCheckedRadioButtonId());
        extra_curricular_exam_string = ids.get(extra_curricular_exam.getCheckedRadioButtonId());


        profileViewModel.changeDetails(
                grade_string,
                state_string,
                field_string,
                binding.schoolName.getEditText().getText().toString(),
                school_type_string,
                binding.averageLevel.getEditText().getText().toString(),
                extra_curricular_exam_string,
                binding.latestGrade.getEditText().getText().toString(),
                binding.description.getEditText().getText().toString(),
                binding.parentCareer.getEditText().getText().toString(),
                binding.parentPhoneNumber.getEditText().getText().toString()
        );
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}