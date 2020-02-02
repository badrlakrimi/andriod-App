package com.example.myapplication.profil_modals;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class weight extends AppCompatDialogFragment {
    TextView txt_msg;
    NumberPicker picker;


    private FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference dbRef;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.settings_healt_weight_layout,null);

        instantiationFirebase();
        retriveData();

        txt_msg = (TextView) view.findViewById(R.id.txt_title_weight);
        picker = view.findViewById(R.id.numberPickerAge);
        picker.setMinValue(0);
        picker.setMaxValue(200);

        builder.setView(view)
        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        })
        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String defaultWeight = ""+picker.getValue();
                dbRef.child("users").child(mAuth.getCurrentUser().getUid()).child("weight").setValue(defaultWeight);
                Toast.makeText(getContext(), "Weight Updated: " + defaultWeight + " Kg", Toast.LENGTH_SHORT).show();
            }
        });

        return builder.create();
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

                String db_weight  = dataSnapshot.child("users").child(mAuth.getCurrentUser().getUid()).child("weight").getValue().toString();
                picker.setValue(Integer.parseInt(db_weight));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
