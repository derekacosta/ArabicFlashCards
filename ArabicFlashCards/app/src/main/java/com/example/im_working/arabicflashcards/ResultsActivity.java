package com.example.im_working.arabicflashcards;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.im_working.arabicflashcards.GameActivity.status;
import static com.example.im_working.arabicflashcards.GameActivity.status2;
import static com.example.im_working.arabicflashcards.GameActivity.temp;
import static com.example.im_working.arabicflashcards.GameActivity.temp2;
import static com.example.im_working.arabicflashcards.GameActivity.ts;
import static com.example.im_working.arabicflashcards.MainActivity.answersCorrect;
import static com.example.im_working.arabicflashcards.MainActivity.answersIncorrect;
import static com.example.im_working.arabicflashcards.MainActivity.database;
import static com.example.im_working.arabicflashcards.MainActivity.t;
import static com.example.im_working.arabicflashcards.MainActivity.timer;
import static com.example.im_working.arabicflashcards.MainActivity.total;

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
        time.setText(timer + " sec");

        TextView correct = (TextView) findViewById(R.id.CorrectNum);
        correct.setText(String.valueOf(answersCorrect));

        TextView incorrect = (TextView) findViewById(R.id.IncorrectNum);
        incorrect.setText(String.valueOf(answersIncorrect));

        TextView wordsknown = (TextView) findViewById(R.id.Words);
        wordsknown.setText(String.valueOf(answersCorrect) + ":" + String.valueOf(total));

        // Write a message to the database
//        userdata.put(status, temp);
//        myKey.setValue(userdata);
//        ArrayList<String> correct =
//        myKey.setValue(userdata);
//        myKey.updateChildren();
//        myKey.setValue(answersCorrect);
//        myKey.setValue(answersIncorrect);
//        database.getInstance().getReference().child(ts).updateChildren((Map<String, Object>) temp);
        Long tsLong = System.currentTimeMillis();
        String timer = tsLong.toString();
        DatabaseReference myKey = database.getReference(String.valueOf(MainActivity.ts) + " " +timer);
        Map<String, User> users = new HashMap<String, User>();
        Map<String, ArrayList<String>> something = new HashMap<String, ArrayList<String>>();
        if(temp.size() != 0)
            something.put(status, temp);
        if(temp2.size() != 0)
            something.put(status2, temp2);
        float did =answersCorrect/answersIncorrect;
        users.put(String.valueOf(ts) + " " + timer, new User(timer, answersCorrect, answersIncorrect, something, did));
        myKey.setValue(users);

        String filename = "KnownWords.txt";
//        String string = "Hello world!";
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            for(String s: temp)
                outputStream.write(s.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Context context = getApplicationContext();

        String blah="";

        try {
            FileInputStream fin = openFileInput("KnownWords.txt");
            int c;
            while( (c = fin.read()) != -1){
                blah = blah + Character.toString((char)c) + "\n";
            }

//string temp contains all the data of the file.
            fin.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] blah2 = blah.split("\n");

        Map<String,Integer> repeatationMap= new HashMap<String,Integer>();
        for(String str : blah2){

            if(repeatationMap.containsKey(str)) {
                repeatationMap.put(str,repeatationMap.get(str) + 1);
            }
            else {
                repeatationMap.put(str, 1);
            }
        }

        //find duplicates and stores them in  an array
        ArrayList<Boolean> dup = new ArrayList<>();
        int count = 0;
        for(int repatCount : repeatationMap.values()){
            if(repatCount > 1) {
                dup.add(repeatationMap.containsValue(repatCount));
                count++;
            }
        }


        temp.clear();
        answersCorrect = 0;
        answersIncorrect = 0;
        t.cancel();
    }

    public static class Template {

        String name;
        ArrayList<String> list;

        public Template() {
        }

        public Template(String name, ArrayList<String> list) {
            this.name = name;
            this.list = list;
        }

        public String getName() {
            return name;
        }

        public ArrayList<String> getList() {
            return list;
        }
    }

    public static class User {


        public Integer Correct;
        public Integer Incorrect;
        public Map<String, ArrayList<String>> Temp;
        public String timer;
        public float did;


        public User(String timer, Integer answersCorrect, Integer answersIncorrect, Map<String, ArrayList<String>> something, float did) {
            this.Correct = answersCorrect;
            this.Incorrect = answersIncorrect;
            this.Temp = something;
            this.timer = timer;
            this.did = did;
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ReturnToHomeButton:
                //startActivity(new Intent(getApplicationContext(),MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                Intent i = new Intent (this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;
            case R.id.PlayAgainButton:
                finish();
                break;

        }


    }

}