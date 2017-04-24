package com.example.im_working.arabicflashcards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static com.example.im_working.arabicflashcards.MainActivity.JSON;

public class GameActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    HashMap<Boolean, Button> bool = new HashMap<Boolean, Button>();
    List<Button> buttons = new ArrayList<Button>();
    Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        String message = intent.getStringExtra("chose");
        TextView textView = (TextView) findViewById(R.id.Option);
        textView.setText("What translates to " + message);

        Button btn0 = (Button) findViewById(R.id.button_0);
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

//        if()

//        for(int i = 0; i < count; i++) {
            Options(message);
//        }

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch(v.getId())
        {
            case R.id.button_0 :
                bool.put(true, btn0);
                break;
            case R.id.button_1 :
                bool.put(true, btn1);
                break;
            case R.id.button_2 :
                bool.put(true, btn2);
                break;
            case R.id.button_3 :
                bool.put(true, btn3);
                break;
            case R.id.button_4 :
                bool.put(true, btn4);
                break;
            case R.id.button_5 :
                bool.put(true, btn5);
                break;
            case R.id.button_6 :
                bool.put(true, btn6);
                break;
            case R.id.button_7 :
                bool.put(true, btn7);
                break;

        }

    }

    public void Options(String message) {

        if (message.equals("English")) {

            ArrayList<Integer> temp = new ArrayList<>();

//            int ran = random_number(temp, JSON.length());
//            temp.add(ran);
//            temp.add(ran);
//            try {
//                buttons.get(0).setText(JSON.getJSONArray(JSON.names().getString(ran)).get(0).toString());
//                buttons.get(1).setText(JSON.getJSONArray(JSON.names().getString(ran)).get(1).toString());
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
            int ran = random_number(temp, JSON.length());

            for(int bttn=0 ; bttn <buttons.size(); bttn++) {
                try {
                    if(bttn == 0){
                        temp.add(ran);
                        buttons.get(bttn).setText(JSON.getJSONArray(JSON.names().getString(ran)).get(0).toString());
                    }
                    else if(bttn == 1){
                        temp.add(ran);
                        buttons.get(bttn).setText(JSON.getJSONArray(JSON.names().getString(ran)).get(1).toString());
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
                guess = JSON.names().getString(temp.get((int) (Math.random() * temp.size())));
                textView.setText(guess);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        } else if (message.equals("Arabic")) {


        } else if (message.equals("Romanization")) {


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

    
