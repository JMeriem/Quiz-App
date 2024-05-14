package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Quiz4 extends AppCompatActivity {
    RadioGroup rg;
    RadioButton rb;
    Button bNext;
    TextView timerTextView;
    CountDownTimer timer;
    int score;
    String RepCorrect="La Cinquieme Avenue";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz4);
        rg = (RadioGroup) findViewById(R.id.choices);
        bNext = (Button) findViewById(R.id.bnext);
        timerTextView = findViewById(R.id.timerTextView);

        Intent intent = getIntent();
        score = intent.getIntExtra("score", 0);

        // Timer setup
        timer = new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                long secondsLeft = millisUntilFinished / 1000;
                timerTextView.setText(String.valueOf(secondsLeft) + " secondes restantes");
            }

            public void onFinish() {

                Intent intent = new Intent(Quiz4.this, Quiz5.class);
                intent.putExtra("score", score);
                startActivity(intent);
                overridePendingTransition(R.anim.exit, R.anim.entry);
                finish();
            }
        };
        timer.start();

        bNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rg.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "Merci de choisir une réponse S.V.P !", Toast.LENGTH_SHORT).show();
                } else {
                    rb = (RadioButton) findViewById(rg.getCheckedRadioButtonId());
                    if (rb.getText().toString().equals(RepCorrect)) {
                        score += 1;
                    }
                    timer.cancel();
                    Intent intent = new Intent(Quiz4.this, Quiz5.class);
                    intent.putExtra("score", score);
                    startActivity(intent);
                    overridePendingTransition(R.anim.exit, R.anim.entry);
                    finish();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }
}