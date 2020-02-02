package com.example.myapplication.fragment_user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.activity_exercices;

public class homeFragment extends Fragment {



    private ImageView btn_abs,btn_back,btn_chest,btn_shoulders,btn_triceps,btn_biceps,btn_legs,btn_forearms;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_home,container,false);

        fun_abs(v);
        fun_back(v);
        fun_chest(v);
        fun_shoulders(v);
        fun_triceps(v);
        fun_biceps(v);
        fun_legs(v);
        fun_forearms(v);

        return  v;

    }
    public void fun_abs(View v){
        btn_abs = v.findViewById(R.id.cat_aps);
        btn_abs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), activity_exercices.class);
                intent.putExtra("cat","Abdominal ");
                intent.putExtra("id","1");
                startActivity(intent);
            }
        });
    }
    public void fun_back(View v){
        btn_back = v.findViewById(R.id.cat_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), activity_exercices.class);
                intent.putExtra("cat","Back");
                intent.putExtra("id","2");
                startActivity(intent);
            }
        });
    }
    public void fun_chest(View v){
        btn_chest = v.findViewById(R.id.cat_chest);
        btn_chest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), activity_exercices.class);
                intent.putExtra("cat","Chest");
                intent.putExtra("id","5");
                startActivity(intent);
            }
        });
    }
    public void fun_shoulders(View v){
        btn_shoulders = v.findViewById(R.id.cat_shoulders);
        btn_shoulders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), activity_exercices.class);
                intent.putExtra("cat","Shoulders");
                intent.putExtra("id","8");
                startActivity(intent);
            }
        });
    }
    public void fun_triceps(View v){
        btn_triceps = v.findViewById(R.id.cat_triceps);
        btn_triceps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), activity_exercices.class);
                intent.putExtra("cat","Triceps");
                intent.putExtra("id","9");
                startActivity(intent);
            }
        });
    }
    public void fun_biceps(View v){
        btn_biceps = v.findViewById(R.id.cat_biceps);
        btn_biceps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), activity_exercices.class);
                intent.putExtra("cat","Biceps");
                intent.putExtra("id","3");
                startActivity(intent);
            }
        });
    }
    public void fun_legs(View v){
        btn_legs = v.findViewById(R.id.cat_legs);
        btn_legs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), activity_exercices.class);
                intent.putExtra("cat","Legs");
                intent.putExtra("id","7");
                startActivity(intent);
            }
        });
    }
    public void fun_forearms(View v){
        btn_forearms = v.findViewById(R.id.cat_forearms);
        btn_forearms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), activity_exercices.class);
                intent.putExtra("cat","Fore Arms");
                intent.putExtra("id","4");
                startActivity(intent);
            }
        });
    }


}
