package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.sqlLight.DatabaseHelper;

public class firstDisplay extends AppCompatActivity {

    Button userAcount;
    Button guestAcount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_display);

        //DatabaseHelper mydb = new DatabaseHelper(this);

        userAcount = findViewById(R.id.btnUser);
        userAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(firstDisplay.this,MainActivity.class);
                startActivity(intent);
            }
        });

        guestAcount = findViewById(R.id.btnGeust);
        guestAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(firstDisplay.this,activity_exercices.class);
                intent.putExtra("guest","guest");
                startActivity(intent);
            }
        });
    }
}
