package com.example.simon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button play;
    Button settings;
    Button credits;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play = (Button)findViewById(R.id.button);
        settings = (Button)findViewById(R.id.button2);
        credits = (Button)findViewById(R.id.button3);
        sp = getSharedPreferences("level", 0);
        play.setOnClickListener(this);
        settings.setOnClickListener(this);
        credits.setOnClickListener(this);
        (new HideBars(this)).hideBars();

    }

    @Override
    public void onClick(View v) {
        if (v == play)
        {
            Intent playIntent;
            int level = sp.getInt("level",1);
            switch(level)
            {
                case(2):
                    playIntent = new Intent(this, GameMedium.class);
                    break;
                case(3):
                    playIntent = new Intent(this, GameHard.class);
                    break;
                default:
                    playIntent = new Intent(this, GameEasy.class);
                    break;
            }
            startActivity(playIntent);
            finish();
        } else if (v == settings)
        {
            Intent settingsIntent = new Intent(this, Settings.class);
            startActivity(settingsIntent);
            finish();
        }
        else if(v == credits)
        {
            Intent creditsIntent = new Intent(this, Stats.class);
            startActivity(creditsIntent);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage(R.string.exit_app_alert)
                .setPositiveButton(R.string.yes_btn, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finishAndRemoveTask();
                    }
                })
                .setNegativeButton(R.string.no_btn, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                }).show();
    }
}