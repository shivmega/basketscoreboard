package com.vito.basketscoreboard;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private CircleImageView homeTeam, awayTeam;
    private EditText etHomeTeam, etAwayteam;
    private Button submit;
    private String Home, Away;
    private Uri urihomeImg;
    private Uri uriawayImg;


    private static final int HOME_REQUEST_CODE = 1;
    private static final int AWAY_REQUEST_CODE = 2;
    private static final String TAG = MainActivity.class.getCanonicalName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeTeam = findViewById(R.id.homeTeam);
        awayTeam = findViewById(R.id.awayTeam);
        etHomeTeam = findViewById(R.id.etHomeTeam);
        etAwayteam = findViewById(R.id.etAwayTeam);
        submit = findViewById(R.id.submit);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Home = etHomeTeam.getText().toString();
                Away = etAwayteam.getText().toString();
                Intent intent = new Intent(MainActivity.this, MatchActivity.class);
                intent.putExtra("namaHome",Home);
                intent.putExtra("namaAway",Away);
                intent.putExtra("homeImgExtra", homeTeam.toString());
                intent.putExtra("awayImgExtra", awayTeam.toString());
                startActivity(intent);
            }
        });
        homeTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), HOME_REQUEST_CODE);
            }
        });
        awayTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), AWAY_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED){
            Log.d(TAG, "CANCEL PICK IMAGE");
            return;
        }else if (requestCode == HOME_REQUEST_CODE){
            if (data != null){
                try {
                    Uri imageUrihome = data.getData();
                    urihomeImg = imageUrihome;
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUrihome);
                    homeTeam.setImageBitmap(bitmap);
                }catch (IOException error){
                    Toast.makeText(this, "FAILED LOADING IMAGE", Toast.LENGTH_SHORT).show();
                }
            }
        }else if (requestCode == AWAY_REQUEST_CODE){
            if (data != null){
                try {
                    Uri imageUriaway = data.getData();
                    uriawayImg = imageUriaway;
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUriaway);
                    awayTeam.setImageBitmap(bitmap);
                }catch (IOException error){
                    Toast.makeText(this, "FAILED LOADING IMAGE", Toast.LENGTH_SHORT).show();
                }
            }
    }}
}