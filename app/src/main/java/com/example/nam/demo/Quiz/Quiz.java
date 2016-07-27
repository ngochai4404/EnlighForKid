package com.example.nam.demo.Quiz;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nam.demo.R;
import com.example.nam.demo.image.ImageDrawable;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import java.util.ArrayList;
import java.util.Random;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Nam on 7/11/2016.
 */
public class Quiz extends Activity {
    ArrayList<String> list = new ArrayList<>();
    ArrayList<String> alist = new ArrayList<>();
    MediaPlayer mp=null;
    ImageDrawable imageDrawable = new ImageDrawable();
    FancyButton newgame_recycleview;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        imageDrawable = new ImageDrawable();
        setContentView(R.layout.recyclerview_quiz);
        newgame_recycleview = (FancyButton) findViewById(R.id.newgame_recycleview);

        recyclerView = (RecyclerView) findViewById(R.id.listRecycleView);
        ranDom();

        LinearLayoutManager layoutManager = new GridLayoutManager(getBaseContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new AdapterRecycle());

        newgame_recycleview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.clear();
                ranDom();
                recyclerView.setAdapter(new AdapterRecycle());
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putStringArrayList("list",list);
        outState.putStringArrayList("allist",alist);
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        list=savedInstanceState.getStringArrayList("list");
        alist=savedInstanceState.getStringArrayList("allist");
        super.onRestoreInstanceState(savedInstanceState);
    }

    public void ranDom() {

        for (int i = 0; i < 12; i++) {
            String x = random();
            list.add(x);
        }
    }

    public int check1(ArrayList<String> alist, String s) {
        for (String i : alist) {
            if (s.equals(i)) {
                return 0;
            }

        }
        return 1;
    }

    public String random() {
        while (true) {
            String text = imageDrawable.random1();
            if (check1(alist, text) == 1 && check1(list, text) == 1) {
                return text;
            }
        }


    }

    public void correct(){
        mp=MediaPlayer.create(getBaseContext(),R.raw.wrong);
        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mp.release();
            }
        });
    }
    public void wrong(){
        mp=MediaPlayer.create(getBaseContext(),R.raw.correct);
        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mp.release();
            }
        });
    }
    class AdapterRecycle extends RecyclerView.Adapter<AdapterRecycle.RecyclerViewHolder> {


        @Override
        public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
            RecyclerViewHolder a = new RecyclerViewHolder(getLayoutInflater().inflate(R.layout.quiz_solo, parent, false));

            return a;
        }


        @Override
        public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
            if(check1(alist,list.get(position))==0){
                holder.iv.setImageResource(R.drawable.tick);
                holder.iv.setEnabled(false);
            }
            else
                holder.iv.setImageResource(getResources().getIdentifier(list.get(position), "drawable", getPackageName()));

            holder.iv.setOnClickListener(new View.OnClickListener() {

                @Override

                public void onClick(View view) {
                    final NiftyDialogBuilder dialog = NiftyDialogBuilder.getInstance(Quiz.this);
                    Random random=new Random();
                    int x=random.nextInt(4);

                    dialog.setCustomView(R.layout.dialog_quiz,getBaseContext());
                    dialog.withTitle("ANSWER ");
                    dialog.withTitleColor("#FFFFFF");
                    chooseEffect(dialog,x);
                    dialog.withDialogColor("#ECEFF1");

                    final ImageView imageView = (ImageView) dialog.findViewById(R.id.imageView);
                    imageView.setImageResource(getResources().getIdentifier(list.get(position), "drawable", getPackageName()));

                    final EditText editText = (EditText) dialog.findViewById(R.id.edit_dialog);

                    Button check = (Button) dialog.findViewById(R.id.check_dialog);
                    Button cancel = (Button) dialog.findViewById(R.id.cancel_dialog);
                    check.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String text = editText.getText().toString();
                            if (text.equals("")) {
                                Toast.makeText(getBaseContext(), "You must answer!", Toast.LENGTH_LONG).show();
                                editText.requestFocus();
                                return;
                            }

                            String answer = imageDrawable.getName(list.get(position)), s;
                            s = answer.toLowerCase();

                            text = text.toLowerCase();
                            if (s.equals(text)) {

                                holder.iv.setImageResource(R.drawable.tick);
                                holder.iv.setEnabled(false);
                                correct();
                                Toast.makeText(getBaseContext(), "right", Toast.LENGTH_LONG).show();
                                alist.add(list.get(position));
                                dialog.dismiss();

                            }
                            else

                            {
                                wrong();
                                Toast.makeText(getBaseContext(), s, Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                            }


                        }
                    });

                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }
            });

        }
        public void chooseEffect(NiftyDialogBuilder dialog,int x){
           // dialog.withEffect(Effectstype.Shake);
//            dialog.withEffect(Effectstype.Fliph);
//            return ;
            switch (x){
                case 0: dialog.withEffect(Effectstype.RotateBottom);
                    break;
                case 1:dialog.withEffect(Effectstype.Fadein);
                    break;
                case 2:
                    dialog.withEffect(Effectstype.Fall);
                    break;
                case 3:
                    dialog.withEffect(Effectstype.Newspager);
                    break;
            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class RecyclerViewHolder extends RecyclerView.ViewHolder {
            ImageView iv;

            public RecyclerViewHolder(View itemView) {
                super(itemView);
                iv = (ImageView) itemView.findViewById(R.id.quiz_image);
            }

        }
    }
}
