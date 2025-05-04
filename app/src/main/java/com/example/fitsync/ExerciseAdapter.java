package com.example.fitsync;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {
    private List<Exercise> exercises;
    private Context context;

    public ExerciseAdapter(Context context, List<Exercise> exercises) {
        this.context = context;
        this.exercises = exercises;
    }

    public void updateData(List<Exercise> newExercises) {
        this.exercises = newExercises;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items_exercise, parent, false);
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        Exercise exercise = exercises.get(position);
        holder.nameText.setText("Exercise: " + exercise.getName());
        holder.equipmentText.setText("Equipment: " + exercise.getEquipment());
        holder.targetText.setText("Target: " + exercise.getTarget());
        holder.secondaryText.setText("Secondary: " + String.join(", ", exercise.getSecondaryMuscles()));
        holder.instructionsText.setText("Instructions:\n• " + String.join("\n• ", exercise.getInstructions()));

        Glide.with(context)
                .load(exercise.getGifUrl())
                .into(holder.gifImage);
    }

    @Override
    public int getItemCount() {
        return exercises != null ? exercises.size() : 0;
    }

    public static class ExerciseViewHolder extends RecyclerView.ViewHolder {
        TextView nameText, equipmentText, targetText, secondaryText, instructionsText;
        ImageView gifImage;

        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.nameText);
            equipmentText = itemView.findViewById(R.id.equipmentText);
            targetText = itemView.findViewById(R.id.targetText);
            secondaryText = itemView.findViewById(R.id.secondaryText);
            instructionsText = itemView.findViewById(R.id.instructionsText);
            gifImage = itemView.findViewById(R.id.gifImage);
        }
    }
}
