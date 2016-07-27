package com.example.nam.demo.Start;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nam.demo.R;
import com.example.nam.demo.image.ImageDrawable;

import java.util.ArrayList;

/**
 * Created by Nam on 7/12/2016.
 */
public class start extends Activity {
    ArrayList<String> list = new ArrayList<>();
    private ImageDrawable imageDrawable = new ImageDrawable();
    String idCurrent;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycleview);
        list=imageDrawable.start();
        recyclerView = (RecyclerView) findViewById(R.id.listRecycleView);
        LinearLayoutManager layoutManager = new GridLayoutManager(getBaseContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new AdapterRecycle());
    }


    class AdapterRecycle extends RecyclerView.Adapter<AdapterRecycle.RecyclerViewHolder>{
        @Override
        public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final RecyclerViewHolder holder= new RecyclerViewHolder(getLayoutInflater().inflate(R.layout.start_solo,parent,false));
            holder.iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent t = new Intent(getBaseContext(),startview.class);
                    t.putExtra("key",imageDrawable.group(list.get(holder.getAdapterPosition())));
                    startActivity(t);

                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerViewHolder holder, int position) {
            holder.iv.setImageResource(getResources().getIdentifier(list.get(position) , "drawable", getPackageName()));
            String f = imageDrawable.group(list.get(position));
            holder.tv.setText(String.valueOf(f.charAt(0)).toUpperCase()+f.substring(1));

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
