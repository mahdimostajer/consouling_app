package com.example.consoulingapp.ui.panel.ui.shop;

import android.graphics.Color;
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
import androidx.lifecycle.ViewModelProvider;

import com.example.consoulingapp.databinding.FragmentShopBinding;
import com.google.android.material.card.MaterialCardView;

public class ShopFragment extends Fragment {

    private FragmentShopBinding binding;
    @Nullable
    private MaterialCardView selectedPackage;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        ShopViewModel shopViewModel =
//                new ViewModelProvider(this).get(ShopViewModel.class);

        binding = FragmentShopBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        setOnCLickListeners();
        return root;
    }
    private void setOnCLickListeners() {
        View.OnClickListener onClickListener = view -> {
            unSelectAllPackages();
            view.setBackgroundColor(Color.CYAN);
            selectedPackage = (MaterialCardView) view;

        };
            binding.package1.setOnClickListener(onClickListener);
            binding.package2.setOnClickListener(onClickListener);
            binding.package3.setOnClickListener(onClickListener);
            binding.package4.setOnClickListener(onClickListener);
            binding.payButton.setOnClickListener(view -> {
                String text = ((TextView) ((ConstraintLayout) selectedPackage.getChildAt(0)).getChildAt(0)).getText().toString();
                Toast.makeText(getActivity(), text, Toast.LENGTH_LONG).show();

            });
    }
    private void unSelectAllPackages(){
        selectedPackage = null;
        binding.package1.setBackgroundColor(Color.WHITE);
        binding.package2.setBackgroundColor(Color.WHITE);
        binding.package3.setBackgroundColor(Color.WHITE);
        binding.package4.setBackgroundColor(Color.WHITE);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}