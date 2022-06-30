package com.example.simon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.TextView;

public class GameMedium extends AppCompatActivity {
    Button ylwBtn, grnBtn, redBtn, bluBtn;
    TextView scoreText, stageText;
    Handler scoreHandler, stageHandler;
    HideBars hideBarsObj;
    GameButton[] btns = new GameButton[4];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_medium2);
        (new HideBars(this)).hideBars();
        ylwBtn = findViewById(R.id.button4);
        grnBtn = findViewById(R.id.button5);
        redBtn = findViewById(R.id.button6);
        bluBtn = findViewById(R.id.button7);
        scoreText = findViewById(R.id.scoreView);
        stageText = findViewById(R.id.stageView);
        btns[0] = new GreenButton(grnBtn, this);
        btns[1] = new YellowButton(ylwBtn, this);
        btns[2] = new RedButton(redBtn, this);
        btns[3] = new BlueButton(bluBtn, this);
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
        GameThread g = new GameThread(btns, scoreHandler, stageHandler, this, 20, 2);
        g.start();
    }

    @Override
    public void onBackPressed() {
        (new StartGameDialogFragment(this)).show(getSupportFragmentManager(), "dialog");
    }

}