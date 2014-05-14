package com.jonduran.qahorn.qahornapp;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;

public class MainActivity extends Activity {
    private int originalVolume = 0;
    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getActionBar().hide();

        audioManager = (AudioManager)getSystemService(AUDIO_SERVICE);
        originalVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);

        final ImageButton hornButton = (ImageButton)this.findViewById(R.id.horn_button);
        hornButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.inception);
                mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mp.start();
            }
        });

        Switch switch1 = (Switch)this.findViewById(R.id.switch1);

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    hornButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.media1);
                            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
                            mp.start();
                        }
                    });
                } else {
                    hornButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.inception);
                            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
                            mp.start();
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onResume() {
        originalVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
        super.onResume();
    }

    @Override
    public void onPause() {
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, originalVolume, 0);
        super.onPause();
    }
}
