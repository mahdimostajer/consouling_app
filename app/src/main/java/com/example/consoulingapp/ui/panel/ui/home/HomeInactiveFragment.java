package com.example.consoulingapp.ui.panel.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.consoulingapp.R;
import com.example.consoulingapp.databinding.FragmentHomeActiveBinding;
import com.example.consoulingapp.databinding.FragmentHomeInactiveBinding;

public class HomeInactiveFragment extends Fragment {

    private FragmentHomeInactiveBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeInactiveBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        initializeOnClickListeners();

        return root;
    }
    private void initializeOnClickListeners(){
        binding.activateTermButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo: change tab
            }
        });
        binding.activateTermButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo: change tab
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}