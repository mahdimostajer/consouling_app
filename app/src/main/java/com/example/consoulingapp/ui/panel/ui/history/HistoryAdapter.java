package com.example.consoulingapp.ui.panel.ui.history;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.consoulingapp.R;
import com.example.consoulingapp.models.Course;
import com.example.consoulingapp.models.History;
import com.example.consoulingapp.ui.panel.ui.shop.CourseAdapter;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class HistoryAdapter extends
        RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>{
    private final MutableLiveData<List<History>> history;
    private LayoutInflater mInflater;
    private Context context;
    private MaterialCardView selectedPackage;

    public HistoryAdapter(Context context, MutableLiveData<List<History>> history) {
        mInflater = LayoutInflater.from(context);
        this.history = history;
        this.context = context;

    }

    class HistoryViewHolder extends RecyclerView.ViewHolder {
        public final TextView start;
        public final TextView end;
        public final TextView consult;
        final HistoryAdapter mAdapter;
        public final View view;

        public HistoryViewHolder(View itemView, HistoryAdapter adapter) {
            super(itemView);
            mAdapter = adapter;
            view = itemView;
            start = itemView.findViewById(R.id.start);
            end = itemView.findViewById(R.id.end);
            consult = itemView.findViewById(R.id.consul);
        }
    }

    @NonNull
    @Override
    public HistoryAdapter.HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.course_item,
                parent, false);
        return new HistoryAdapter.HistoryViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.HistoryViewHolder holder, int position) {
        History current = history.getValue().get(position);
        holder.start.setText(current.start_date);
        holder.end.setText(current.end_date);
        holder.consult.setText(current.consultant.acount.first_name + current.consultant.acount.last_name);

    }

    @Override
    public int getItemCount() {
        return history.getValue().size();
    }

}
