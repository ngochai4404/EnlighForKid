package com.example.nam.demo.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.nam.demo.R;

import mehdi.sakout.fancybuttons.FancyButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FancyButton bt,Quiz,VideoLearning,LookAndChoose,Start,ListenAndGuess;
    int key = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        bt = (FancyButton) findViewById(R.id.Exit);
        bt.setOnClickListener(this);

        Quiz = (FancyButton) findViewById(R.id.Quiz);
        Quiz.setOnClickListener(this);
        VideoLearning = (FancyButton) findViewById(R.id.VideoLearning);
        VideoLearning.setOnClickListener(this);
        LookAndChoose = (FancyButton) findViewById(R.id.LookAndChoose);
        LookAndChoose.setOnClickListener(this);
        Start = (FancyButton) findViewById(R.id.Start);
        Start.setOnClickListener(this);
        ListenAndGuess = (FancyButton) findViewById(R.id.ListenAndGuess);
        ListenAndGuess.setOnClickListener(this);


    }
//    public void setLocale(String lang) {
//        Locale locale = new Locale(lang);
//        Locale.setDefault(locale);
//        Configuration config = new Configuration();
//        config.locale = locale;
//
//        Log.d("error2", String.valueOf(Quiz.getText()));
//
//    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.Start:
                Intent t4 = new Intent(this, com.example.nam.demo.Start.start.class);
                startActivity(t4);
                overridePendingTransition(R.anim.flip_horizontal_in,R.anim.flip_horizontal_out);
                break;
            case R.id.VideoLearning:
                Intent t2 = new Intent(this, com.example.nam.demo.VideoLearning.VideoLearning.class);
                startActivity(t2);
                overridePendingTransition(R.anim.flip_vertical_in,R.anim.flip_vertical_out);
                break;
            case R.id.LookAndChoose:
                Intent t3 = new Intent(this, com.example.nam.demo.LookAndChoose.LookAndChoose.class);
                startActivity(t3);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                break;
            case R.id.ListenAndGuess:
                Intent t1 = new Intent(this, com.example.nam.demo.ListenAndGuess.ListenAndGuess.class);
                startActivity(t1);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                break;


            case R.id.Quiz:
                Intent t = new Intent(this, com.example.nam.demo.Quiz.Quiz.class);
                startActivity(t);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                break;
            case R.id.Exit:
                finish();
                break;
        }

    }
}
