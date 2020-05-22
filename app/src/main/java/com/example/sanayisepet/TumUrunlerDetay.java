package com.example.sanayisepet;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sanayisepet.Adapters.SliderAdapter;
import com.example.sanayisepet.Models.SliderPojo;
import com.example.sanayisepet.Models.UrunDetayPojo;
import com.example.sanayisepet.RestApi.ManagerAll;

import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TumUrunlerDetay extends AppCompatActivity {

    private TextView urunDetayIlce, urunDetayMahalle, urunDetaySokak, urunDetayNo,
            urunDetayBaslik, urunDetayAciklama, urunDetayMarka, urunDetayFiyat, urunDetayUretimYer;
    private Button btnMesajGonder, btnDetayFavoriAl;
    private ViewPager urunDetayViewPager;
    CircleIndicator urunDetaySlider;

    List<SliderPojo> list;
    SliderAdapter sliderAdapter;
    String urunID;
    SharedPreferences sharedPreferences;
    String uyeId,otherId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tum_urunler_detay);

        sharedPreferences = this.getSharedPreferences("giris",0);
        uyeId = sharedPreferences.getString("uye_id",null);

        Bundle bundle = getIntent().getExtras();
        urunID = bundle.getString("urun_id");

        tanimla();
        urunDetayGoruntule(urunID);
        getResim();

    }

    public void tanimla()
    {
        urunDetayIlce = findViewById(R.id.urunDetayIlce);
        urunDetayMahalle = findViewById(R.id.urunDetayMahalle);
        urunDetaySokak = findViewById(R.id.urunDetaySokak);
        urunDetayNo = findViewById(R.id.urunDetayNo);
        urunDetayBaslik = findViewById(R.id.urunDetayBaslik);
        urunDetayAciklama = findViewById(R.id.urunDetayAciklama);
        urunDetayMarka = findViewById(R.id.urunDetayMarka);
        urunDetayFiyat = findViewById(R.id.urunDetayFiyat);
        urunDetayUretimYer = findViewById(R.id.urunDetayUretimYer);

        btnMesajGonder = findViewById(R.id.btnMesajGonder);
        btnDetayFavoriAl = findViewById(R.id.btnDetayFavoriAl);

        urunDetayViewPager = findViewById(R.id.urunDetayViewPager);
        urunDetaySlider = findViewById(R.id.urunDetaySlider);

    }
    public void urunDetayGoruntule(String urunid){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Ürünler");
        progressDialog.setMessage("Ürün Detay Getiriliyor.. Lütfen bekleyin.");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Call<UrunDetayPojo> request = ManagerAll.getInstance().urunDetay(urunid);
        request.enqueue(new Callback<UrunDetayPojo>() {
            @Override
            public void onResponse(Call<UrunDetayPojo> call, Response<UrunDetayPojo> response) {
                progressDialog.cancel();

                if(response.isSuccessful()){
                    urunDetayIlce.setText(response.body().getIlce());
                    urunDetayMahalle.setText(response.body().getMahalle());
                    urunDetaySokak.setText(response.body().getSokak());
                    urunDetayNo.setText(response.body().getBinano());
                    urunDetayBaslik.setText(response.body().getBaslik());
                    urunDetayAciklama.setText(response.body().getAciklama());
                    urunDetayMarka.setText(response.body().getMarka());
                    urunDetayFiyat.setText(response.body().getFiyat());
                    urunDetayUretimYer.setText(response.body().getUretimyeri());

                }
            }

            @Override
            public void onFailure(Call<UrunDetayPojo> call, Throwable t) {

                progressDialog.cancel();
                Toast.makeText(getApplicationContext(),"HATA",Toast.LENGTH_LONG).show();
            }
        });
    }


    public void getResim(){
        Call<List<SliderPojo>> request = ManagerAll.getInstance().urunDetayResim(urunID);
        request.enqueue(new Callback<List<SliderPojo>>() {
            @Override
            public void onResponse(Call<List<SliderPojo>> call, Response<List<SliderPojo>> response) {

                list = response.body();
                sliderAdapter = new SliderAdapter(list,getApplicationContext());
                urunDetayViewPager.setAdapter(sliderAdapter);

                urunDetaySlider.setViewPager(urunDetayViewPager);
                urunDetaySlider.bringToFront();

            }

            @Override
            public void onFailure(Call<List<SliderPojo>> call, Throwable t) {
            }
        });
    }











}
//urunDetayIlce, urunDetayMahalle, urunDetaySokak, urunDetayNo, urunDetayBaslik, urunDetayAciklama, urunDetayMarka, urunDetayFiyat, urunDetayUretimYer
//btnMesajGonder, btnDetayFavoriAl , urunDetayViewPager, urunDetaySlider,