package com.example.im_working.arabicflashcards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ResultsActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Button returnToMain = (Button) findViewById(R.id.ReturnToHomeButton);

        returnToMain.setOnClickListener(this);

        Button playAgain = (Button) findViewById(R.id.PlayAgainButton);
        playAgain.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ReturnToHomeButton:
                Intent i = new Intent(ResultsActivity.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;
            case R.id.PlayAgainButton:
                finish();
                break;

        }


    }

}