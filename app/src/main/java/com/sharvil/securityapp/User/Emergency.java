package com.sharvil.securityapp.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.sharvil.securityapp.R;

public class Emergency extends AppCompatActivity {

    Button ambulancebtn, policebtn, firebtn;
    ImageView btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        //hooks
        btnback = findViewById(R.id.back_emergency1);
        ambulancebtn =findViewById(R.id.ambulancebtn);
        policebtn = findViewById(R.id.policebtn);
        firebtn = findViewById(R.id.firebtn);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Emergency.super.onBackPressed();
            }
        });

        ambulancebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intamb = new Intent(Emergency.this, Ambulance.class);
                startActivity(intamb);
            }
        });
        policebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intpol = new Intent(Emergency.this, Police.class);
                startActivity(intpol);
            }
        });
        firebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intfir = new Intent(Emergency.this, Fire.class);
                startActivity(intfir);
            }
        });
    }
}