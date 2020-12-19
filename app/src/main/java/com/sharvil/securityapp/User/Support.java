package com.sharvil.securityapp.User;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.sharvil.securityapp.Admin.VerifyOwners;
import com.sharvil.securityapp.R;

public class Support extends AppCompatActivity {

    ImageView btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        //hooks
        btnback = findViewById(R.id.back_support1);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Support.super.onBackPressed();
            }
        });
    }
}