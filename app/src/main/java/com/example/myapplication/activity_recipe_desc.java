package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class activity_recipe_desc extends AppCompatActivity {


    String nomRecipe = "";
    String imgRecipe = "";
    String descRecipe = "";

    private TextView nom_Recipe,desc_Recipe;
    private ImageView img_recipe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_desc);

        //back button + title text
        Intent intent = getIntent();
        nomRecipe = intent.getStringExtra("nomRecipe");
        imgRecipe = intent.getStringExtra("imgRecipe");
        descRecipe = intent.getStringExtra("descRecipe");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(nomRecipe);
        }

        nom_Recipe = findViewById(R.id.recipe_desc_nom);
        img_recipe = findViewById(R.id.recipe_desc_img);
        desc_Recipe = findViewById(R.id.recipe_desc_text);

        nom_Recipe.setText(nomRecipe);
        desc_Recipe.setText(descRecipe);

        Uri imgUri=Uri.parse("/data/data/com.example.myapplication/databases/images/recete/"+imgRecipe);
        img_recipe.setImageURI(imgUri);

    }

    //back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.onBackPressed();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
