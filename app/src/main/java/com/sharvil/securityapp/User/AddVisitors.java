package com.sharvil.securityapp.User;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.sharvil.securityapp.R;

public class AddVisitors extends AppCompatActivity {
    ImageView btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_visitors);

        //hooks
        btnback = findViewById(R.id.back_addvis1);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddVisitors.super.onBackPressed();
            }
        });
    }
}