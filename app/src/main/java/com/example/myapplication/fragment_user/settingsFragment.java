package com.example.myapplication.fragment_user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.map;
import com.example.myapplication.profil_modals.feedBack;
import com.example.myapplication.profil_modals.language;
import com.example.myapplication.profil_modals.rating;
import com.example.myapplication.profil_modals.logout;
import com.example.myapplication.healtdata_settings;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class settingsFragment extends Fragment  {

    private LinearLayout referFriend_l,feedBack_l,language_l,rating_l,logout_l,healthData_l,map_l;
    private ImageView profile_image;
    private TextView lbl_language;


    private FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference dbRef;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v  = inflater.inflate(R.layout.fragment_settings,container,false);

        profile_image =  v.findViewById(R.id.profile_image);
        lbl_language =  v.findViewById(R.id.lbl_language);


        instantiationFirebase();
        retriveData();


        healthDataFunction(v);
        logoutFunction(v);
        ratingFunction(v);
        feedBackFunction(v);
        refearFriendFunction(v);
        languageFunction(v);

        SettingsFunction(v);

        return v;
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
                lbl_language.setText(dataSnapshot.child("users").child(mAuth.getCurrentUser().getUid()).child("language").getValue().toString());

                String img_profile  = dataSnapshot.child("users").child(mAuth.getCurrentUser().getUid()).child("img").getValue().toString();
                if(!img_profile.equals("null"))
                    Picasso.get().load(img_profile).into(profile_image);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void healthDataFunction(View v)
    {
        healthData_l = v.findViewById(R.id.healthData_l);
        healthData_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), healtdata_settings.class);
                startActivity(intent);
            }
        });
    }

    public void logoutFunction(View v)
    {
        logout_l = v.findViewById(R.id.logout_l);
        logout_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout classeIns = new logout();
                classeIns.show(getFragmentManager(), "Rating Modal");
            }
        });
    }
    public void ratingFunction(View v){
        rating_l = v.findViewById(R.id.rating_l);
        rating_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rating classeIns = new rating();
                classeIns.show(getFragmentManager(), "Rating Modal");
            }
        });

    }

    public void languageFunction(View v){
        language_l = v.findViewById(R.id.language_l);
        language_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                language classeIns = new language();
                classeIns.show(getFragmentManager(), "Language Modal");
            }
        });

    }

    public void feedBackFunction(View v){
        feedBack_l = v.findViewById(R.id.feedBack_l);
        feedBack_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedBack classeIns = new feedBack();
                classeIns.show(getFragmentManager(), "Feed Back Modal");
            }
        });
    }

    public void SettingsFunction(View v){
        map_l = v.findViewById(R.id.Map_l);
        map_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), map.class);
                startActivity(intent);
            }
        });
    }

    public void refearFriendFunction(View v){
        referFriend_l = v.findViewById(R.id.referFriend_l);
        referFriend_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String shareBody = "https://www.google.com";
                String shareSub = "Health Exercise Application";
                intent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
                intent.putExtra(Intent.EXTRA_TEXT,shareBody);
                startActivity(Intent.createChooser(intent,"Healtho Appication"));
            }
        });
    }


}
