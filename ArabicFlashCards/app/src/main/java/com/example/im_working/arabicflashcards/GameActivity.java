package com.example.im_working.arabicflashcards;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static com.example.im_working.arabicflashcards.MainActivity.JSON;
import static com.example.im_working.arabicflashcards.MainActivity.answersCorrect;
import static com.example.im_working.arabicflashcards.MainActivity.answersIncorrect;
import static com.example.im_working.arabicflashcards.MainActivity.count;
import static com.example.im_working.arabicflashcards.MainActivity.progressBarCounter;
import static com.example.im_working.arabicflashcards.R.id.button_0;

public class GameActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    ArrayList<String> butt = new ArrayList<>();
    HashMap<Boolean, ArrayList<String>> bool = new HashMap<>();
    List<Button> buttons = new ArrayList<Button>();
    String btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7;
    ArrayList<String> answers = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ProgressBar prog = (ProgressBar) findViewById(R.id.progressBar);
        progressBarCounter++;
        prog.setProgress(0, true);
//        prog.incrementProgressBy(progressBarCounter);
        prog.setMax(count);
        prog.setProgress(progressBarCounter, true);


        Intent intent = getIntent();
        String message = intent.getStringExtra("chose");
        TextView textView = (TextView) findViewById(R.id.Option);
        textView.setText("What translates to " + message);

        Button btn0 = (Button) findViewById(button_0);
        Button btn1 = (Button) findViewById(R.id.button_1);
        Button btn2 = (Button) findViewById(R.id.button_2);
        Button btn3 = (Button) findViewById(R.id.button_3);
        Button btn4 = (Button) findViewById(R.id.button_4);
        Button btn5 = (Button) findViewById(R.id.button_5);
        Button btn6 = (Button) findViewById(R.id.button_6);
        Button btn7 = (Button) findViewById(R.id.button_7);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);



        buttons.add(btn0);
        buttons.add(btn1);
        buttons.add(btn2);
        buttons.add(btn3);
        buttons.add(btn4);
        buttons.add(btn5);
        buttons.add(btn6);
        buttons.add(btn7);

        Collections.shuffle(buttons);


        Options(message);


    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        MainActivity.countee++;

        switch (v.getId()) {
            case button_0:
                butt.add(String.valueOf(((Button) v).getText()));
                bool.put(true, butt);
                break;
            case R.id.button_1:
                butt.add(String.valueOf(((Button) v).getText()));
                bool.put(true, butt);
                break;
            case R.id.button_2:
                butt.add(String.valueOf(((Button) v).getText()));
                bool.put(true, butt);
                break;
            case R.id.button_3:
                butt.add(String.valueOf(((Button) v).getText()));
                bool.put(true, butt);
                break;
            case R.id.button_4:
                butt.add(String.valueOf(((Button) v).getText()));
                bool.put(true, butt);
                break;
            case R.id.button_5:
                butt.add(String.valueOf(((Button) v).getText()));
                bool.put(true, butt);
                break;
            case R.id.button_6:
                butt.add(String.valueOf(((Button) v).getText()));
                bool.put(true, butt);
                break;
            case R.id.button_7:
                butt.add(String.valueOf(((Button) v).getText()));
                bool.put(true, butt);
                break;

        }
        if(bool.get(true).size() == 2) {
//            if(butt.contains(answers.get(0).toString()) && butt.contains(butt.get(1).toString())
//            || butt.contains(answers.get(1).toString()) && butt.contains(butt.get(2).toString())
//                    || butt.contains(answers.get(0).toString()) && butt.contains(butt.get(2).toString()))
//                getWindow().getDecorView().setBackgroundColor(Color.GREEN);
//            else
//                getWindow().getDecorView().setBackgroundColor(Color.RED);
            if(answers.contains(butt.get(0)) && answers.contains(butt.get(1)) && !butt.get(0).equals(butt.get(1))){
                getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                answersCorrect++;
            }
            else {
                getWindow().getDecorView().setBackgroundColor(Color.RED);
                answersIncorrect++;
            }
            answers.clear();
            if(MainActivity.countee / 2 != count){
                recreate();
            }
            else{
                MainActivity.countee = 0;
                bool.clear();
                butt.clear();
                progressBarCounter = 0;
                Intent i = new Intent(GameActivity.this, ResultsActivity.class);
                startActivity(i);
            }
        }


    }

    public void Options(String message) {

        if (message.equals("English")) {

            ArrayList<Integer> temp = new ArrayList<>();

            int ran = random_number(temp, JSON.length());

            for(int bttn=0 ; bttn <buttons.size(); bttn++) {
                try {
                    if(bttn == 0){
                        temp.add(ran);
                        buttons.get(bttn).setText(JSON.getJSONArray(JSON.names().getString(ran)).get(0).toString());
                        answers.add(JSON.getJSONArray(JSON.names().getString(ran)).get(0).toString());
                    }
                    else if(bttn == 1){
                        temp.add(ran);
                        buttons.get(bttn).setText(JSON.getJSONArray(JSON.names().getString(ran)).get(1).toString());
                        answers.add(JSON.getJSONArray(JSON.names().getString(ran)).get(1).toString());
                    }
                    else {
                        int random = random_number(temp, JSON.length());
                        temp.add(random);
                        buttons.get(bttn).setText(JSON.getJSONArray(JSON.names().getString(random)).get(bttn % 2).toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


            String guess;
            try {
                TextView textView = (TextView) findViewById(R.id.determine);
                guess = JSON.names().getString(temp.get(0));
                answers.add(JSON.names().getString(temp.get(0)));
                textView.setText(guess);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        } else if (message.equals("Arabic")) {
            ArrayList<Integer> temp = new ArrayList<>();

            int ran = random_number(temp, JSON.length());

            for(int bttn=0 ; bttn <buttons.size(); bttn++) {
                try {
                    if(bttn == 0){
                        temp.add(ran);
                        buttons.get(bttn).setText(JSON.names().getString(ran));
                        answers.add(JSON.names().getString(ran));
                    }
                    else if(bttn == 1){
                        temp.add(ran);
                        buttons.get(bttn).setText(JSON.getJSONArray(JSON.names().getString(ran)).get(1).toString());
                        answers.add(JSON.getJSONArray(JSON.names().getString(ran)).get(1).toString());
                    }
                    else {
                        int random = random_number(temp, JSON.length());
                        temp.add(random);
                        if(bttn%2 == 0)
                            buttons.get(bttn).setText(JSON.getJSONArray(JSON.names().getString(random)).get(1).toString());
                        else
                            buttons.get(bttn).setText(JSON.names().getString(random));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


            String guess;
            try {
                TextView textView = (TextView) findViewById(R.id.determine);
                guess = JSON.getJSONArray(JSON.names().getString(temp.get(0))).get(0).toString();
                answers.add(JSON.getJSONArray(JSON.names().getString(temp.get(0))).get(0).toString());
                textView.setText(guess);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else if (message.equals("Romanization")) {
            ArrayList<Integer> temp = new ArrayList<>();

            int ran = random_number(temp, JSON.length());

            for(int bttn=0 ; bttn <buttons.size(); bttn++) {
                try {
                    if(bttn == 0){
                        temp.add(ran);
                        buttons.get(bttn).setText(JSON.names().getString(ran));
                        answers.add(JSON.names().getString(ran));
                    }
                    else if(bttn == 1){
                        temp.add(ran);
                        buttons.get(bttn).setText(JSON.getJSONArray(JSON.names().getString(ran)).get(0).toString());
                        answers.add(JSON.getJSONArray(JSON.names().getString(ran)).get(0).toString());
                    }
                    else {
                        int random = random_number(temp, JSON.length());
                        temp.add(random);
                        if(bttn%2 == 0)
                            buttons.get(bttn).setText(JSON.getJSONArray(JSON.names().getString(random)).get(0).toString());
                        else
                            buttons.get(bttn).setText(JSON.names().getString(random));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


            String guess;
            try {
                TextView textView = (TextView) findViewById(R.id.determine);
                guess = JSON.getJSONArray(JSON.names().getString(temp.get(0))).get(1).toString();
                answers.add(JSON.getJSONArray(JSON.names().getString(temp.get(0))).get(1).toString());
                textView.setText(guess);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    public int random_number(ArrayList<Integer> temp, int length) {
        int random = (int) (Math.random() * length + 1);
        if(!temp.contains(random)) 
            return random;
        else return random_number(temp, length);
        
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

    
