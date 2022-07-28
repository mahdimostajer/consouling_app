package com.example.consoulingapp.ui.panelCounsel.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.consoulingapp.databinding.FragmentHomeBinding;
import com.example.consoulingapp.databinding.FragmentHomeCounselBinding;

public class HomeCounselFragment extends Fragment {

    private FragmentHomeCounselBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeCounselViewModel homeCounselViewModel =
                new ViewModelProvider(this).get(HomeCounselViewModel.class);

        binding = FragmentHomeCounselBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}