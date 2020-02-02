package com.example.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.myapplication.profil_modals.age;
import com.example.myapplication.profil_modals.height;
import com.example.myapplication.profil_modals.levels;
import com.example.myapplication.profil_modals.weight;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class healtdata_settings extends AppCompatActivity {


    private LinearLayout age_l,height_l,weight_l,levelS_l;
    private TextView lbl_level,lbl_weight,lbl_meters,lbl_cm,lbl_age;

    private FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healtdata_settings);

        declaration();

        instantiationFirebase();
        retriveData();



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("settings");


        ageFunction();
        weightFuntion();
        heightFunction();
        levelsFunction();






    }

    public void ageFunction()
    {

        age_l = findViewById(R.id.age_l);
        age_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                age classeIns = new age();
                classeIns.show(getSupportFragmentManager(), "Rating Modal");
            }
        });
    }

    public void weightFuntion()
    {

        weight_l = findViewById(R.id.weight_l);
        weight_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weight classeIns = new weight();
                classeIns.show(getSupportFragmentManager(), "Rating Modal");
            }
        });
    }
    public void heightFunction()
    {

        height_l = findViewById(R.id.height_l);
        height_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                height classeIns = new height();
                classeIns.show(getSupportFragmentManager(), "Rating Modal");
            }
        });
    }
    public void levelsFunction()
    {

       levelS_l = findViewById(R.id.levelS_l);
        levelS_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                levels classeIns = new levels();
                classeIns.show(getSupportFragmentManager(), "Rating Modal");
            }
        });
    }

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

    public void declaration()
    {
        lbl_age = findViewById(R.id.lbl_age);
        lbl_level = findViewById(R.id.lbl_level);
        lbl_cm = findViewById(R.id.lbl_cm);
        lbl_meters = findViewById(R.id.lbl_meters);
        lbl_weight = findViewById(R.id.lbl_weight);
    }

    public void instantiationFirebase()
    {
        db = FirebaseDatabase.getInstance();
        dbRef = db.getReference();
        mAuth = FirebaseAuth.getInstance();
    }

    public void retriveData()
    {
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                lbl_age.setText(dataSnapshot.child("users").child(mAuth.getCurrentUser().getUid()).child("age").getValue().toString());
                lbl_cm.setText(dataSnapshot.child("users").child(mAuth.getCurrentUser().getUid()).child("cm").getValue().toString());
                lbl_level.setText( dataSnapshot.child("users").child(mAuth.getCurrentUser().getUid()).child("level").getValue().toString());
                lbl_meters.setText( dataSnapshot.child("users").child(mAuth.getCurrentUser().getUid()).child("meters").getValue().toString());
                lbl_weight.setText( dataSnapshot.child("users").child(mAuth.getCurrentUser().getUid()).child("weight").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
