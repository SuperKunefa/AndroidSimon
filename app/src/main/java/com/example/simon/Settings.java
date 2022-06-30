package com.example.simon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Settings extends AppCompatActivity implements View.OnClickListener {
    Button bckBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        (new HideBars(this)).hideBars();
        bckBtn = (Button) findViewById(R.id.backBtn);
        bckBtn.setOnClickListener(this);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.settings_container, new Preference()).commit();
    }

    @Override
    public void onClick(View v) {
        if((Button) v == bckBtn)
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