package com.sharvil.securityapp.User;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.sharvil.securityapp.R;

public class History extends AppCompatActivity {

    ImageView btnback;


    private Boolean table_flg = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        //hooks
        btnback = findViewById(R.id.back_history1);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                History.super.onBackPressed();
            }
        });

    }
}