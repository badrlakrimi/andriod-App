package com.example.myapplication.fragment_user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.profil_modals.goals;
import com.example.myapplication.profil_modals.levels;
import com.example.myapplication.profil_modals.uploadimage;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class profileFragment extends Fragment {

    private LinearLayout fitnessDevice_l,plans_l,level_l,goals_l,referFriend_l;
    private ImageView uploadImage_l;
    private TextView lbl_name,lbl_niveau,lbl_email,lbl_ville,lbl_goalL,lbl_niveauL;



    private FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference dbRef;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v  = inflater.inflate(R.layout.fragment_profile,container,false);


        declaration(v);


        instantiationFirebase();
        retriveData();

        uploadImage(v);
        fitnessDeviceFunction(v);
        plansFunction(v);
        levelFunction(v);
        goalsFunction(v);
        referFriendFunction(v);


        return v;

    }

    public void uploadImage(View v){
        uploadImage_l = v.findViewById(R.id.profile_image_l);
        uploadImage_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadimage classeIns = new uploadimage();
                classeIns.show(getFragmentManager(), "example dialog");
            }
        });
    }

    public void levelFunction(View v){
        level_l = v.findViewById(R.id.level_l);
        level_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                levels classeIns = new levels();
                classeIns.show(getFragmentManager(), "example dialog");
            }
        });
    }
    public void goalsFunction(View v){
        goals_l = v.findViewById(R.id.goals_l);
        goals_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goals classeIns = new goals();
                classeIns.show(getFragmentManager(), "example dialog");
            }
        });
    }
    public void referFriendFunction(View v){
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
    public void plansFunction(View v){
        plans_l = v.findViewById(R.id.plans_l);
        plans_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "this feature isn't available for now", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void fitnessDeviceFunction(View v){

        fitnessDevice_l = v.findViewById(R.id.fitnessDevice_l);
        fitnessDevice_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "this feature isn't available for now", Toast.LENGTH_LONG).show();
            }
        });
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
                lbl_name.setText(dataSnapshot.child("users").child(mAuth.getCurrentUser().getUid()).child("name").getValue().toString());
                lbl_goalL.setText(dataSnapshot.child("users").child(mAuth.getCurrentUser().getUid()).child("goal").getValue().toString());
                lbl_ville.setText( dataSnapshot.child("users").child(mAuth.getCurrentUser().getUid()).child("city").getValue().toString());
                lbl_email.setText(mAuth.getCurrentUser().getEmail());
                String level = dataSnapshot.child("users").child(mAuth.getCurrentUser().getUid()).child("level").getValue().toString();
                lbl_niveauL.setText(level);
                lbl_niveau.setText(level);

                String img_profile  = dataSnapshot.child("users").child(mAuth.getCurrentUser().getUid()).child("img").getValue().toString();
                if(!img_profile.equals("null"))
                Picasso.get().load(img_profile).into(uploadImage_l);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void declaration(View v){
        uploadImage_l =  v.findViewById(R.id.image_upload);
        lbl_name =  v.findViewById(R.id.lbl_name);
        lbl_niveau =  v.findViewById(R.id.lbl_niveau);
        lbl_email =  v.findViewById(R.id.lbl_email);
        lbl_ville =  v.findViewById(R.id.lbl_ville);
        lbl_goalL =  v.findViewById(R.id.lbl_goalL);
        lbl_niveauL =  v.findViewById(R.id.lbl_niveauL);

    }


}
