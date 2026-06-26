package com.example.pz23_26mahotkin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class OnboardAdapter extends RecyclerView.Adapter<OnboardAdapter.OnboardViewHolder> {

    private List<OnboardItem> items;

    public OnboardAdapter(List<OnboardItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public OnboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_onboard, parent, false);
        return new OnboardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardViewHolder holder, int position) {
        OnboardItem item = items.get(position);
        holder.textTitle.setText(item.title);
        holder.textDescription.setText(item.description);
        holder.imageIllustration.setImageResource(item.imageResId);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class OnboardViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle;
        TextView textDescription;
        ImageView imageIllustration;

        public OnboardViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            textDescription = itemView.findViewById(R.id.textDescription);
            imageIllustration = itemView.findViewById(R.id.imageIllustration);
        }
    }
}