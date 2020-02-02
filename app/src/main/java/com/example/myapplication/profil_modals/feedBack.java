package com.example.myapplication.profil_modals;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;

public class feedBack extends AppCompatDialogFragment {

    EditText txt_title_feed;
    EditText txt_des_feed;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.settings_feedback_layout,null);

        txt_title_feed = view.findViewById(R.id.txt_title_feed);
        txt_des_feed = view.findViewById(R.id.txt_description_feed);

        builder.setView(view)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getContext(), "Thank you for the feed back", Toast.LENGTH_SHORT).show();
                    }
                });
        return builder.create();
    }
}
