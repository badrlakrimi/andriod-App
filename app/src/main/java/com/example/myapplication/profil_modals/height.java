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

public class height extends AppCompatDialogFragment {
    TextView txt_msg;
    NumberPicker pickerM;
    NumberPicker pickerCm;

    private FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference dbRef;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.settings_healt_height_layout,null);

        instantiationFirebase();
        retriveData();

       txt_msg = (TextView) view.findViewById(R.id.txt_title_height);

        pickerM = view.findViewById(R.id.numberPickerAgeM);
        pickerM.setMinValue(0);
        pickerM.setMaxValue(4);

        pickerCm = view.findViewById(R.id.numberPickerAgeCm);
        pickerCm.setMinValue(0);
        pickerCm.setMaxValue(99);

        builder.setView(view)
        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        })
        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String defaultMeters = ""+pickerM.getValue();
                String defaultCm = ""+pickerCm.getValue();

                dbRef.child("users").child(mAuth.getCurrentUser().getUid()).child("meters").setValue(defaultMeters);
                dbRef.child("users").child(mAuth.getCurrentUser().getUid()).child("cm").setValue(defaultCm);
                Toast.makeText(getContext(), "Weight Updated: " + defaultMeters + " M " + defaultCm + " Cm" , Toast.LENGTH_SHORT).show();

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

                String db_meters = dataSnapshot.child("users").child(mAuth.getCurrentUser().getUid()).child("meters").getValue().toString();
                pickerM.setValue(Integer.parseInt(db_meters));

                String db_cm  = dataSnapshot.child("users").child(mAuth.getCurrentUser().getUid()).child("cm").getValue().toString();
                pickerCm.setValue(Integer.parseInt(db_cm));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
