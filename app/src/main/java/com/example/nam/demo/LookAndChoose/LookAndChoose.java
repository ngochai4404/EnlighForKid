package com.example.nam.demo.LookAndChoose;

import android.app.Activity;
import android.app.Dialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.nam.demo.R;
import com.example.nam.demo.image.ImageDrawable;

import java.util.ArrayList;
import java.util.Random;

import cn.pedant.SweetAlert.SweetAlertDialog;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Nam on 7/12/2016.
 */
public class LookAndChoose extends Activity implements View.OnClickListener {
    int ques = 0;
    MediaPlayer mp3 = null;
    int demsocau = 0;
    ArrayList<String> list = new ArrayList<>();
    private ImageDrawable imageDrawable = new ImageDrawable();
    int testRandom[] = new int[5];
    int valuesProgressBar = 0;
    String text;
    TextView textView;
    ImageView LookAndChoose_img, LookAndChoose_imgview;
    FancyButton LookAndChoose_btn1, LookAndChoose_btn2, LookAndChoose_btn3, LookAndChoose_btn4;

    private int x[] = new int[5];
    private int y[] = new int[5];

    private int wrongAnswer = 5;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.lookandcheck);
        LookAndChoose_imgview = (ImageView) findViewById(R.id.LookAndChoose_imgview);

        LookAndChoose_btn1 = (FancyButton) findViewById(R.id.LookAndChoose_btn1);
        LookAndChoose_btn1.setOnClickListener(this);
        LookAndChoose_btn2 = (FancyButton) findViewById(R.id.LookAndChoose_btn2);
        LookAndChoose_btn2.setOnClickListener(this);
        LookAndChoose_btn3 = (FancyButton) findViewById(R.id.LookAndChoose_btn3);
        LookAndChoose_btn3.setOnClickListener(this);
        LookAndChoose_btn4 = (FancyButton) findViewById(R.id.LookAndChoose_btn4);
        LookAndChoose_btn4.setOnClickListener(this);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setProgress(0);
        progressBar.setMax(200);

        list = imageDrawable.list("list");
        chooseQues();
    }

    public void correct() {
        mp3 = MediaPlayer.create(getBaseContext(), R.raw.correct);
        mp3.start();
        mp3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }

            ;
        });
    }

    public void wrong() {
        mp3 = MediaPlayer.create(getBaseContext(), R.raw.wrong);
        mp3.start();
        mp3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }

            ;
        });
    }

    public void setXY(int i) {
        this.x[i] = imageDrawable.getX();
        this.y[i] = imageDrawable.getY();
    }


    public void chooseQues() {
        //lan1
        int t1 = imageDrawable.random();
        testRandom[0] = t1;
        setXY(0);
        String s = list.get(t1);
        String[] separated = s.split("_");

        LookAndChoose_btn1.setText(String.valueOf(separated[1].charAt(0)).toUpperCase() + separated[1].substring(1));
        Log.d("Error", "0");

        //lan2->4
        for (int i = 1; i < 4; i++) {
            int t2 = imageDrawable.random();
            while (!checkRandom(i, testRandom, t2)) {
                Log.d("Error1", "1");
                t2 = imageDrawable.random();
            }

            testRandom[i] = t2;
            Log.d("Error2", "2");

            String s1 = list.get(t2);
            String[] separated1 = s1.split("_");
            if (i == 1) {
                LookAndChoose_btn2.setText(String.valueOf(separated1[1].charAt(0)).toUpperCase() + separated1[1].substring(1));
            } else if (i == 2) {
                LookAndChoose_btn3.setText(String.valueOf(separated1[1].charAt(0)).toUpperCase() + separated1[1].substring(1));
            } else if (i == 3) {
                LookAndChoose_btn4.setText(String.valueOf(separated1[1].charAt(0)).toUpperCase() + separated1[1].substring(1));
            }
            setXY(i);
            LookAndChoose_imgview.setImageResource(R.drawable.pregunta);
        }
        //Random trong listQues;
        Random random2 = new Random();
        int dapanchinhxac = random2.nextInt(4);


        LookAndChoose_img = (ImageView) findViewById(R.id.LookAndChoose_img);

        LookAndChoose_img.setImageResource(getResources().getIdentifier(list.get(testRandom[dapanchinhxac]), "drawable", getPackageName()));
        testRandom[4] = testRandom[dapanchinhxac];
        x[4] = x[dapanchinhxac];
        y[4] = y[dapanchinhxac];


    }

    public void sleepThread(int s) {
        LookAndChoose_imgview.setImageResource((R.drawable.good));
        LookAndChoose_btn1.setEnabled(false);
        LookAndChoose_btn2.setEnabled(false);
        LookAndChoose_btn3.setEnabled(false);
        LookAndChoose_btn4.setEnabled(false);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // Actions to do after s seconds
                LookAndChoose_btn1.setEnabled(true);
                LookAndChoose_btn2.setEnabled(true);
                LookAndChoose_btn3.setEnabled(true);
                LookAndChoose_btn4.setEnabled(true);
                chooseQues();
                LookAndChoose_imgview.setImageResource((R.drawable.pregunta));
                valuesProgressBar += 10;
                progressBar.setProgress(valuesProgressBar);
                demsocau++;

            }
        }, s);

    }

    public boolean checkRandom(int i, int check[], int random) {
        for (int j = 0; j < i; j++)
            if (check[j] == random)
                return false;
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate ifut the process isa
        // killed and restarted.);
        savedInstanceState.putInt("MyInt1", testRandom[0]);
        savedInstanceState.putInt("MyInt2", testRandom[1]);
        savedInstanceState.putInt("MyInt3", testRandom[2]);
        savedInstanceState.putInt("MyInt4", testRandom[3]);
        savedInstanceState.putInt("MyInt5", testRandom[4]);
//


        savedInstanceState.putInt("ques", ques);
        savedInstanceState.putInt("value", valuesProgressBar);
        // etc.
        super.onSaveInstanceState(savedInstanceState);
    }

    //onRestoreInstanceState
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        testRandom[0] = savedInstanceState.getInt("MyInt1");
        testRandom[1] = savedInstanceState.getInt("MyInt2");
        testRandom[2] = savedInstanceState.getInt("MyInt3");
        testRandom[3] = savedInstanceState.getInt("MyInt4");
        testRandom[4] = savedInstanceState.getInt("MyInt5");

        valuesProgressBar = savedInstanceState.getInt("value");
        ques = savedInstanceState.getInt("ques");
        reQuest();
        progressBar.setProgress(valuesProgressBar);

    }

    public void reQuest() {
        String s = list.get(testRandom[0]);
        String[] separated = s.split("_");

        LookAndChoose_btn1.setText(String.valueOf(separated[1].charAt(0)).toUpperCase() + separated[1].substring(1));
        Log.d("Error", "0");

        //lan2->4
        for (int i = 0; i < 4; i++) {
            String s1 = list.get(testRandom[i]);
            String[] separated1 = s1.split("_");
            if (i == 1) {
                LookAndChoose_btn2.setText(String.valueOf(separated1[1].charAt(0)).toUpperCase() + separated1[1].substring(1));
            } else if (i == 2) {
                LookAndChoose_btn3.setText(String.valueOf(separated1[1].charAt(0)).toUpperCase() + separated1[1].substring(1));
            } else if (i == 3) {
                LookAndChoose_btn4.setText(String.valueOf(separated1[1].charAt(0)).toUpperCase() + separated1[1].substring(1));
            }
            LookAndChoose_imgview.setImageResource(R.drawable.pregunta);
        }


        LookAndChoose_img = (ImageView) findViewById(R.id.LookAndChoose_img);

        LookAndChoose_img.setImageResource(getResources().getIdentifier(list.get(testRandom[4]), "drawable", getPackageName()));
    }

    public void quest() {
        textView = (TextView) findViewById(R.id.LookAndChoose_text);
        text = (String) textView.getText();
        textView.setText(ques + "/20");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.LookAndChoose_btn1:
                if (testRandom[4] == testRandom[0]) {
                    correct();
                    YoYo.with(Techniques.Flash)
                            .duration(700)
                            .playOn(findViewById(R.id.LookAndChoose_btn1));
                    sleepThread(1000);
                    ques++;
                    quest();

                    imageDrawable.removeQues(testRandom[4], x[4], y[4]);

                } else {
                    wrong();
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .playOn(findViewById(R.id.LookAndChoose_btn1));
                    wrongAnswer--;
                    if(wrongAnswer == 0){
                        dialog_fail();
                    }
                }
                break;
            case R.id.LookAndChoose_btn2:
                if (testRandom[4] == testRandom[1]) {
                    correct();
                    YoYo.with(Techniques.Flash)
                            .duration(700)
                            .playOn(findViewById(R.id.LookAndChoose_btn2));
                    sleepThread(1000);
                    ques++;
                    quest();

                    imageDrawable.removeQues(testRandom[4], x[4], y[4]);
                } else {
                    wrong();
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .playOn(findViewById(R.id.LookAndChoose_btn2));

                    wrongAnswer--;
                    if(wrongAnswer == 0){
                        dialog_fail();
                    }
                }
                break;
            case R.id.LookAndChoose_btn3:
                if (testRandom[4] == testRandom[2]) {
                    correct();
                    YoYo.with(Techniques.Flash)
                            .duration(700)
                            .playOn(findViewById(R.id.LookAndChoose_btn3));
                    sleepThread(1000);
                    ques++;
                    quest();

                    imageDrawable.removeQues(testRandom[4], x[4], y[4]);


                } else {
                    wrong();
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .playOn(findViewById(R.id.LookAndChoose_btn3));
                    wrongAnswer--;
                    if(wrongAnswer == 0){
                        dialog_fail();
                    }
                }
                break;
            case R.id.LookAndChoose_btn4:
                if (testRandom[4] == testRandom[3]) {
                    correct();
                    YoYo.with(Techniques.Flash)
                            .duration(700)
                            .playOn(findViewById(R.id.LookAndChoose_btn4));
                    sleepThread(1000);
                    ques++;
                    quest();

                    imageDrawable.removeQues(testRandom[4], x[4], y[4]);
                } else {
                    wrong();
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .playOn(findViewById(R.id.LookAndChoose_btn4));
                    wrongAnswer--;
                    if(wrongAnswer == 0){
                        dialog_fail();
                    }
                }
                break;
        }
        if (demsocau == 19) {
            dialog_winner();
        }
    }
    private static void doKeepDialog(Dialog dialog){
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);
    }
    public void dialog_winner() {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE);
        sweetAlertDialog.setTitleText("Win");
        sweetAlertDialog.show();
        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                finish();
            }
        });
        doKeepDialog(sweetAlertDialog);
    }

    public void dialog_fail() {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE);
        sweetAlertDialog.setTitleText("Lose");
        sweetAlertDialog.show();
        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                finish();
            }
        });
        doKeepDialog(sweetAlertDialog);
    }
}
