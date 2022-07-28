package com.example.consoulingapp.ui.panel.ui.history;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.consoulingapp.databinding.FragmentHistoryBinding;
import com.example.consoulingapp.models.Course;
import com.example.consoulingapp.models.History;
import com.example.consoulingapp.ui.panel.ui.shop.CourseAdapter;

import java.util.List;

public class HistoryFragment extends Fragment {

    private FragmentHistoryBinding binding;
    private HistoryAdapter historyAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HistoryViewModel historyViewModel =
                new ViewModelProvider(this).get(HistoryViewModel.class);

        binding = FragmentHistoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        historyViewModel.getHistory();
        historyViewModel.history.observe(requireActivity(), new Observer<List<History>>() {
            @Override
            public void onChanged(List<History> histories) {
                historyAdapter = new HistoryAdapter(requireContext(), historyViewModel.history);
                binding.historyRecyclerview.setAdapter(historyAdapter);
                binding.historyRecyclerview.setLayoutManager(new LinearLayoutManager(requireContext()));
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}