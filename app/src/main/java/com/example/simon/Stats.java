package com.example.simon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class Stats extends AppCompatActivity implements View.OnClickListener {

    ArrayAdapter recAdp;
    ListView recLst;
    Button backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(this);
        recLst = findViewById(R.id.recLst);
        (new HideBars(this)).hideBars();
        DataBaseHelper dbHlpr = new DataBaseHelper(this);
        List<UserRecords> usersRec = dbHlpr.getAll();
        recAdp = new ArrayAdapter<UserRecords>(this, android.R.layout.simple_list_item_1, usersRec);
        recLst.setAdapter(recAdp);
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onClick(View v) {
        if(v == backBtn)
        {
            this.onBackPressed();
        }
    }
}