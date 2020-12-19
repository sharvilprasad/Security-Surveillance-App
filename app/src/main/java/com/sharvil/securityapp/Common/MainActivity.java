package com.sharvil.securityapp.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.sharvil.securityapp.R;

public class MainActivity extends AppCompatActivity {
    Button btnLogIn, btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        btnLogIn = findViewById(R.id.button);
        btnSignUp = findViewById(R.id.button2);

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intLogIn = new Intent(MainActivity.this, Login.class);
                startActivity(intLogIn);
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intSignUp = new Intent(MainActivity.this, SignUp.class);
                startActivity(intSignUp);
            }
        });
    }

}