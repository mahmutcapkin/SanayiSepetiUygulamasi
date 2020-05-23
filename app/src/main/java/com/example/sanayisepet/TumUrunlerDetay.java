package com.example.sanayisepet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.sanayisepet.Adapters.SliderAdapter;
import com.example.sanayisepet.Models.FavoriIslem;
import com.example.sanayisepet.Models.FavoriKontrol;
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
        urunDetayGoruntule();
        getResim();
        FavoriBtnText();
        FavoriBtnAksiyon();

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
    public void urunDetayGoruntule(){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Ürünler");
        progressDialog.setMessage("Ürün Detay Getiriliyor.. Lütfen bekleyin.");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Call<UrunDetayPojo> request = ManagerAll.getInstance().urunDetay(urunID);
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
                    otherId = response.body().getUyeid();
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

    public void FavoriBtnText()
    {
        Call<FavoriKontrol> request = ManagerAll.getInstance().favoritext(uyeId,urunID);
        request.enqueue(new Callback<FavoriKontrol>() {
            @Override
            public void onResponse(Call<FavoriKontrol> call, Response<FavoriKontrol> response) {

                if(response.body().isTruefalse())
                {
                    btnDetayFavoriAl.setText(response.body().getText());
                }else {
                    btnDetayFavoriAl.setText(response.body().getText());
                }
            }

            @Override
            public void onFailure(Call<FavoriKontrol> call, Throwable t) {

            }
        });

    }

    public void FavoriBtnAksiyon()
    {
        btnDetayFavoriAl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<FavoriIslem> request = ManagerAll.getInstance().favoriIslem(uyeId,urunID);
                request.enqueue(new Callback<FavoriIslem>() {
                    @Override
                    public void onResponse(Call<FavoriIslem> call, Response<FavoriIslem> response) {

                        if(response.body().isTruefalse())
                        {
                            Toast.makeText(getApplicationContext(),response.body().getText(),Toast.LENGTH_LONG).show();
                            FavoriBtnText();
                        }else
                        {
                            Toast.makeText(getApplicationContext(),response.body().getText(),Toast.LENGTH_LONG).show();
                            FavoriBtnText();
                        }
                    }

                    @Override
                    public void onFailure(Call<FavoriIslem> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"HATA",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        btnMesajGonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ChatActivity.class);
                DigerID.setOtherID(otherId);
                startActivity(intent);
            }
        });

    }











}
