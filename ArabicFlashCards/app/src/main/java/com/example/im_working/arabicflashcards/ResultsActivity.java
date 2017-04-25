package com.example.im_working.arabicflashcards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.im_working.arabicflashcards.MainActivity.answersCorrect;
import static com.example.im_working.arabicflashcards.MainActivity.answersIncorrect;
import static com.example.im_working.arabicflashcards.MainActivity.t;
import static com.example.im_working.arabicflashcards.MainActivity.timer;

public class ResultsActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Button returnToMain = (Button) findViewById(R.id.ReturnToHomeButton);
        returnToMain.setOnClickListener(this);

        Button playAgain = (Button) findViewById(R.id.PlayAgainButton);
        playAgain.setOnClickListener(this);

        TextView time = (TextView) findViewById(R.id.Time);
        time.setText(timer);

        TextView correct = (TextView) findViewById(R.id.CorrectNum);
        correct.setText(String.valueOf(answersCorrect));

        TextView incorrect = (TextView) findViewById(R.id.IncorrectNum);
        incorrect.setText(String.valueOf(answersIncorrect));


        answersCorrect = 0;
        answersIncorrect = 0;
        t.cancel();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ReturnToHomeButton:
                startActivity(new Intent(getApplicationContext(),MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                break;
            case R.id.PlayAgainButton:
                finish();
                break;

        }


    }

}