package com.sharvil.securityapp.User;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.sharvil.securityapp.R;

public class LocalHelp extends AppCompatActivity {

    ImageView btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_help);

        //hooks
        btnback = findViewById(R.id.back_local1);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalHelp.super.onBackPressed();
            }
        });
    }
}