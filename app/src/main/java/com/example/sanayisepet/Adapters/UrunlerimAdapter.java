package com.example.sanayisepet.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sanayisepet.AlertDialogClass;
import com.example.sanayisepet.Models.UrunlerimPojo;
import com.example.sanayisepet.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UrunlerimAdapter extends BaseAdapter {
    List<UrunlerimPojo> list;
    Context context;
    Activity activity;
    String uyeid, urunid;
    AlertDialogClass alertDialogClass;

    public UrunlerimAdapter(List<UrunlerimPojo> list, Context context,Activity activity)
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
      //  LinearLayout linearLayout;
        resim = convertView.findViewById(R.id.ivUrunlerResim);
        baslik = convertView.findViewById(R.id.tvUrunlerimBaslik);
        fiyat = convertView.findViewById(R.id.tvUrunlerimFiyat);
        baslik.setText(list.get(position).getBaslik());
        fiyat.setText(list.get(position).getFiyat());
        urunid = list.get(position).getUrunid();
        uyeid = list.get(position).getUyeid();
    //    linearLayout = convertView.findViewById(R.id.urunLinearLayout);
     /*   linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialogClass = new AlertDialogClass();
                alertDialogClass.UrunlerimAlertDialog(activity,urunid);
            }
        });*/

        Picasso.with(context).load("http://192.168.2.122/"+list.get(position).getResimsayiadres()+".jpg").into(resim);

        return  convertView;
    }
}
