package com.example.miniprojectsukhada.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.miniprojectsukhada.R;
import com.example.miniprojectsukhada.models.SleepRecord;
import java.util.ArrayList;

public class SleepAdapter extends RecyclerView.Adapter<SleepAdapter.SleepViewHolder> {

    private ArrayList<SleepRecord> records;

    public SleepAdapter(ArrayList<SleepRecord> records) {
        this.records = records;
    }

    @NonNull
    @Override
    public SleepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sleep_record, parent, false);
        return new SleepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SleepViewHolder holder, int position) {
        SleepRecord record = records.get(position);
        holder.tvDate.setText(record.date);
        holder.tvDuration.setText(record.getFormattedDuration());
        holder.tvQuality.setText("Quality: " + record.quality);
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    public static class SleepViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate, tvDuration, tvQuality;

        public SleepViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvDuration = itemView.findViewById(R.id.tvDuration);
            tvQuality = itemView.findViewById(R.id.tvQuality);
        }
    }
}