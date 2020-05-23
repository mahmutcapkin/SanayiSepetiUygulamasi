package com.example.sanayisepet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sanayisepet.Adapters.TumUrunlerAdapter;
import com.example.sanayisepet.Models.TumUrunlerPojo;
import com.example.sanayisepet.RestApi.ManagerAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TumUrunler extends AppCompatActivity {


    ListView listView;
    TumUrunlerAdapter adapter;
    List<TumUrunlerPojo> TumUrunlerPojolist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tum_urunler);

        listView = (ListView)findViewById(R.id.lvTumUrunler);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent  = new Intent(TumUrunler.this,TumUrunlerDetay.class);
                intent.putExtra("urun_id",TumUrunlerPojolist.get(position).getUrunid());   //""+TumUrunlerPojolist.get(position).getUrunid()
                startActivity(intent);
            }
        });
        TumUrunleriGoruntule();

    }


    private void TumUrunleriGoruntule(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Ürünler");
        progressDialog.setMessage("Ürünler yükleniyor..Lütfen bekleyiniz..");
        progressDialog.setCancelable(false);
        progressDialog.show();
        Call<List<TumUrunlerPojo>> request = ManagerAll.getInstance().tumurunler();
        request.enqueue(new Callback<List<TumUrunlerPojo>>() {
            @Override
            public void onResponse(Call<List<TumUrunlerPojo>> call, Response<List<TumUrunlerPojo>> response) {
                if(response.isSuccessful())
                {
                    if(response.body().get(0).isTruefalse())
                    {
                        TumUrunlerPojolist = response.body();
                        adapter = new TumUrunlerAdapter(TumUrunlerPojolist,getApplicationContext(),TumUrunler.this);
                        listView.setAdapter(adapter);
                        progressDialog.cancel();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<TumUrunlerPojo>> call, Throwable t) {

            }
        });

    }

}
