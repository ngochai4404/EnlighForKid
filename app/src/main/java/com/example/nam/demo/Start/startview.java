package com.example.nam.demo.Start;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.nam.demo.R;
import com.example.nam.demo.image.ImageDrawable;

import java.util.ArrayList;

/**
 * Created by Nam on 7/13/2016.
 */
public class startview extends Activity {
    ArrayList<String> list = new ArrayList<>();
    private ImageDrawable imageDrawable = new ImageDrawable();
    RecyclerView recyclerView;
    MediaPlayer mp3= null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycleview);

        String f =getIntent().getStringExtra("key");
        list=imageDrawable.list(f);
        recyclerView = (RecyclerView) findViewById(R.id.listRecycleView);
        LinearLayoutManager layoutManager = new GridLayoutManager(getBaseContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new AdapterRecycle());
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    class AdapterRecycle extends RecyclerView.Adapter<AdapterRecycle.RecyclerViewHolder>{
        @Override
        public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final RecyclerViewHolder holder= new RecyclerViewHolder(getLayoutInflater().inflate(R.layout.start_solo,parent,false));
            holder.iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    YoYo.with(Techniques.Pulse)
                            .duration(500)
                            .playOn(holder.iv);
                    YoYo.with(Techniques.Pulse)
                            .duration(500)
                            .playOn(holder.tv);
                    mp3 = MediaPlayer.create(getBaseContext(),getResources().getIdentifier(list.get(holder.getAdapterPosition()) , "raw", getPackageName()) );
                    mp3.start();
                    mp3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                        };
                    });


                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerViewHolder holder, int position) {
            holder.iv.setImageResource(getResources().getIdentifier(list.get(position) , "drawable", getPackageName()));
            holder.tv.setText(imageDrawable.getName(list.get(position)));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class RecyclerViewHolder extends RecyclerView.ViewHolder {
            ImageView iv;
            TextView tv;
            public RecyclerViewHolder(View itemView) {
                super(itemView);
                iv = (ImageView) itemView.findViewById(R.id.start_image);
                tv = (TextView) itemView.findViewById(R.id.start_textview);
            }

        }
    }
}
