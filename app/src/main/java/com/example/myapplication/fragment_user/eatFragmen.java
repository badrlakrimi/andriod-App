package com.example.myapplication.fragment_user;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.activity_recipe_desc;
import com.example.myapplication.adapters.recipeAdapter;
import com.example.myapplication.models.recipe_class;
import com.example.myapplication.sqlLight.DatabaseHelper;

import java.util.ArrayList;

public class eatFragmen extends Fragment {

    DatabaseHelper db;

    ArrayList<recipe_class> mRecipeItems;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private recipeAdapter mRecipeAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_eat,container,false);
        View v  = inflater.inflate(R.layout.fragment_eat,container,false);


        insertionData(v);

       buildRecycleView(v);

        return v;
    }

    public void insertionData(View v){
        mRecipeItems = new ArrayList<>();
        /*recipeItems.add(new recipe_class("","badr lakrimi","recette.jpg",""));
        recipeItems.add(new recipe_class("","badr lakrimi","recette.jpg",""));
        recipeItems.add(new recipe_class("","badr lakrimi","recette.jpg",""));
        recipeItems.add(new recipe_class("","badr lakrimi","recette.jpg",""));
        recipeItems.add(new recipe_class("","badr lakrimi","recette.jpg",""));
        recipeItems.add(new recipe_class("","badr lakrimi","recette.jpg",""));
        recipeItems.add(new recipe_class("","badr lakrimi","recette.jpg",""));
        recipeItems.add(new recipe_class("","badr lakrimi","recette.jpg",""));
        recipeItems.add(new recipe_class("","badr lakrimi","recette.jpg",""));
        recipeItems.add(new recipe_class("","badr lakrimi","recette.jpg",""));
        recipeItems.add(new recipe_class("","badr lakrimi","recette.jpg",""));
        recipeItems.add(new recipe_class("","badr lakrimi","recette.jpg",""));*/

        db = new DatabaseHelper(v.getContext());
        Cursor cursor = db.DataRecette();
        while (cursor.moveToNext()){

            mRecipeItems.add(new recipe_class(cursor.getString(0), cursor.getString(1) , cursor.getString(2),cursor.getString(3)));
            Log.d("badrzwinf",cursor.getString(3));
        }
    }


    public void buildRecycleView(View v) {

        mRecyclerView = v.findViewById(R.id.recyclerview_recipe);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(v.getContext());
        mRecipeAdapter = new recipeAdapter(mRecipeItems);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mRecipeAdapter);

        mRecipeAdapter.setOnItemClickListenerRe(new recipeAdapter.OnItemClickListenerRe() {
            @Override
            public void onShowClick(int position) {

                //exampleItems.remove(position);

                Intent intent = new Intent(getContext(), activity_recipe_desc.class);
                intent.putExtra("nomRecipe",mRecipeItems.get(position).getRecipeNume());
                intent.putExtra("imgRecipe",mRecipeItems.get(position).getRecipeImage());
                intent.putExtra("descRecipe",mRecipeItems.get(position).getRecipeDesc());
                startActivity(intent);


            }
        });
    }
}





