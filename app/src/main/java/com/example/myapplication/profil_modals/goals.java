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

public class goals extends AppCompatDialogFragment {

    RadioGroup radioGroup;
    RadioButton radio1;
    RadioButton radio2;
    RadioButton radio3;
    RadioButton radioButton;

    private String defaultLevel = "Fat Loss";

    private FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference dbRef;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.profile_golas_layout,null);

        instantiationFirebase();
        retriveData();

        radioFunction(view);

        builder.setView(view)
        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = view.findViewById(radioId);

                dbRef.child("users").child(mAuth.getCurrentUser().getUid()).child("goal").setValue(defaultLevel);
                Toast.makeText(getContext(), "Goal Updated: " + defaultLevel, Toast.LENGTH_SHORT).show();
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

                String db_level  = dataSnapshot.child("users").child(mAuth.getCurrentUser().getUid()).child("goal").getValue().toString();

                if(db_level.equals("Fat Loss"))
                    radio1.setChecked(true);
                if(db_level.equals("Weight Gain"))
                    radio2.setChecked(true);
                if(db_level.equals("Muscle Gain "))
                    radio3.setChecked(true);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
