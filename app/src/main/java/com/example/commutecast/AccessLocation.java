package com.example.commutecast;

import static androidx.constraintlayout.motion.widget.Debug.getLocation;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationRequest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;

import com.google.android.material.button.MaterialButton;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class AccessLocation extends AppCompatActivity {

    private Button mSkipBtn;
    private MaterialButton singlePermissionBtn;
    private TextView resultTv;
    private static final String TAG = "PERMISSION_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access_location);

        getSupportActionBar().hide();

        singlePermissionBtn = findViewById(R.id.continueBtn);
        resultTv = findViewById(R.id.tvGeoLocation);
        mSkipBtn = findViewById(R.id.skipBtn);

        singlePermissionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String permission = Manifest.permission.ACCESS_FINE_LOCATION;
                permissionLauncherSingle.launch(permission);
            }
        });

        mSkipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHome();
            }
        });
    }

    private ActivityResultLauncher<String> permissionLauncherSingle = registerForActivityResult(new ActivityResultContracts.RequestPermission(),
            new ActivityResultCallback<Boolean>() {
                @Override
                public void onActivityResult(Boolean isGranted) {
                    Log.d(TAG, "onActivityResult: isGranted: " + isGranted);
                    if(isGranted) {
                        singlePermissionGranted();
                    }
                    else {
                        Log.d(TAG, "onActivityResult: Permission denied...");
                        Toast.makeText(AccessLocation.this, "Permission denied...", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    private void singlePermissionGranted() {
        resultTv.setText("Single Permission granted. You can do your tasks...");
    }

    public void openHome() {
        Intent intent1 = new Intent(this, Login.class);
        startActivity(intent1);
    }
}