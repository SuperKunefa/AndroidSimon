package com.example.simon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

public class GameEasy extends AppCompatActivity {
    Button ylwBtn, grnBtn, redBtn;
    TextView scoreText, stageText;
    Handler scoreHandler, stageHandler;
    GameButton[] btns = new GameButton[3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_easy);
        ylwBtn = findViewById(R.id.button4);
        grnBtn = findViewById(R.id.button5);
        redBtn = findViewById(R.id.button6);
        scoreText = findViewById(R.id.scoreView);
        stageText = findViewById(R.id.stageView);
        btns[0] = new GreenButton(grnBtn, this);
        btns[1] = new YellowButton(ylwBtn, this);
        btns[2] = new RedButton(redBtn, this);
        (new HideBars(this)).hideBars();
        scoreHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                scoreText.setText("Score: " + msg.arg1);
                return true;
            }
        });
        stageHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                stageText.setText("Stage: " + msg.arg1);
                return true;
            }
        });
        GameThread g = new GameThread(btns, scoreHandler, stageHandler, this, 12,1);
        g.start();
    }

    @Override
    public void onBackPressed() {
        (new StartGameDialogFragment(this)).show(getSupportFragmentManager(), "dialog");
    }

}