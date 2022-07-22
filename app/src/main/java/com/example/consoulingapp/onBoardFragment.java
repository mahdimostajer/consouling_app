package com.example.consoulingapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ramotion.paperonboarding.PaperOnboardingFragment;

public class onBoardFragment extends PaperOnboardingFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View parentView = super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_on_board, container, false);
        TextView skipTextview = view.findViewById(R.id.skip_textview);
        skipTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        Log.d("framgent okay", "fragment okay");
        ((ViewGroup)parentView).addView(skipTextview);
        return null;
    }
}