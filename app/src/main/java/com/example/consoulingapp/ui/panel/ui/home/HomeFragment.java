package com.example.consoulingapp.ui.panel.ui.home;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.consoulingapp.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private SharedPreferences mPreferences;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.swiperefresh.setOnRefreshListener(
                () -> {
                    homeViewModel.getDashboard();
                    homeViewModel.getProfile();
                    binding.swiperefresh.setRefreshing(false);
                }
        );

        homeViewModel.dashboardResponse.observe(getViewLifecycleOwner(),  dashboardResponse -> {
            if (dashboardResponse != null) {
                FragmentManager fm = getParentFragmentManager();
                Fragment fragment = fm.findFragmentByTag("home_fragment");
                if(fragment != null)
                    fm.beginTransaction().remove(fragment).commit();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                Fragment fm2;
                if(dashboardResponse.active_course == null){
                    fm2 = new HomeInactiveFragment();
                }else{
                    fm2 = new HomeActiveFragment();
                }

                fragmentTransaction.add(binding.fragment1.getId(), fm2, "home_fragment");
                fragmentTransaction.commit();
            }
        });
        if(homeViewModel.dashboardResponse.getValue() == null)
            homeViewModel.getDashboard();
        if(homeViewModel.profileResponse.getValue() == null)
            homeViewModel.getProfile();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}