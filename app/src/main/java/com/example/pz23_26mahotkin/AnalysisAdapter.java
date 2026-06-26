package com.example.pz23_26mahotkin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AnalysisAdapter extends RecyclerView.Adapter<AnalysisAdapter.ViewHolder> {

    private List<AnalysisItem> items;

    public AnalysisAdapter(List<AnalysisItem> items) {
        this.items = items;
    }

    public void updateList(List<AnalysisItem> newList) {
        this.items = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_analysis, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AnalysisItem item = items.get(position);
        holder.tvName.setText(item.getName());
        holder.tvDuration.setText(item.getDuration());
        holder.tvPrice.setText(item.getPrice());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDuration, tvPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvAnalysisName);
            tvDuration = itemView.findViewById(R.id.tvAnalysisDuration);
            tvPrice = itemView.findViewById(R.id.tvAnalysisPrice);
        }
    }
}