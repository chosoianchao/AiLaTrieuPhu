package com.example.ailatrieuphu.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ailatrieuphu.R;
import com.example.ailatrieuphu.databases.entities.HighScore;

import java.util.List;

public class HighScoreAdapter extends RecyclerView.Adapter<HighScoreAdapter.HighScoreViewHolder> {
    private final Context context;
    private List<HighScore> highScore;

    public HighScoreAdapter(Context context, List<HighScore> highScore) {
        this.context = context;
        this.highScore = highScore;
    }

    @NonNull
    @Override
    public HighScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_list_high_score, parent, false);
        return new HighScoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HighScoreViewHolder holder, int position) {
        HighScore item = highScore.get(position);
        holder.tvName.setText(item.name);
        holder.tvScore.setText(String.format("%s VNĐ", item.score));
    }

    @Override
    public int getItemCount() {
        return highScore.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<HighScore> highScores) {
        highScore = highScores;
        notifyDataSetChanged();
    }

    public static class HighScoreViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvScore;

        public HighScoreViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvScore = itemView.findViewById(R.id.tv_score);
        }
    }
}
