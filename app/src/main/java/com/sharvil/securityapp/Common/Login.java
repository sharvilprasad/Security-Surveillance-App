package com.sharvil.securityapp.Common;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sharvil.securityapp.R;
import com.sharvil.securityapp.User.UserDashboard;
import com.sharvil.securityapp.User.UserProfile;

public class Login extends AppCompatActivity {
    Button btnLogIn;
    TextView  tvforgotpass, tvsignup;
    TextInputLayout Email, Password;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        btnLogIn = findViewById(R.id.button3);
        Email = findViewById(R.id.Email_login);
        Password = findViewById(R.id.TextPassword);
        tvforgotpass = findViewById(R.id.Forgot_pass);
        tvsignup = findViewById(R.id.textView2);

        /*mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mAuth.getCurrentUser();
                if (mFirebaseUser != null) {
                    Toast.makeText(Login.this, "you are logged in", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Login.this, UserDashboard.class);
                    startActivity(i);
                } else {
                    Toast.makeText(Login.this, "Please Login", Toast.LENGTH_SHORT).show();
                }
            }
        };*/





        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( !validateEmail() | !validatePassword()){
                    return;
                }
                else {
                    isUser();

                }


            }

            private void isUser(){
                String email = Email.getEditText().getText().toString().trim();
                String password = Password.getEditText().getText().toString().trim();

                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                            Intent intlogin = new Intent(Login.this, UserProfile.class);
                            intlogin.putExtra("uid",uid);
                            startActivity(intlogin);

                        }
                        else {
                            Toast.makeText(Login.this, "failed to login",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }

            private boolean validateEmail() {
                String val = Email.getEditText().getText().toString();


                if(val.isEmpty()){
                    Email.setError("Field is empty");
                    return false;
                }
                else{
                    Email.setError(null);
                    Email.setErrorEnabled((false));
                    return true;
                }
            }
            private boolean validatePassword() {
                String val = Password.getEditText().getText().toString();


                if(val.isEmpty()){
                    Password.setError("Field is empty");
                    return false;
                }
                else{
                    Password.setError(null);
                    Password.setErrorEnabled((false));
                    return true;
                }
            }
        });


        
        tvsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intSignUp = new Intent(Login.this, SignUp.class);
                startActivity(intSignUp);
            }
        });

        tvforgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText resetMail = new EditText(v.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password?");
                passwordResetDialog.setMessage("Enter your email to recieve Reset Link");
                passwordResetDialog.setView(resetMail);
                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //extract email
                        String mail = resetMail.getText().toString();
                        mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Login.this ,"Reset link is sent to your email", Toast. LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Login.this ,"Error! Reset link is not sent to your email", Toast. LENGTH_LONG).show();
                            }
                        });
                    }
                });
                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //close dialog
                    }
                });

            }
        });


    }
    /*@Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        mAuth.addAuthStateListener(mAuthStateListener);

    }*/

}

