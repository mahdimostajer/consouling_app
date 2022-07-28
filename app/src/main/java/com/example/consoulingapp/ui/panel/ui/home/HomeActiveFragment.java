package com.example.consoulingapp.ui.panel.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.consoulingapp.databinding.FragmentHomeActiveBinding;
import com.example.consoulingapp.models.DashboardResponse;
import com.github.eloyzone.jalalicalendar.DateConverter;
import com.github.eloyzone.jalalicalendar.JalaliDate;
import com.github.eloyzone.jalalicalendar.JalaliDateFormatter;

public class HomeActiveFragment extends Fragment {

    private FragmentHomeActiveBinding binding;
    private DashboardResponse dashboardResponse;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeActiveBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        if(homeViewModel.dashboardResponse.getValue() == null)
            homeViewModel.getDashboard();
        homeViewModel.dashboardResponse.observe(getViewLifecycleOwner(),  dashboardResponse -> {
            if (dashboardResponse != null) {
                this.dashboardResponse = dashboardResponse;
                setFields();
            }
        });
        return root;
    }

    private void setFields() {
        binding.termStart.setText(convertToJalali(dashboardResponse.active_course.start_date));
        binding.termEnd.setText(convertToJalali(dashboardResponse.active_course.end_date));
        binding.consultant.setText(String.format("%s %s", dashboardResponse.active_course.consultant.account.first_name, dashboardResponse.active_course.consultant.account.last_name));
        binding.homeRemainingDaysTextView.setText(String.valueOf(dashboardResponse.active_course.days_left));
        binding.homeTotalDaysTextView.setText(String.valueOf(dashboardResponse.active_course.total_days));
    }

    public String convertToJalali(String date){

        DateConverter dateConverter = new DateConverter();
        String[] ymd = date.split("-");
        JalaliDate jalaliDate = dateConverter.gregorianToJalali(Integer.parseInt(ymd[0]), Integer.parseInt(ymd[1]), Integer.parseInt(ymd[2]));
        return jalaliDate.format(new JalaliDateFormatter("yyyy M dd", JalaliDateFormatter.FORMAT_IN_PERSIAN));
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}