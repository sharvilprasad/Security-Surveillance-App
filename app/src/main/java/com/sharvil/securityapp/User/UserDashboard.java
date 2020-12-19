package com.sharvil.securityapp.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sharvil.securityapp.Admin.VerifyOwners;
import com.sharvil.securityapp.Common.MainActivity;
import com.sharvil.securityapp.HelperClasses.HomeAdapter.RequestAdapter;
import com.sharvil.securityapp.HelperClasses.HomeAdapter.RequestHelperClass;
import com.sharvil.securityapp.R;
import com.sharvil.securityapp.Security.RequestForm;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Variables
    static  final float END_SCALE =0.7f;

    String uid;
    String nameofvis,reasonofvis,dateofvis;
    RecyclerView requestRecycler;
    RecyclerView.Adapter adapter;
    ImageView menuIcon;
    ImageButton emergency, localhelp, history, notice, vehicle;
    LinearLayout contentView;
    TextView profile, otp, logout, addvis,form , home, verify,support,help;

    //Drawer menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        //hooks
        Intent intent= getIntent();
        uid = intent.getStringExtra("uid");
        requestRecycler = findViewById(R.id.request_recycle);
        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.content);
        emergency =  findViewById(R.id.Emergency);
        localhelp =  findViewById(R.id.localhelp);
        history =findViewById(R.id.history);
        notice =findViewById(R.id.notice);
        vehicle =findViewById(R.id.shop);
        profile =findViewById(R.id.profile);
        otp  = findViewById(R.id.genotp);
        logout = findViewById(R.id.logout);
        addvis = findViewById(R.id.addvisitor);
        home = findViewById(R.id.home);
        form = findViewById(R.id.form);
        verify = findViewById(R.id.Verify);
        support = findViewById(R.id.support);
        help = findViewById(R.id.Help);


        //Menu hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);




        navigationDrawer();

        requestRecycler();
    }

    //NavigationDrawer Functions
    private void navigationDrawer() {
        //Navigation Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.home);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);

            }
        });


        /*animateNavigationDrawer();
    }

    private void animateNavigationDrawer() {
        drawerLayout.setScrimColor(getResources().getColor(R.color.mainblue));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                //scale the View based on current offset
                final float diffScaledOffset = slideOffset = (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                //Translate the view accounting for the scaled width
                final float xoffset =drawerView.getWidth() * slideOffset;
                final float xoffsetDiff =contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xoffset - xoffsetDiff;
                contentView.setTranslationX(xTranslation);

            }

        });*/
    }

    @Override
    public void onBackPressed(){
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }else
            super.onBackPressed();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile:
                Intent intprof = new Intent(UserDashboard.this, UserProfile.class);
                startActivity(intprof);
                break;
        }
        switch (item.getItemId()) {
            case R.id.home:
                Intent inthome = new Intent(UserDashboard.this, UserDashboard.class);
                startActivity(inthome);
                break;
        }
        switch (item.getItemId()) {
            case R.id.genotp:
                Intent intotp = new Intent(UserDashboard.this, Otp.class);
                startActivity(intotp);
                break;
        }
        switch (item.getItemId()) {
            case R.id.logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure you want to Log Out?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intlogout = new Intent(UserDashboard.this, MainActivity.class);
                                startActivity(intlogout);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog =builder.create();
                alertDialog.show();
                break;
        }
        switch (item.getItemId()) {
            case R.id.addvisitor:
                Intent intaddvis = new Intent(UserDashboard.this, AddVisitors.class);
                startActivity(intaddvis);
                break;
        }
        /**/
        switch (item.getItemId()) {
            case R.id.form:
                Intent intform = new Intent(UserDashboard.this, RequestForm.class);
                startActivity(intform);
                break;
        }

        switch (item.getItemId()) {
        case R.id.Verify:
            Intent intveri = new Intent(UserDashboard.this, VerifyOwners.class);
            startActivity(intveri);
            break;
        }
        switch (item.getItemId()) {
            case R.id.support:
                Intent intsupp = new Intent(UserDashboard.this, Support.class);
                startActivity(intsupp);
                break;
        }
        switch (item.getItemId()) {
            case R.id.Help:
                Intent inthelp= new Intent(UserDashboard.this, Help.class);
                startActivity(inthelp);
                break;
        }


        return true;
    }


    //Recycle view Function
    private void requestRecycler() {

       /* DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUser = reference.orderByChild("uid").equalTo(uid);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String buildingnameFromDB = snapshot.child(uid).child("buildingname").getValue(String.class);
                    final String flatnoFromDB = snapshot.child(uid).child("flatno").getValue(String.class);
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("visitor");
                    Query visitor = reference.orderByChild("buildingname").equalTo(buildingnameFromDB);
                    visitor.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("visitor");
                                Query visitor1 = reference.orderByChild("flatno").equalTo(flatnoFromDB);
                                visitor1.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if(snapshot.exists()){
                                            Date currentTime = Calendar.getInstance().getTime();
                                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                                            String currentDate  = dateFormat.format(currentTime);
                                            String nameofvis1 = snapshot.child(currentDate).child("name").getValue(String.class);
                                            String reasonofvis1 = snapshot.child(currentDate).child("reason").getValue(String.class);
                                            String dateofvis1 = snapshot.child(currentDate).child("currentDate").getValue(String.class);

                                            nameofvis = nameofvis1;
                                            reasonofvis = reasonofvis1;
                                            dateofvis = dateofvis1;



                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });


                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/



        requestRecycler.setHasFixedSize(true);
        requestRecycler.setLayoutManager(new LinearLayoutManager(this));


        ArrayList<RequestHelperClass> requestLocation = new ArrayList<>();


        requestLocation.add(new RequestHelperClass(R.drawable.man, "Name:- Sharvil", "Date/time:-27/8/20/2:30am", "Reason:-Guest"));
        requestLocation.add(new RequestHelperClass(R.drawable.man, "Name:- Sharvil", "Date/time:-27/8/20/2:30am", "Reason:-Guest"));
        requestLocation.add(new RequestHelperClass(R.drawable.man, "Name:- Sharvil", "Date/time:-27/8/20/2:30am", "Reason:-meet a friend"));
        requestLocation.add(new RequestHelperClass(R.drawable.man, "Name:- Sharvil", "Date/time:-27/8/20/2:30am", "Reason:-meet a friend"));


        adapter = new RequestAdapter(requestLocation);
        requestRecycler.setAdapter(adapter);
    }


    public void emergencyclick(View view) {
        Intent intemer =new Intent(UserDashboard.this,Emergency.class);
        startActivity(intemer);
    }

    public void localhelpclick(View view) {
        Intent intloc =new Intent(UserDashboard.this,LocalHelp.class);
        startActivity(intloc);
    }

    public void historyclick(View view) {
        Intent inthis = new Intent(UserDashboard.this,History.class);
        startActivity(inthis);
    }

    public void noticeclick(View view) {
        Intent intnot = new Intent(UserDashboard.this,Notice.class);
        startActivity(intnot);
    }

    public void vehicleclick(View view) {
        Intent intveh =new Intent(UserDashboard.this, Shop.class);
        startActivity(intveh);
    }
}