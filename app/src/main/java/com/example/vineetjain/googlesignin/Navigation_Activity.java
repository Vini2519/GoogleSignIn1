package com.example.vineetjain.googlesignin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.vineetjain.googlesignin.About.About_Us;
import com.example.vineetjain.googlesignin.Academic.Academics;
import com.example.vineetjain.googlesignin.Department.Departments;
import com.example.vineetjain.googlesignin.Extra_curricular.Extra_Curricular;
import com.example.vineetjain.googlesignin.Gallery.Gallery;
import com.example.vineetjain.googlesignin.Home.Home_Fragment;
import com.example.vineetjain.googlesignin.Placement.Placements;
import com.example.vineetjain.googlesignin.Queries.Query;
import com.example.vineetjain.googlesignin.SignIn.GoogleSignInActivity;
import com.example.vineetjain.googlesignin.Syllabus.Syllabus;
import com.google.firebase.auth.FirebaseAuth;

public class Navigation_Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListner;

    private static boolean doubleBackToExitPressedOnce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Home_Fragment home = new Home_Fragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, home);
        fragmentTransaction.commit();

        mAuth = FirebaseAuth.getInstance();

        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    startActivity(new Intent(Navigation_Activity.this, GoogleSignInActivity.class));
                }
            }
        };

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View view = navigationView.getRootView();

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListner);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.Logout) {
            mAuth.signOut();
            return true;
        }
        if (id == R.id.Editprofile) {
            Intent intent = new Intent(Navigation_Activity.this, Query.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Home) {
            Toast.makeText(this, "HOME", Toast.LENGTH_SHORT).show();
            Home_Fragment home = new Home_Fragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, home).commit();

        } else if (id == R.id.Academics) {
            Toast.makeText(this, "Academics", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Navigation_Activity.this, Academics.class);
            startActivity(intent);

        } else if (id == R.id.Attendence) {
            Toast.makeText(this, "Attendance", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Navigation_Activity.this, Attendence.class);
            startActivity(intent);

        } else if (id == R.id.Gallery) {
            Toast.makeText(this, "Gallery", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Navigation_Activity.this, Gallery.class);
            startActivity(intent);

        } else if (id == R.id.Extra_Curricular) {
            Toast.makeText(this, "Extra-Curricular", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Navigation_Activity.this, Extra_Curricular.class);
            startActivity(intent);

        } else if (id == R.id.Departments) {
            Toast.makeText(this, "Departments", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Navigation_Activity.this, Departments.class);
            startActivity(intent);

        } else if (id == R.id.Placements) {
            Toast.makeText(this, "Placements", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Navigation_Activity.this, Placements.class);
            startActivity(intent);

        } else if (id == R.id.Contact_Us) {
            Toast.makeText(this, "Contact_Us", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Navigation_Activity.this, Contact_Us.class);
            startActivity(intent);

        } else if (id == R.id.About_Us) {
            Toast.makeText(this, "About Us", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Navigation_Activity.this, About_Us.class);
            startActivity(intent);

        } else if (id == R.id.Syllabus) {
            Toast.makeText(this, "Syllabus", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Navigation_Activity.this, Syllabus.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
