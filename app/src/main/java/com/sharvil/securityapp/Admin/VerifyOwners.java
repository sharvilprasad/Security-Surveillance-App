package com.sharvil.securityapp.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.sharvil.securityapp.R;
import com.sharvil.securityapp.User.Notice;

public class VerifyOwners extends AppCompatActivity {

    ImageView btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_owners);

        //hooks
        btnback = findViewById(R.id.back_Verify1);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerifyOwners.super.onBackPressed();
            }
        });
    }
}