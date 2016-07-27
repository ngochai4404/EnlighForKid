package com.example.nam.demo.ListenAndGuess;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.nam.demo.R;
import com.example.nam.demo.image.ImageDrawable;

import java.util.ArrayList;
import java.util.Random;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Nam on 7/13/2016.
 */
public class ListenAndGuess extends Activity implements View.OnClickListener {
    private ImageView listen_and_guess_iv1;
    private ImageView listen_and_guess_iv2;
    private ImageView listen_and_guess_iv3;
    private ImageView listen_and_guess_iv4;
    private ProgressBar progressBar;
    private boolean screenRotate = true;
    private ImageView listen_and_guess_imgview;
    private ImageButton listen_and_guess_imagebutton;
    private MediaPlayer mp3 = null;
    int ques = 0, demsocau = 1, trueQues;
    private ImageDrawable imageDrawable = new ImageDrawable();
    private int testRandom[] = new int[5];
    private int valueProgressBar = 0;
    private String text;
    private TextView textView;
    private ArrayList<String> list = new ArrayList<>();
    private int x[] = new int[5];
    private int y[] = new int[5];
    private int wrongAnswer = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listenandguess);
        listen_and_guess_iv1 = (ImageView) findViewById(R.id.listen_and_guess_iv1);
        listen_and_guess_iv1.setOnClickListener(this);

        listen_and_guess_iv2 = (ImageView) findViewById(R.id.listen_and_guess_iv2);
        listen_and_guess_iv2.setOnClickListener(this);

        listen_and_guess_iv3 = (ImageView) findViewById(R.id.listen_and_guess_iv3);
        listen_and_guess_iv3.setOnClickListener(this);

        listen_and_guess_iv4 = (ImageView) findViewById(R.id.listen_and_guess_iv4);
        listen_and_guess_iv4.setOnClickListener(this);

        listen_and_guess_imagebutton = (ImageButton) findViewById(R.id.listen_and_guess_imagebutton);
        listen_and_guess_imagebutton.setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.listen_and_guess_progressBar);
        progressBar.setProgress(0);
        progressBar.setMax(200);

        list = imageDrawable.list("list");
        if (savedInstanceState == null) {
            chooseQues();
        }

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

    public void playMp3This() {
        mp3 = MediaPlayer.create(getBaseContext(), getResources().getIdentifier(list.get(testRandom[4]), "raw", getPackageName()));
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
        int t1 = imageDrawable.random();
        testRandom[0] = t1;
        String s = list.get(t1);
        listen_and_guess_iv1.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), getResources().getIdentifier(s, "drawable", getPackageName())));

        setXY(0);

        for (int i = 1; i < 4; i++) {
            int t2 = imageDrawable.random();
            while (!checkRandom(i, testRandom, t2)) {
                t2 = imageDrawable.random();
            }
            testRandom[i] = t2;
            String s1 = list.get(t2);
            String separated1[] = s1.split("_");
            switch (i) {
                case 1:
                    listen_and_guess_iv2.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), getResources().getIdentifier(s1, "drawable", getPackageName())));
                    break;
                case 2:
                    listen_and_guess_iv3.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), getResources().getIdentifier(s1, "drawable", getPackageName())));
                    break;
                case 3:
                    listen_and_guess_iv4.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), getResources().getIdentifier(s1, "drawable", getPackageName())));
                    break;
            }
            setXY(i);
//            listen_and_guess_imgview.setImageResource(R.drawable.pregunta);

        }

        Random random2 = new Random();
        trueQues = random2.nextInt(4);
        testRandom[4] = testRandom[trueQues];
        this.x[4] = x[trueQues];
        y[4] = y[trueQues];
        playMp3This();
    }

    public boolean checkRandom(int i, int check[], int random) {
        for (int j = 0; j < i; j++)
            if (check[j] == random)
                return false;
        return true;
    }

    public void sleepThread(int s) {
        if (ques >= 20) {
            dialog_winner();
        }
        else {
            screenRotate = false;
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    if(screenRotate==false) {
                        valueProgressBar += 10;
                        progressBar.setProgress(valueProgressBar);
                        chooseQues();
                        ques++;
                        demsocau++;
                        quest();
                        //imageDrawable.removeQues(testRandom[4], x[4], y[4]);
                        screenRotate=true;

                    }
                }
            }, s);
        }

    }

    public void quest() {
        textView = (TextView) findViewById(R.id.listen_and_guess_tvprogress);
        text = (String) textView.getText();
        textView.setText(ques + "/20");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.listen_and_guess_iv1:
                if (testRandom[0] == testRandom[4]) {
                    if(ques>=20){

                        dialog_winner();
                        return;
                    }
                    correct();
                    YoYo.with(Techniques.Flash)
                            .duration(700)
                            .playOn(findViewById(R.id.listen_and_guess_iv1));
                    sleepThread(1000);

                } else {
                    wrong();
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .playOn(findViewById(R.id.listen_and_guess_iv1));
                    wrongAnswer--;
                    if (wrongAnswer == 0) {
                        dialog_fail();
                    }
                }
                break;
            case R.id.listen_and_guess_iv2:
                if (testRandom[1] == testRandom[4]) {
                    if(ques>=20){
                        dialog_winner();
                        return;
                    }
                    correct();
                    YoYo.with(Techniques.Flash)
                            .duration(700)
                            .playOn(findViewById(R.id.listen_and_guess_iv2));
                    sleepThread(1000);

                } else {
                    wrong();
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .playOn(findViewById(R.id.listen_and_guess_iv2));
                    wrongAnswer--;
                    if (wrongAnswer == 0) {
                        dialog_fail();
                    }
                }
                break;
            case R.id.listen_and_guess_iv3:
                if (testRandom[2] == testRandom[4]) {
                    if(ques>=20){
                        dialog_winner();
                        return;
                    }
                    correct();
                    YoYo.with(Techniques.Flash)
                            .duration(700)
                            .playOn(findViewById(R.id.listen_and_guess_iv3));
                    sleepThread(1000);

                } else {
                    wrong();
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .playOn(findViewById(R.id.listen_and_guess_iv3));
                    wrongAnswer--;
                    if (wrongAnswer == 0) {
                        dialog_fail();
                    }
                }
                break;
            case R.id.listen_and_guess_iv4:
                if (testRandom[3] == testRandom[4]) {
                    if(ques>=20){
                        dialog_winner();
                        return;
                    }
                    correct();
                    YoYo.with(Techniques.Flash)
                            .duration(700)
                            .playOn(findViewById(R.id.listen_and_guess_iv4));
                    sleepThread(1000);

                } else {
                    wrong();
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .playOn(findViewById(R.id.listen_and_guess_iv4));
                    wrongAnswer--;
                    if (wrongAnswer == 0) {
                        dialog_fail();
                    }
                }
                break;
            case R.id.listen_and_guess_imagebutton:
                YoYo.with(Techniques.FadeIn)
                        .duration(300)
                        .playOn(findViewById(R.id.listen_and_guess_imagebutton));
                playMp3This();
                break;
        }

    }

    public void dialog_winner() {
//        final Context context = this;
//        final Dialog dialog = new Dialog(context);
//        dialog.setContentView(R.layout.dialog);
//        dialog.setTitle("Chúc mừng bạn đã vượt qua 20 câu hỏi!");
//
//        // set the custom dialog components - text, image and button
//        TextView text = (TextView) dialog.findViewById(R.id.text);
//        text.setText("WINNER!");
//
//        ImageView image = (ImageView) dialog.findViewById(R.id.image);
//        image.setImageResource(R.drawable.cup);
//
//        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
//        // if button is clicked, close the custom dialog
//        dialog.show();
//        dialogButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });
//        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//            @Override
//            public void onDismiss(DialogInterface dialog) {
//                finish();
//            }
//        });
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

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate ifut the process isa
        // killed and restarted.);
        if (screenRotate == false&&ques<19) {
            screenRotate = true;
            valueProgressBar += 10;
            progressBar.setProgress(valueProgressBar);
            chooseQues();
            ques++;
            demsocau++;
            quest();
       //     imageDrawable.removeQues(testRandom[4], x[4], y[4]);

        }
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("MyInt1", testRandom[0]);
        savedInstanceState.putInt("MyInt2", testRandom[1]);
        savedInstanceState.putInt("MyInt3", testRandom[2]);
        savedInstanceState.putInt("MyInt4", testRandom[3]);
        savedInstanceState.putInt("MyInt5", testRandom[4]);

        savedInstanceState.putInt("ques", ques);
        savedInstanceState.putInt("value", valueProgressBar);


}

    //onRestoreInstanceState
    @Override
    public void onRestoreInstanceState( Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);
        testRandom[0] = savedInstanceState.getInt("MyInt1");
        testRandom[1] = savedInstanceState.getInt("MyInt2");
        testRandom[2] = savedInstanceState.getInt("MyInt3");
        testRandom[3] = savedInstanceState.getInt("MyInt4");
        testRandom[4] = savedInstanceState.getInt("MyInt5");

        valueProgressBar = savedInstanceState.getInt("value");
        ques = savedInstanceState.getInt("ques");
        reQuest();
        quest();
        progressBar.setProgress(valueProgressBar);
        if(ques>=20){
            dialog_winner();
            return;
        }

    }

    public void reQuest() {
        listen_and_guess_iv1.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), getResources().getIdentifier(list.get(testRandom[0]), "drawable", getPackageName())));
        listen_and_guess_iv2.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), getResources().getIdentifier(list.get(testRandom[1]), "drawable", getPackageName())));
        listen_and_guess_iv3.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), getResources().getIdentifier(list.get(testRandom[2]), "drawable", getPackageName())));
        listen_and_guess_iv4.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), getResources().getIdentifier(list.get(testRandom[3]), "drawable", getPackageName())));


    }

    public void toasttrue() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toasttrue, null);

        Toast toast = new Toast(getApplicationContext());
        //set vi tri hien thi cho toasttrue
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        //Thoi gian hien thi cho toasttrue
        toast.setDuration(Toast.LENGTH_SHORT);
        //set layout toasttruetrue.xml cho Toast
        toast.setView(layout);
        toast.show();
    }

    private static void doKeepDialog(Dialog dialog) {
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);
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
