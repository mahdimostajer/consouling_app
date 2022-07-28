package com.example.consoulingapp.ui.panel.ui.home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.consoulingapp.databinding.FragmentHomeActiveBinding;
import com.example.consoulingapp.databinding.FragmentHomeBinding;

public class HomeActiveFragment extends Fragment {

    private FragmentHomeActiveBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeActiveBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }
    private void initializeOnClickListeners(){
        binding.extendTermButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo: change tab
            }
        });
        binding.studyTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo: change tab
            }
        });
    }
    private void setTexts(){
//        binding.termStartText.set
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}