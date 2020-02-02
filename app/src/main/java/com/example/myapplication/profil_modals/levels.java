package com.example.myapplication.profil_modals;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class levels extends AppCompatDialogFragment {

    RadioGroup radioGroup;
    RadioButton radio1;
    RadioButton radio2;
    RadioButton radio3;
    RadioButton radioButton;

    private String defaultLevel = "Beginner";

    private FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference dbRef;



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.profile_level_layout,null);

        instantiationFirebase();
        retriveData();

        radioFunction(view);

        builder.setView(view)

          .setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = view.findViewById(radioId);
                dbRef.child("users").child(mAuth.getCurrentUser().getUid()).child("level").setValue(defaultLevel);
                Toast.makeText(getContext(), "Level Updated: " + defaultLevel, Toast.LENGTH_SHORT).show();
               }
           })
          .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        return builder.create();
    }



    public void radioFunction(View view)
    {
        radioGroup = view.findViewById(R.id.radioGroup);
        radio1 = view.findViewById(R.id.radio_one);
        radio2 = view.findViewById(R.id.radio_two);
        radio3 = view.findViewById(R.id.radio_three);

        radio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = v.findViewById(radioId);
                defaultLevel = radioButton.getText().toString();
            }
        });
        radio2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = v.findViewById(radioId);
                defaultLevel = radioButton.getText().toString();

            }
        });
        radio3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = v.findViewById(radioId);
                defaultLevel = radioButton.getText().toString();

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


                String db_level  = dataSnapshot.child("users").child(mAuth.getCurrentUser().getUid()).child("level").getValue().toString();
                Log.d("bb1", db_level);
                if(db_level.equals("Beginner")) {

                    radio1.setChecked(true);
                }
                if(db_level.equals("Intermediate")){

                    radio2.setChecked(true);
                }

                if(db_level.equals("Advance")){
                    radio3.setChecked(true);
                }
                Log.d("bb2", db_level);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
