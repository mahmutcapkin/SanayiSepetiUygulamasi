package com.example.sanayisepet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sanayisepet.Models.BilgilerPojo;
import com.example.sanayisepet.RestApi.ManagerAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MesajlarAdapter  extends BaseAdapter {

    List<String> otherIdList;
    String userID;
    Context context;
    Activity activity;

    public MesajlarAdapter(List<String> otherIdList, String userID, Context context,Activity activity) {
        this.otherIdList = otherIdList;
        this.userID = userID;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return otherIdList.size();
    }

    @Override
    public Object getItem(int position) {
        return otherIdList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.other,parent,false);
        TextView textView;
        textView = convertView.findViewById(R.id.otherText);
        istekAt(otherIdList.get(position).toString(), textView);
        LinearLayout linearLayout = convertView.findViewById(R.id.linearmesaj);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ıntent = new Intent(activity,ChatActivity.class);
                DigerID.setOtherID(otherIdList.get(position));
                activity.startActivity(ıntent);
            }
        });

        return convertView;
    }

    public void istekAt(String uye_id, final TextView textView) {


        Call<BilgilerPojo> request = ManagerAll.getInstance().BilgiGetir(uye_id);
        request.enqueue(new Callback<BilgilerPojo>() {
            @Override
            public void onResponse(Call<BilgilerPojo> call, Response<BilgilerPojo> response) {
                if (response.isSuccessful()) {
                    textView.setText(response.body().getKadi());
                }
            }

            @Override
            public void onFailure(Call<BilgilerPojo> call, Throwable t) {

            }
        });
    }

}
