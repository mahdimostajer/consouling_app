package com.example.consoulingapp.ui.panel.ui.home;

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

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        homeViewModel.profileResponse.observe(getViewLifecycleOwner(),  profile -> {
            if (profile != null) {
                FragmentManager fm = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                Fragment fm2;
                if(profile.has_contract){
                    fm2 = new HomeActiveFragment();
                }else{
                    fm2 = new HomeInactiveFragment();
                }

                fragmentTransaction.add(binding.fragment1.getId(), fm2, "home_fragment");
                fragmentTransaction.commit();
            }
        });

        homeViewModel.getProfile();


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}