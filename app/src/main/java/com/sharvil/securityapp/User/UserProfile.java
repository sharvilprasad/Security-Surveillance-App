package com.sharvil.securityapp.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sharvil.securityapp.Common.SignUp;
import com.sharvil.securityapp.R;

public class UserProfile extends AppCompatActivity {

    String uid;
    ImageView btnback;
    TextInputLayout Name,Email,Buildingname,Flatno,Phoneno;

    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_user_profile);

        //hooks
        Intent intent= getIntent();
        uid = intent.getStringExtra("uid");
        Name = findViewById(R.id.profName);
        Email = findViewById(R.id.profEmail);
        Buildingname = findViewById(R.id.profBuildingname);
        Flatno = findViewById(R.id.profFlatno);
        Phoneno = findViewById(R.id.profPhoneno);
        btnback = findViewById(R.id.back_profile1);
        
        //show all data
        showalluserdata();

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intback = new Intent(UserProfile.this, UserDashboard.class);
                intback.putExtra("uid",uid);
                startActivity(intback);
            }
        });
    }

    private void showalluserdata() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUser = reference.orderByChild("uid").equalTo(uid);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){

                    String nameFromDB = snapshot.child(uid).child("name").getValue(String.class);
                    String emailFromDB = snapshot.child(uid).child("email").getValue(String.class);
                    String phonenoFromDB = snapshot.child(uid).child("phoneno").getValue(String.class);
                    String buildingnameFromDB = snapshot.child(uid).child("buildingname").getValue(String.class);
                    String flatnoFromDB = snapshot.child(uid).child("flatno").getValue(String.class);
                    /*Intent intent = getIntent();
                    String user_name = intent.getStringExtra("name");
                    String user_email = intent.getStringExtra("email");
                    String user_buildingname = intent.getStringExtra("buildingname");
                    String user_flatno = intent.getStringExtra("flatno");
                    String user_phoneno = intent.getStringExtra("phoneno");*/

                    Name.getEditText().setText(nameFromDB);
                    Email.getEditText().setText(emailFromDB);
                    Buildingname.getEditText().setText(buildingnameFromDB);
                    Flatno.getEditText().setText(flatnoFromDB);
                    Phoneno.getEditText().setText(phonenoFromDB);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}