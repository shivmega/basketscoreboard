package com.vito.basketscoreboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MatchActivity extends AppCompatActivity {

    private String namaHome, namaAway, winner;
    private int scoreHome, scoreAway;
    private CircleImageView homeTeam, awayTeam;
    private TextView tvScoreHome, tvScoreAway, tvNamaHome, tvNamaAway;
    private Button Home1, Home2, Home3, Away1, Away2, Away3, btnResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        homeTeam = findViewById(R.id.homeTeam);
        awayTeam = findViewById(R.id.awayTeam);
        tvNamaAway = findViewById(R.id.tvNamaAway);
        tvNamaHome= findViewById(R.id.tvNamaHome);
        tvScoreAway = findViewById(R.id.tvScoreAway);
        tvScoreHome = findViewById(R.id.tvScoreHome);
        Home1 = findViewById(R.id.Home1);
        Home2 = findViewById(R.id.Home2);
        Home3 = findViewById(R.id.Home3);
        Away1 = findViewById(R.id.Away1);
        Away2 = findViewById(R.id.Away2);
        Away3 = findViewById(R.id.Away3);
        btnResult = findViewById(R.id.btnResult);
        scoreHome = 0;
        scoreAway = 0;
        tvScoreHome.setText(String.valueOf(scoreHome));
        tvScoreAway.setText(String.valueOf(scoreAway));

        Bundle bundle = getIntent().getExtras();
        namaHome = bundle.getString("namaHome");
        tvNamaHome.setText(namaHome);
        namaAway = bundle.getString("namaAway");
        tvNamaAway.setText(namaAway);
        homeTeam.setImageURI(Uri.parse(bundle.getString("homeImgExtra")));
        awayTeam.setImageURI(Uri.parse(bundle.getString("awayImgExtra")));


        Home1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoreHome += 1;
                tvScoreHome.setText(String.valueOf(scoreHome));
            }
        });
        Home2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoreHome += 2;
                tvScoreHome.setText(String.valueOf(scoreHome));
            }
        });
        Home3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoreHome += 3;
                tvScoreHome.setText(String.valueOf(scoreHome));
            }
        });

        Away1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoreAway += 1;
                tvScoreAway.setText(String.valueOf(scoreAway));
            }
        });
        Away2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoreAway += 2;
                tvScoreAway.setText(String.valueOf(scoreAway));
            }
        });
        Away3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoreAway += 3;
                tvScoreAway.setText(String.valueOf(scoreAway));
            }
        });
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                winner = "empty";
                if (scoreHome>scoreAway){
                    winner = namaHome;
                }else if (scoreAway>scoreHome){
                    winner = namaAway;
                }else{
                    winner = "draw";
                }

                Intent intent = new Intent(MatchActivity.this, ResultActivity.class);
                intent.putExtra("winner", winner );
                startActivity(intent);
            }
        });
    }
}