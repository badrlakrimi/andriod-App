package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.support.design.widget.TabLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.adapters.exercises_images_adapter;
import com.example.myapplication.models.exercises_images_class;
import com.example.myapplication.models.exercices_desc;
import com.example.myapplication.sqlLight.DatabaseHelper;

import java.util.ArrayList;


public class execises_desc extends AppCompatActivity {

    String exercise_id = "";
    String exercise_nom = "";

    TextView lbl_series,lbl_textDesc;

    DatabaseHelper db;


    //liste description exercises
    private ArrayList<exercices_desc> listedesc;

    //liste images slider
    private ArrayList<exercises_images_class> listeImages;
    private ViewPager slidePager;
    private TabLayout indicator;

    private FloatingActionButton btn_video;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_execises_desc);

        //back button + title text
        Intent intent = getIntent();
        exercise_id = intent.getStringExtra("idExercises");
        exercise_nom = intent.getStringExtra("nomExercises");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(exercise_nom);
        }

        // insertion des image
        slidePager = findViewById(R.id.slider_pager);
        indicator = findViewById(R.id.indicator);

        insertDataImage(exercise_id);
        exercises_images_adapter adapter = new exercises_images_adapter(this, listeImages);
        slidePager.setAdapter(adapter);
        indicator.setupWithViewPager(slidePager,true);



        //insertion
        insertDataDescription();
    }


    public void insertDataImage(String exercise_id) {
        listeImages = new ArrayList<>();

        db = new DatabaseHelper(this);
        Cursor cursor = db.DataExercices_images(exercise_id);
        while (cursor.moveToNext()){
                listeImages.add(new exercises_images_class(cursor.getString(0),cursor.getString(1)));
        }
    }

    public void insertDataDescription() {
        listedesc = new ArrayList<exercices_desc>();

        db = new DatabaseHelper(this);
        Cursor cursor = db.DataExercices_desc(exercise_id);
        while (cursor.moveToNext()){
            listedesc.add(new exercices_desc(cursor.getString(1),"https://www.youtube.com/watch?v=bQKIczEj8m4",cursor.getString(0)));
            break;
        }




        lbl_series = findViewById(R.id.lbl_sets);
        lbl_textDesc = findViewById(R.id.lbl_text);
        lbl_series.setText(listedesc.get(0).getSeries()+" Sets");
        lbl_textDesc.setText(listedesc.get(0).gettextDesc());

        //link video
        btn_video = findViewById(R.id.btn_video);
        btn_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = listedesc.get(0).getvideo();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setPackage("com.google.android.youtube");
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
}
