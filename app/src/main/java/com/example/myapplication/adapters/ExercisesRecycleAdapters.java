package com.example.myapplication.adapters;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.models.exercices_class;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ExercisesRecycleAdapters extends RecyclerView.Adapter<ExercisesRecycleAdapters.ViewHolder> {

    private ArrayList<exercices_class> mExercices = new ArrayList<>();

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onShowClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView exercises, nbrSeries,idExercice;
        LinearLayout exercises_l;
        ImageView imgExercice;

        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            exercises = itemView.findViewById(R.id.lbl_exercises);
            nbrSeries = itemView.findViewById(R.id.lbl_nbrSeries);
            exercises_l = itemView.findViewById(R.id.exercises_l);
            imgExercice = itemView.findViewById(R.id.img_exercises);

            exercises_l.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onShowClick(position);
                        }
                    }
                }
            });

        }
    }

        public ExercisesRecycleAdapters(ArrayList<exercices_class> exercises) {
            mExercices = exercises;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.exercises_item, viewGroup, false);
        ViewHolder exercisesViewHolder = new ViewHolder(view,mListener);
        return exercisesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        exercices_class  currentItem = mExercices.get(i);

        viewHolder.exercises.setText(currentItem.getNom_exercises());
        viewHolder.nbrSeries.setText(currentItem.getNbrSets_exercises());

        Uri imgUri=Uri.parse("/data/data/com.example.myapplication/databases/images/"+currentItem.getImg_exercises());
        Log.d("hibazwina",currentItem.getImg_exercises());
        viewHolder.imgExercice.setImageURI(imgUri);


    }

    @Override
    public int getItemCount() {
        return mExercices.size();
    }

}
