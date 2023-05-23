package com.example.commutecast;

import static android.app.Activity.RESULT_OK;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class Home extends Fragment {

    FloatingActionButton addTaskBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        addTaskBtn = view.findViewById(R.id.floationActionButton);

        addTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proceedToAddingReminder();
            }
        });

        return view;
    }

    public void proceedToAddingReminder() {
        Intent intent = new Intent(requireActivity(), AddingReminder.class);
        startActivity(intent);
    }
}