package com.example.commutecast;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;

import android.content.Context;
import androidx.fragment.app.Fragment;


public class CreateAlarm extends Fragment implements AdapterView.OnItemSelectedListener, SeekBar.OnSeekBarChangeListener {

    private ImageView soundImageView;
    private ImageView alarmImageView;
    private SeekBar soundSeekBar;
    private SeekBar alarmSeekBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_alarm, container, false);

        Spinner alarmModeSpinner = view.findViewById(R.id.spinner);
        soundImageView = view.findViewById(R.id.volumeUp);
        alarmImageView = view.findViewById(R.id.alarm);
        soundSeekBar = view.findViewById(R.id.seekBar1);
        alarmSeekBar = view.findViewById(R.id.seekBar2);

        Context context = requireContext();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                context, R.array.alarm_modes,
                android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        alarmModeSpinner.setAdapter(adapter);
        alarmModeSpinner.setOnItemSelectedListener(this);

        soundSeekBar.setOnSeekBarChangeListener(this);
        alarmSeekBar.setOnSeekBarChangeListener(this);

        return view;
    }

    public void setAlarm(View view) {
        Spinner alarmModeSpinner = view.findViewById(R.id.spinner);
        String selectedOption = alarmModeSpinner.getSelectedItem().toString();
        int soundLevel = soundSeekBar.getProgress();
        int alarmLevel = alarmSeekBar.getProgress();

        ImageView soundImageView = view.findViewById(R.id.volumeUp);
        ImageView alarmImageView = view.findViewById(R.id.alarm);

        soundImageView.setImageResource(R.drawable.baseline_volume_up_24);
        alarmImageView.setImageResource(R.drawable.baseline_add_alert_24);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String selectedOption = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}