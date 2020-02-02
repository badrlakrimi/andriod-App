package com.example.myapplication.adapters;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.models.recipe_class;

import java.util.ArrayList;

public class recipeAdapter extends RecyclerView.Adapter<recipeAdapter.RecipeViewHolder> {

    private ArrayList<recipe_class> mRecipeList;

    private OnItemClickListenerRe mListener;

    public interface OnItemClickListenerRe {
        void onShowClick(int position);
    }

    public void setOnItemClickListenerRe(OnItemClickListenerRe listener) {
        mListener = listener;
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder{
        ConstraintLayout recipe_l;
        public ImageView recipe_img;
        public TextView recipe_name;

        public RecipeViewHolder(@NonNull View itemView, final OnItemClickListenerRe listener) {
            super(itemView);
            recipe_l = itemView.findViewById(R.id.recipe_l);
            recipe_img = itemView.findViewById(R.id.recipe_img);
            recipe_name = itemView.findViewById(R.id.recipe_text);


            recipe_l.setOnClickListener(new View.OnClickListener() {
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

    public recipeAdapter(ArrayList<recipe_class> recipeList) {
        mRecipeList = recipeList;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recipe_item,viewGroup,false);
        RecipeViewHolder recipeViewHolder = new RecipeViewHolder(v,mListener);
        return recipeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder recipeViewHolder, int i) {
        recipe_class currentItem = mRecipeList.get(i);
        recipeViewHolder.recipe_name.setText(currentItem.getRecipeNume());

        Uri imgUri=Uri.parse("/data/data/com.example.myapplication/databases/images/recete/"+currentItem.getRecipeImage());
        Log.d("hibazwina",currentItem.getRecipeImage());
        recipeViewHolder.recipe_img.setImageURI(imgUri);
    }

    @Override
    public int getItemCount() {
        return mRecipeList.size();
    }
}
