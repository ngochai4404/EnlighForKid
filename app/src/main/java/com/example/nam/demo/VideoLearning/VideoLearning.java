package com.example.nam.demo.VideoLearning;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nam.demo.R;

/**
 * Created by Nam on 7/12/2016.
 */
public class VideoLearning extends Activity {
    RecyclerView recyclerView;
    String linkVideo[]={
            "https://www.youtube.com/watch?v=8lD2CWsxHw4&index=30&list=RDFJ9rr3DL-Rs",
            "https://www.youtube.com/watch?v=voCrQ1ebEdU",
            "https://www.youtube.com/watch?v=vLeQJL-28K0",
            "https://www.youtube.com/watch?v=Jn_5RjLiGHY",
            "https://www.youtube.com/watch?v=2gftgtedvI0",
            "https://www.youtube.com/watch?v=1iKJz02kUik",
            "https://www.youtube.com/watch?v=HSKvuWsXtjI&list=RDHSKvuWsXtjI"
    };
    int linkImage[]={
            R.drawable.anh1,
            R.drawable.anh2,
            R.drawable.anh3,
            R.drawable.anh4,
            R.drawable.anh5,
            R.drawable.anh6,
            R.drawable.anh7
    };
    String nameVideo[]={
        "ABC Song",
        "Number song",
        "Shapes Song",
        "Animal Song",
        "Color Song",
        "If You're Happy and You Know It",
        "Happy birthday"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycleview);

        recyclerView = (RecyclerView) findViewById(R.id.listRecycleView);
        int orientation = this.getResources().getConfiguration().orientation;
        RecyclerView.LayoutManager layoutManager;
        if(orientation== Configuration.ORIENTATION_PORTRAIT){
            layoutManager = new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL,false);

        }
        else {
            layoutManager = new GridLayoutManager(getBaseContext(),2);
        }
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new RecycleAdapter());

    }
//    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=cxLG2wtE7TM")));
    class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.RecycleAdapterHolder>{

        @Override
        public RecycleAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final RecycleAdapterHolder holder= new RecycleAdapterHolder(getLayoutInflater().inflate(R.layout.video_solo,parent,false));
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(linkVideo[holder.getAdapterPosition()])));

                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(RecycleAdapterHolder holder, int position) {

            holder.iv.setImageResource(linkImage[position]);
            holder.tv.setText(nameVideo[position]);
        }

        @Override
        public int getItemCount() {
            return linkImage.length;
        }

        class RecycleAdapterHolder extends RecyclerView.ViewHolder{
            ImageView iv;
            TextView tv;
            LinearLayout layout;
            public RecycleAdapterHolder(View itemView) {
                super(itemView);
                iv = (ImageView) itemView.findViewById(R.id.video_image);
                tv = (TextView) itemView.findViewById(R.id.video_text);
                layout =(LinearLayout) itemView.findViewById(R.id.video_layout);
            }
        }
    }


}
