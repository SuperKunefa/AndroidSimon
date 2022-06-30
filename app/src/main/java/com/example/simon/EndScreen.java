package com.example.simon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EndScreen extends AppCompatActivity implements View.OnClickListener {
    TextView score, stage, status;
    int scoreNum, stageNum, levelNum;
    boolean win;
    Button backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);
        (new HideBars(this)).hideBars();
        score = (TextView) findViewById(R.id.textScore);
        stage = (TextView) findViewById(R.id.textStage);
        status = (TextView) findViewById(R.id.textLost);
        backBtn = (Button) findViewById(R.id.backBtn);
        Bundle extras = getIntent().getExtras();
        if(extras == null)
        {
            scoreNum = -1;
            stageNum = -1;
            levelNum = -1;
            win = false;
        }
        else
        {
            scoreNum = extras.getInt("score");
            stageNum = extras.getInt("stage");
            levelNum = extras.getInt("lvl");
            win = extras.getBoolean("win");
        }
        DataBaseHelper dbh = new DataBaseHelper(this);
        UserRecords rec = new UserRecords(scoreNum, stageNum, win, levelNum);
        dbh.addOne(rec);
        score.setText("Score: " + scoreNum);
        stage.setText("Stage: " + stageNum);
        if(win)
        {
            status.setText("You win!");
            status.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }
        backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == backBtn)
        {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}

