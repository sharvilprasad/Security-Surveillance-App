package com.sharvil.securityapp.Security;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sharvil.securityapp.Common.SignUp;
import com.sharvil.securityapp.R;
import com.sharvil.securityapp.User.AddVisitors;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RequestForm extends AppCompatActivity {
    ImageView btnback;
    TextInputLayout Rname , Rphoneno, Rreason, Rform, Rflatno, Rbuildingname;
    Button reqbutton;

    private AutoCompleteTextView Downtext;
    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_form);

        //hooks
        btnback = findViewById(R.id.back_form1);
        Downtext = findViewById(R.id.reqdropdownbuilding);
        Rname = findViewById(R.id.reqname);
        Rphoneno = findViewById(R.id.reqphoneno);
        Rreason = findViewById(R.id.reqreason);
        Rform = findViewById(R.id.reqform);
        Rflatno = findViewById(R.id.reqflatno);
        Rbuildingname = findViewById(R.id.reqBuilding_name);
        reqbutton = findViewById(R.id.reqbutton);





        String[] buildingnames = new String[]{"Nilgiri A wing ", "Nilgiri B wing","Nilgiri C wing"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(RequestForm.this, R.layout.dropdown_item_signup, buildingnames);
        Downtext.setAdapter(adapter);

        reqbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateName() | !validatephone() | !validatereason() | !validateform() | !validateflatno() | !validatebuildingname()){
                    return;
                }
                else{
                    String name = Rname.getEditText().getText().toString();
                    String phoneno = Rphoneno.getEditText().getText().toString();
                    String reason = Rreason.getEditText().getText().toString();
                    String form = Rform.getEditText().getText().toString();
                    String flatno = Rflatno.getEditText().getText().toString();
                    String buildingname = Rbuildingname.getEditText().getText().toString();
                    Date currentTime = Calendar.getInstance().getTime();
                    //String currentDate = DateFormat.getDateInstance().format(currentTime);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    String currentDate  = dateFormat.format(currentTime);


                    //Calendar calendar = Calendar.getInstance();
                    //String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

                    rootNode = FirebaseDatabase.getInstance();
                    reference = rootNode.getReference("Visitor");
                    RequestFormHelperClass visitor = new RequestFormHelperClass(name, phoneno, reason, form, flatno, buildingname,currentDate);
                    reference.child(buildingname).child(flatno).child(currentDate).setValue(visitor).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(RequestForm.this, "Request Successful!!",Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(RequestForm.this, "Request Unsuccessful!! Try again",Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }

            }
            private boolean validateName() {
                String val = Rname.getEditText().getText().toString();

                if(val.isEmpty()){
                    Rname.setError("Field is empty");
                    return false;
                }
                else{
                    Rname.setError(null);
                    Rname.setErrorEnabled((false));
                    return true;
                }
            }
            private boolean validatephone() {
                String val = Rphoneno.getEditText().getText().toString();

                if(val.isEmpty()){
                    Rphoneno.setError("Field is empty");
                    return false;
                }
                else{
                    Rphoneno.setError(null);
                    Rphoneno.setErrorEnabled((false));
                    return true;
                }
            }
            private boolean validatereason() {
                String val = Rreason.getEditText().getText().toString();

                if(val.isEmpty()){
                    Rreason.setError("Field is empty");
                    return false;
                }
                else{
                    Rreason.setError(null);
                    Rreason.setErrorEnabled((false));
                    return true;
                }
            }
            private boolean validateform() {
                String val = Rform.getEditText().getText().toString();

                if(val.isEmpty()){
                    Rform.setError("Field is empty");
                    return false;
                }
                else{
                    Rform.setError(null);
                    Rform.setErrorEnabled((false));
                    return true;
                }
            }
            private boolean validateflatno() {
                String val = Rflatno.getEditText().getText().toString();

                if(val.isEmpty()){
                    Rflatno.setError("Field is empty");
                    return false;
                }
                else{
                    Rflatno.setError(null);
                    Rflatno.setErrorEnabled((false));
                    return true;
                }
            }
            private boolean validatebuildingname() {
                String val = Rbuildingname.getEditText().getText().toString();

                if(val.isEmpty()){
                    Rbuildingname.setError("Field is empty");
                    return false;
                }
                else{
                    Rbuildingname.setError(null);
                    Rbuildingname.setErrorEnabled((false));
                    return true;
                }
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    RequestForm.super.onBackPressed();
            }
        });
    }
}