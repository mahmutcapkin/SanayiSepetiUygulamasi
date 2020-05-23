package com.example.sanayisepet.Adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.sanayisepet.Models.SliderPojo;
import com.example.sanayisepet.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SliderAdapter extends PagerAdapter {
    List<SliderPojo> Sliderlist;
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(List<SliderPojo> list, Context context) {
        this.Sliderlist = list;
        this.context = context;
    }



    @Override
    public int getCount() {
        return Sliderlist.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return (view==(RelativeLayout)o);
    }


    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.sliderlayout,container,false);

        ImageView resim = view.findViewById(R.id.sliderImageView);

        Picasso.with(context).load("http://192.168.2.122/"+Sliderlist.get(position).getResimsayiadres()+".jpg").into(resim);

        container.addView(view);
        return view;
    }



}
