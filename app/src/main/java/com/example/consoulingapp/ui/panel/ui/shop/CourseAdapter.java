package com.example.consoulingapp.ui.panel.ui.shop;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.consoulingapp.models.Course;
import com.example.consoulingapp.ui.panel.PanelActivity;
import com.google.android.material.card.MaterialCardView;
import com.google.gson.Gson;
import com.example.consoulingapp.R;


import java.lang.reflect.Type;
import java.util.List;

public class CourseAdapter extends
        RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private final MutableLiveData<List<Course>> courseList;
    private LayoutInflater mInflater;
    private Context context;
    private MaterialCardView selectedPackage;
    private MutableLiveData<Integer> selectedId;

    public CourseAdapter(Context context, MutableLiveData<List<Course>> courseList, MutableLiveData<Integer> selectedId) {
        mInflater = LayoutInflater.from(context);
        this.courseList = courseList;
        this.context = context;
        this.selectedId = selectedId;

    }

    class CourseViewHolder extends RecyclerView.ViewHolder {
        public final TextView title;
        public final TextView price;
        final CourseAdapter mAdapter;
        public final View view;

        public CourseViewHolder(View itemView, CourseAdapter adapter) {
            super(itemView);
            mAdapter = adapter;
            view = itemView;
            title = itemView.findViewById(R.id.package_name);
            price = itemView.findViewById(R.id.price);
        }
    }

    @NonNull
    @Override
    public CourseAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.course_item,
                parent, false);
        return new CourseViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.CourseViewHolder holder, int position) {
        Course current = courseList.getValue().get(position);
        holder.title.setText("بسته مشاوره " + current.persian_month + " ماهه");
        holder.price.setText(String.valueOf(current.base_price));
        holder.view.setOnClickListener(view -> {
            if(selectedPackage != null){
                selectedPackage.setBackgroundColor(Color.WHITE);
            }
            selectedPackage = (MaterialCardView) view;
            view.setBackgroundColor(Color.CYAN);
            selectedId.setValue(current.id);
        });
    }

    @Override
    public int getItemCount() {
        return courseList.getValue().size();
    }


}
