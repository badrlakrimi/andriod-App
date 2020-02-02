package com.example.myapplication.profil_modals;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.Upload;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import static android.app.Activity.RESULT_OK;

public class uploadimage extends AppCompatDialogFragment {

    ImageView upload_image;

    public Uri imguri;

    private FirebaseAuth mAuth;
    FirebaseDatabase db;

    StorageReference mStorage;
    DatabaseReference mdatabase;
    boolean var = false;

//db.getReference("Images").child("users").child(mAuth.getCurrentUser().getUid());

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.profile_upload_layout,null);


        instantiationFirebase();
        retriveData();

        mStorage = FirebaseStorage.getInstance().getReference("Images");

        upload_image =  view.findViewById(R.id.image_upload);

        upload_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImfFunction();
            }
        });


        builder.setView(view)
                .setPositiveButton("Upload", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(var == true) {
                            uploadImgFunction();
                            Toast.makeText(getContext(), "image uploaded ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        return builder.create();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode ==RESULT_OK && data!=null)
        {
            imguri = data.getData();
            upload_image.setImageURI(imguri);
        }

    }

    public void chooseImfFunction(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
        var = true;
    }

    private String getExtension(Uri uri){

        ContentResolver cr = getActivity().getContentResolver();
        MimeTypeMap map = MimeTypeMap.getSingleton();
        return map.getExtensionFromMimeType(cr.getType(uri));
    }

    public void uploadImgFunction()
    {

        final StorageReference ref = mStorage.child(System.currentTimeMillis()+"."+getExtension(imguri));

          ref.putFile(imguri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()){
                    throw task.getException();
                }
                return ref.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()){
                    Uri downUri = task.getResult();
                    mdatabase.child("users").child(mAuth.getCurrentUser().getUid()).child("img")
                                     .setValue(task.getResult().toString());
                }
            }
        });
    }
    public void instantiationFirebase()
    {
        db = FirebaseDatabase.getInstance();
        mdatabase = db.getReference();
        mAuth = FirebaseAuth.getInstance();
    }
    public void retriveData()
    {
        mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String db_level  = dataSnapshot.child("users").child(mAuth.getCurrentUser().getUid()).child("img").getValue().toString();
                Picasso.get().load(db_level).into(upload_image);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
