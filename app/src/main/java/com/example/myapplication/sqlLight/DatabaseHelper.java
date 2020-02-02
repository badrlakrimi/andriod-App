package com.example.myapplication.sqlLight;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "bd_sport.db";
    private SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //onCreate(db);
    }

    public Cursor Dataexercises(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery( "select e.id_exercice,e.nume,f.number,p.nume from exercices e,photos p,foto f where p.id_type = e.id_exercice and e.id_exercice = f.id_exercice and e.id_type = "+id+" and lang = 'en' and p.nume like '%1.png'",null);
        return cursor;
    }

    public Cursor DataexercisesGeust(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery( "select e.id_exercice,e.nume,f.number,p.nume from exercices e,photos p,foto f where p.id_type = e.id_exercice and e.id_exercice = f.id_exercice and lang = 'en' and p.nume like '%1.png'",null);
        return cursor;
    }


    public Cursor DataExercices_images(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery( "select p.id,p.nume from exercices e,photos p where p.id_type = e.id_exercice and e.id_exercice = "+id+" and lang = 'en'",null);
        return cursor;
    }

    public Cursor DataExercices_desc(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery( "select e.text,f.number,e.video_name from exercices e,foto f where e.id_exercice = f.id_exercice and e.id_exercice =" +id,null);
        return cursor;
    }

    public Cursor DataRecette(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery( "select id,nom,image,recipe from recette",null);
        return cursor;
    }


}
