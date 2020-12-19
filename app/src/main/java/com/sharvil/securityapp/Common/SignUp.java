package com.sharvil.securityapp.Common;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sharvil.securityapp.R;
import com.sharvil.securityapp.User.Fire;
import com.sharvil.securityapp.User.UserDashboard;

public class SignUp extends AppCompatActivity {
    TextInputLayout Name, Email, Phoneno, Buildingname, Flatno, Password;
    Button btnsignup;
    TextView tvlogin;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private TextInputLayout textInputLayout;
    private AutoCompleteTextView Downtext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //Hooks
        mAuth = FirebaseAuth.getInstance();
        btnsignup = findViewById(R.id.button4);
        tvlogin = findViewById(R.id.textView3);
        textInputLayout = findViewById(R.id.Building_name);
        Downtext = findViewById(R.id.dropdownbuilding);
        Name = findViewById(R.id.Name);
        Email = findViewById(R.id.EmailAddress);
        Phoneno = findViewById(R.id.PhoneNo);
        Buildingname = findViewById(R.id.Building_name);
        Flatno = findViewById(R.id.FlatNumber);
        Password = findViewById(R.id.Password);

        String[] buildingnames = new String[]{"Nilgiri A wing ", "Nilgiri B wing","Nilgiri C wing"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(SignUp.this, R.layout.dropdown_item_signup, buildingnames);
        Downtext.setAdapter(adapter);




        //Save data
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateName() | !validateEmail() | !validatePhoneNo() | !validateBuildingname() | !validateFlatNo() | !validatePassword()){
                    return;
                }

                else{
                    final String name = Name.getEditText().getText().toString();
                    final String email = Email.getEditText().getText().toString();
                    final String phoneno = Phoneno.getEditText().getText().toString();
                    final String buildingname = Buildingname.getEditText().getText().toString();
                    final String flatno = Flatno.getEditText().getText().toString();
                    final String password = Password.getEditText().getText().toString();


                    mAuth.createUserWithEmailAndPassword(email,password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        //rootNode = FirebaseDatabase.getInstance();
                                        //reference = rootNode.getReference("users");
                                        //UserHelperClass helperClass = new UserHelperClass(name, email, phoneno, buildingname, flatno, password);
                                        //reference.child(phoneno).setValue(helperClass);
                                        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();


                                        rootNode = FirebaseDatabase.getInstance();
                                        reference = rootNode.getReference("users");
                                        UserHelperClass  userHelperClass = new UserHelperClass(name, email, phoneno, buildingname, flatno, uid);
                                        reference.child(uid).setValue(userHelperClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()){
                                                    Toast.makeText(SignUp.this, "Sign up Successful!!",Toast.LENGTH_LONG).show();
                                                }
                                                else{
                                                    Toast.makeText(SignUp.this, "Sign up Unsuccessful!! Try again",Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });
                                    }

                                }
                            });
                }



            }
            private boolean validateName() {
                String val = Name.getEditText().getText().toString();

                if(val.isEmpty()){
                    Name.setError("Field is empty");
                    return false;
                }
                else{
                    Name.setError(null);
                    Name.setErrorEnabled((false));
                    return true;
                }
            }
            private boolean validateEmail() {
                String val = Email.getEditText().getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if(val.isEmpty()){
                    Email.setError("Field is empty");
                    return false;
                } else if (!val.matches(emailPattern)){
                    Email.setError("Invalid email address");
                    return false;
                }
                else{
                    Email.setError(null);
                    Email.setErrorEnabled((false));
                    return true;
                }
            }
            private boolean validatePhoneNo() {
                String val = Phoneno.getEditText().getText().toString();

                if(val.isEmpty()){
                    Phoneno.setError("Field is empty");
                    return false;
                }
                else{
                    Phoneno.setError(null);
                    Phoneno.setErrorEnabled((false));
                    return true;
                }
            }
            private boolean validateBuildingname() {
                String val = Buildingname.getEditText().getText().toString();

                if(val.isEmpty()){
                    Buildingname.setError("Field is empty");
                    return false;
                }
                else{
                    Buildingname.setError(null);
                    Buildingname.setErrorEnabled((false));
                    return true;
                }
            }
            private boolean validateFlatNo() {
                String val = Flatno.getEditText().getText().toString();

                if(val.isEmpty()){
                    Flatno.setError("Field is empty");
                    return false;
                }
                else{
                    Flatno.setError(null);
                    Flatno.setErrorEnabled((false));
                    return true;
                }
            }
            private boolean validatePassword() {
                String val = Password.getEditText().getText().toString();
                String passwordVal = "^" +
                        //"(?=.*[0-9])" +         //at least 1 digit
                        //"(?=.*[a-z])" +         //at least 1 lower case letter
                        //"(?=.*[A-Z])" +         //at least 1 upper case letter
                        "(?=.*[a-zA-Z])" +      //any letter
                        "(?=.*[@#$%^&+=])" +    //at least 1 special character
                        "(?=\\S+$)" +           //no white spaces
                        ".{8,}" +               //at least 8 characters
                        "$";

                if(val.isEmpty()){
                    Password.setError("Field is empty");
                    return false;
                } else if (!val.matches(passwordVal)) {
                    Password.setError("should have 8 characters and special characters");
                    return false;
                }
                else{
                    Password.setError(null);
                    Password.setErrorEnabled((false));
                    return true;
                }
            }



        });


        tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intLogin = new Intent(SignUp.this, Login.class);
                startActivity(intLogin);
            }
        });

    }


}