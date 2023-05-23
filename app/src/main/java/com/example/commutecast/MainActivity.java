package com.example.commutecast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
<<<<<<< HEAD
import androidx.appcompat.app.AppCompatDelegate;
=======
import androidx.drawerlayout.widget.DrawerLayout;
>>>>>>> 3775744ecf6cc0004eecd7fb5db95282cec9d9ef
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    ViewPager2 viewPager2;
    ViewPagerAdapter viewPagerAdapter;
    BottomNavigationView bottomNavigationView;
    Switch switch_id;

    private Home homeFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

<<<<<<< HEAD
        switch_id = findViewById(R.id.switch_id);
=======
        homeFragment = new Home();

>>>>>>> 3775744ecf6cc0004eecd7fb5db95282cec9d9ef
        bottomNavigationView = findViewById(R.id.navBtn);
        viewPager2 = findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(viewPagerAdapter);

<<<<<<< HEAD
        switch_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switch_id.isChecked()) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });
=======
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, homeFragment)
                .commit();

>>>>>>> 3775744ecf6cc0004eecd7fb5db95282cec9d9ef

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                switch(id) {
                    case R.id.bottom_home:
                        viewPager2.setCurrentItem(0);
                        break;
                    case R.id.bottom_favorite:
                        viewPager2.setCurrentItem(1);
                        break;
                    case R.id.bottom_alarm:
                        viewPager2.setCurrentItem(2);
                        break;
                    case R.id.bottom_profile:
                        viewPager2.setCurrentItem(3);
                        break;
                }
                return false;
            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch(position) {
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.bottom_home).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.bottom_favorite).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.bottom_alarm).setChecked(true);
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.bottom_profile).setChecked(true);
                        break;
                }
                super.onPageSelected(position);
            }
        });
<<<<<<< HEAD

        fAuth = FirebaseAuth.getInstance();
        /*ImageButton logout = findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fAuth.signOut();
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });*/
=======
>>>>>>> 3775744ecf6cc0004eecd7fb5db95282cec9d9ef
    }
}