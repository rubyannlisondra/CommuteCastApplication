package com.example.commutecast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class AddingReminder extends AppCompatActivity {

    Button selectBtn, createReminderBtn;
    ImageButton backButton, imageBtnAlarm;
    EditText tName;
    Uri uri;
    CheckBox checkboxAlarm;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_reminder);
        getSupportActionBar().hide();

        checkboxAlarm = findViewById(R.id.checkbox_alarm);
        backButton = findViewById(R.id.backBtn);
        selectBtn = findViewById(R.id.selectLocBtn);
        imageBtnAlarm = findViewById(R.id.imageBtnAlarm);
        createReminderBtn = findViewById(R.id.createReminderBtn);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            uri = data.getData();

                        }
                    }
                }
        );

        createReminderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToHome();
            }
        });

        selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectingLocation();
            }
        });
    }

    public void selectingLocation() {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
    public void backToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}