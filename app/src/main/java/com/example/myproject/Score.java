package com.example.myproject;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Score extends AppCompatActivity {
    TextView tvscore;
    ProgressBar pb;
    Button blogout,btry;
    int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        tvscore=findViewById(R.id.etscore);
        pb=findViewById(R.id.etprogress);
        blogout=findViewById(R.id.bLogout);
        btry=findViewById(R.id.bTry);
        Intent i1=getIntent();
        score= i1.getIntExtra("score", 0);
        tvscore.setText(score*100/5+"%");
        pb.setProgress(score*100/5);
        blogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                overridePendingTransition(R.anim.exit,R.anim.entry);
                finish();
            }
        });
        btry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Quiz1.class));
                overridePendingTransition(R.anim.exit,R.anim.entry);
                finish();
            }
        });

    }
}
