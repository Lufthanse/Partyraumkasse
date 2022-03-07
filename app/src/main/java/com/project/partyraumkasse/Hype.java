package com.project.partyraumkasse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;


public class Hype extends AppCompatActivity {

    public MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hype);

        mp = MediaPlayer.create(this, R.raw.hansi);

        mp.start();

    }

    public void onBackPressed(){
        mp.stop();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}