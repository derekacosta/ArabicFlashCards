package com.example.im_working.arabicflashcards;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity implements OnClickListener, AdapterView.OnItemSelectedListener {

    static JSONObject JSON;

    private Spinner spinner;
    private static final String[]paths = {"Arabic", "English", "Romanization"};

    TextView counter;

    static int count = 0;
    static int countee = 0;
    int selected = 0;
    static int progressBarCounter = 0;
    static int answersCorrect = 0;
    static int answersIncorrect = 0;
    static String timer;
    static int total = 0;


    static FirebaseDatabase database = FirebaseDatabase.getInstance();
//    static Long tsLong = System.currentTimeMillis()/1000;
//    static String ts = tsLong.toString();
    static Date ts = new Date(System.currentTimeMillis());
    static Map<String, ArrayList<String>> userdata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadTask task = new DownloadTask();
        try {
            JSON = task.execute().get();
            total = JSON.length();
//            Iterator<?> keys = JSON.keys();

//            while( keys.hasNext() ) {
//                String key = (String) keys.next();
//                System.out.println("Key: " + key);
//            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Button play = (Button) findViewById(R.id.Play);
        play.setOnClickListener(this);

        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        this.counter = (TextView) findViewById(R.id.counter);

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        // Sets the initial value of the SeekBar (it must be the same initial
        // value of the counter)
        seekBar.setProgress(10);
        // Sets the max value of the counter
        seekBar.setMax(total);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                counter.setText(String.valueOf(progress));
                count = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
         selected = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    static CountDownTimer t = new CountDownTimer(100000000 , 1000) {
        int cnt;

        @Override
        public void onTick(long millisUntilFinished) {

            cnt++;
            String time = new Integer(cnt).toString();

            long millis = cnt;
            int seconds = (int) (millis / 60);
            int minutes = seconds / 60;
            seconds     = seconds % 60;

            timer = String.format(String.valueOf(millis));

        }

        @Override
        public void onFinish() {            }
    };

    @Override
    public void onClick(View v) {
//            increment = 1/count;
            Intent i = new Intent(MainActivity.this, GameActivity.class);
            i.putExtra("chose", paths[selected]);
            startActivity(i);
            t.start();
    }
//    @Override
//    public void onRestart() {
//        super.onRestart();
//        //When BACK BUTTON is pressed, the activity on the stack is restarted
//        //Do what you want on the refresh procedure here
//    }

    private class DownloadTask extends AsyncTask<JSONObject, Void, JSONObject> {

        // This is run in a background thread
        @Override
        protected JSONObject doInBackground(JSONObject... param) {
            // get the string from params, which is an array

            URL url;
            String result = "";
            HttpURLConnection conn = null;
            try {
                url = new URL("https://raw.githubusercontent.com/derekacosta/ArabicScrape/master/compact.json");

                conn = (HttpURLConnection) url.openConnection();

                InputStream in = conn.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1) {

                    char current = (char) data;

                    result += current;

                    data = reader.read();

                }


                JSON = new JSONObject(result);

            } catch (Exception e) {

                e.printStackTrace(System.out);
            }

            return JSON;

        }


    }



}
