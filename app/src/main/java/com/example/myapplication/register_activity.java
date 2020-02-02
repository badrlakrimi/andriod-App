package com.example.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register_activity extends AppCompatActivity {

    private TextView txtName ,txtCity , txtEmail , txtPassword,txtconfirmPass;
    private Button btnRegister;
    private FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        txtName = findViewById(R.id.txtName);
        txtCity = findViewById(R.id.txtCity);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        txtconfirmPass = findViewById(R.id.txtConfirmPss);
        btnRegister = findViewById(R.id.btnRegister);

        db = FirebaseDatabase.getInstance();
        dbRef = db.getReference();
        mAuth = FirebaseAuth.getInstance();

       btnRegister.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               final String email = txtEmail.getText().toString();
               final String password = txtPassword.getText().toString();
               final String confirmPass = txtconfirmPass.getText().toString();
               final String name = txtName.getText().toString();
               final String city = txtCity.getText().toString();

               if(!email.isEmpty() && !password.isEmpty() && !confirmPass.isEmpty() && !name.isEmpty() && !city.isEmpty()) {
                   if (password.equals(confirmPass)) {
                       mAuth.createUserWithEmailAndPassword(email, password)
                               .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                   @Override
                                   public void onComplete(@NonNull Task<AuthResult> task) {
                                       if (task.isSuccessful()) {
                                           // Sign in success, update UI with the signed-in user's information
                                           dbRef.child("users").child(mAuth.getCurrentUser().getUid()).child("name").setValue(name);
                                           dbRef.child("users").child(mAuth.getCurrentUser().getUid()).child("city").setValue(city);
                                           dbRef.child("users").child(mAuth.getCurrentUser().getUid()).child("age").setValue("0");
                                           dbRef.child("users").child(mAuth.getCurrentUser().getUid()).child("cm").setValue("0");
                                           dbRef.child("users").child(mAuth.getCurrentUser().getUid()).child("meters").setValue("0");
                                           dbRef.child("users").child(mAuth.getCurrentUser().getUid()).child("weight").setValue("0");
                                           dbRef.child("users").child(mAuth.getCurrentUser().getUid()).child("goal").setValue("Fat Loss");
                                           dbRef.child("users").child(mAuth.getCurrentUser().getUid()).child("language").setValue("English");
                                           dbRef.child("users").child(mAuth.getCurrentUser().getUid()).child("level").setValue("Beginner");
                                           dbRef.child("users").child(mAuth.getCurrentUser().getUid()).child("img").setValue("https://firebasestorage.googleapis.com/v0/b/myapplication-66a12.appspot.com/o/Images%2Fprofile.png?alt=media&token=b1ea5d49-3e48-4178-9eba-9ac4a10857a1");

                                           Intent intent = new Intent(register_activity.this, MainActivity.class);
                                           startActivity(intent);
                                           Toast.makeText(register_activity.this, "Add Completed", Toast.LENGTH_SHORT).show();

                                       }

                                       // ...
                                   }
                               }).addOnFailureListener(new OnFailureListener() {
                           @Override
                           public void onFailure(@NonNull Exception e) {
                               Toast.makeText(register_activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                           }
                       });
                   } else {
                       Toast.makeText(register_activity.this, "cheek your password", Toast.LENGTH_SHORT).show();
                   }
               }
               else {
                   Toast.makeText(register_activity.this, "Field Empty", Toast.LENGTH_SHORT).show();
               }





           }
       });


    }
}
