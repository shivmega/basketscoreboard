package com.vito.basketscoreboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView tvWinnerTeam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        tvWinnerTeam = findViewById(R.id.tvWinnerTeam);
        tvWinnerTeam.setText(getIntent().getExtras().getString("winner"));
    }
}