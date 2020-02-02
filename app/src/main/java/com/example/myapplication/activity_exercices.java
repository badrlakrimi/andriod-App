package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.myapplication.adapters.ExercisesRecycleAdapters;
import com.example.myapplication.fragment_user.homeFragment;

import java.util.ArrayList;
import com.example.myapplication.models.exercices_class;
import com.example.myapplication.sqlLight.DatabaseHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class activity_exercices extends AppCompatActivity  {

    String cat_name = "";
    String cat_id = "";

    String cat_geust = "none";

    //declaration recycle view
    private RecyclerView mRecyclerView;
    private ArrayList<exercices_class> mExercicesList = new ArrayList<>();
    private RecyclerView.LayoutManager layoutManager;
    private ExercisesRecycleAdapters mExercisesRecycleAdapters;

    DatabaseHelper db;

    private FirebaseAuth mAuth;
    FirebaseDatabase dbf;
    DatabaseReference dbRef;
    String lang = "en";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercices);



        //back button + title text
        Intent intent = getIntent();
        cat_name = intent.getStringExtra("cat");
        cat_id = intent.getStringExtra("id");

        //guest knowing
        cat_geust = intent.getStringExtra("guest");


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // condition to knew if the user is a guest or not
        mRecyclerView = findViewById(R.id.recyclerview_Exercises);
        Log.d("pedro","bb" + cat_geust);
        if(cat_geust == null ) {

            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setTitle(cat_name);
            }
            //insertion data exercice par categorie
            insertData(cat_id);

        }
        else{
            getSupportActionBar().setTitle("Exercises");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            //insertion all data exercice
            insertDataGeust();

        }
        initRecycleView();
    }

    private void insertData(String id){
        db = new DatabaseHelper(this);


        Cursor cursor = db.Dataexercises(id);
                if(cursor.getCount() == 0)
                {
                    Toast.makeText(getApplicationContext(), "no data", Toast.LENGTH_SHORT).show();
                }
                else{
                    while (cursor.moveToNext()){

                        mExercicesList.add(new exercices_class(cursor.getString(0), cursor.getString(2) + " Sets", cursor.getString(1),cursor.getString(3)));
                        Log.d("badrzwinf",cursor.getString(3));
                    }
                }
    }

    private void insertDataGeust(){
        db = new DatabaseHelper(this);
        Cursor cursor = db.DataexercisesGeust();
            while (cursor.moveToNext()){

                mExercicesList.add(new exercices_class(cursor.getString(0), cursor.getString(2) + " Sets", cursor.getString(1),cursor.getString(3)));
                Log.d("badrzwinf",cursor.getString(3));
            }


    }


    private void initRecycleView(){



        mRecyclerView = findViewById(R.id.recyclerview_Exercises);
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mExercisesRecycleAdapters = new ExercisesRecycleAdapters(mExercicesList);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mExercisesRecycleAdapters);

        mExercisesRecycleAdapters.setOnItemClickListener(new ExercisesRecycleAdapters.OnItemClickListener() {
            @Override
            public void onShowClick(int position) {

                //exampleItems.remove(position);

                Intent intent = new Intent(activity_exercices.this,execises_desc.class);
                String idExercises = mExercicesList.get(position).getId_exercises();
                String nomExercises = mExercicesList.get(position).getNom_exercises();
                intent.putExtra("idExercises",idExercises);
                intent.putExtra("nomExercises",nomExercises);

                startActivity(intent);


            }
        });
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



    public void instantiationFirebase()
    {
        dbf = FirebaseDatabase.getInstance();
        dbRef = dbf.getReference();
        mAuth = FirebaseAuth.getInstance();
    }
    public void retriveData()
    {

    }

}
