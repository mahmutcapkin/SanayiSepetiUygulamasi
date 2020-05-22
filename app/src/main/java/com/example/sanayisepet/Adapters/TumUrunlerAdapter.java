package com.example.sanayisepet.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sanayisepet.Models.TumUrunlerPojo;
import com.example.sanayisepet.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TumUrunlerAdapter extends BaseAdapter {

    List<TumUrunlerPojo> list;
    Context context;
    Activity activity;
    public TumUrunlerAdapter(List<TumUrunlerPojo> list, Context context, Activity activity)
    {
        this.list = list;
        this.context = context;
        this.activity= activity;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.tumurunlerlayout,parent,false);
        ImageView resim;
        TextView baslik,fiyat,adres;
        resim = convertView.findViewById(R.id.ivTumUrunlerResim);
        baslik = convertView.findViewById(R.id.tvTumUrunlerimBaslik);
        adres = convertView.findViewById(R.id.tvTumUrunlerimAdres);
        fiyat = convertView.findViewById(R.id.tvTumUrunlerimFiyat);

        baslik.setText(list.get(position).getBaslik()+"-"+list.get(position).getUrunid());
        fiyat.setText(list.get(position).getFiyat());
        adres.setText(list.get(position).getIlce()+" / "+list.get(position).getMahalle()+" / "+list.get(position).getSokak()+" / "+list.get(position).getBinano());

        Picasso.with(context).load("http://192.168.2.122/"+list.get(position).getResimsayiadres()+".jpg").into(resim);
        return convertView;
    }
}
