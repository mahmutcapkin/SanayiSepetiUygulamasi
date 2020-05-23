package com.example.sanayisepet.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sanayisepet.AlertDialogClass;
import com.example.sanayisepet.Models.UrunlerimPojo;
import com.example.sanayisepet.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UrunlerimAdapter extends BaseAdapter {
    List<UrunlerimPojo> list;
    Context context;
    AppCompatActivity activity;
    String uyeid, urunid;
    AlertDialogClass alertDialogClass;

    public UrunlerimAdapter(List<UrunlerimPojo> list, Context context, AppCompatActivity activity)
    {
        this.list = list;
        this.context = context;
        this.activity = activity;
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

        convertView = LayoutInflater.from(context).inflate(R.layout.urunlerimlayout,parent,false);
        ImageView resim;
        TextView baslik,fiyat;
        resim = convertView.findViewById(R.id.ivUrunlerResim);
        baslik = convertView.findViewById(R.id.tvUrunlerimBaslik);
        fiyat = convertView.findViewById(R.id.tvUrunlerimFiyat);
        baslik.setText(list.get(position).getBaslik());
        fiyat.setText(list.get(position).getFiyat());
        urunid = list.get(position).getUrunid();
        uyeid = list.get(position).getUyeid();


        Picasso.with(context).load("http://192.168.2.122/"+list.get(position).getResimsayiadres()+".jpg").into(resim);

        return  convertView;
    }
}
