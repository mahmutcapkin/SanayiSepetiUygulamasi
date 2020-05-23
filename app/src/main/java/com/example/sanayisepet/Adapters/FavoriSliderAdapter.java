package com.example.sanayisepet.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;

import com.example.sanayisepet.MainActivity;
import com.example.sanayisepet.Models.FavoriSliderPojo;
import com.example.sanayisepet.R;
import com.example.sanayisepet.TumUrunlerDetay;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriSliderAdapter  extends PagerAdapter {

    List<FavoriSliderPojo> list;
    Context context;
    LayoutInflater layoutInflater;
    AppCompatActivity activity;

    public FavoriSliderAdapter(List<FavoriSliderPojo> list, Context context, MainActivity activity) {
        this.list = list;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (RelativeLayout)o;
    }

    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.sliderlayout,container,false);

        ImageView img = (ImageView)view.findViewById(R.id.sliderImageView);

        Picasso.with(context).load("http://192.168.2.122/"+list.get(position).getResimsayiadres()+".jpg").into(img);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //resme tıklandıgında detaya gitsin..
                if(list.get(position).getUrunid() != null)
                {
                    Intent intent = new Intent(activity, TumUrunlerDetay.class);
                    intent.putExtra("urun_id", list.get(position).getUrunid());
                    activity.startActivity(intent);
                }
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

         container.removeView((View)object);
    }

}
