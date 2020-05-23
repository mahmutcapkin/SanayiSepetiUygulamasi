package com.example.sanayisepet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sanayisepet.Models.UrunSatPojo;
import com.example.sanayisepet.Models.UrunSonucPojo;
import com.example.sanayisepet.RestApi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdresBilgileriActivity extends AppCompatActivity {

    Button btnAdresBilgiIleri,btnAdresBilgiGeri;
    EditText etNo,etSokak,etIlceBilgi,etMahalleBilgi;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adres_bilgileri);
        tanimlama();
    }


    public void tanimlama()
    {

        sharedPreferences = getApplicationContext().getSharedPreferences("giris",0);
        UrunSatPojo.setKullanici_id(sharedPreferences.getString("uye_id",null));

        etIlceBilgi = findViewById(R.id.etIlceBilgi);
        etMahalleBilgi = findViewById(R.id.etMahalle);
        etSokak = findViewById(R.id.etSokak);
        etNo = findViewById(R.id.etNo);
        btnAdresBilgiIleri = findViewById(R.id.btnAdresIleri);
        btnAdresBilgiGeri = findViewById(R.id.btnAdresGeri);

        etIlceBilgi.setText(UrunSatPojo.getIlce());
        etMahalleBilgi.setText(UrunSatPojo.getMahalle());
        etSokak.setText(UrunSatPojo.getSokak());
        etNo.setText(UrunSatPojo.getBinano());


        btnAdresBilgiIleri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!etIlceBilgi.getText().toString().equals("") && !etMahalleBilgi.getText().toString().equals("") && !etSokak.getText().toString().equals("") && !etNo.getText().toString().equals(""))
                {
                    UrunSatPojo.setIlce(etIlceBilgi.getText().toString());
                    UrunSatPojo.setMahalle(etMahalleBilgi.getText().toString());
                    UrunSatPojo.setSokak(etSokak.getText().toString());
                    UrunSatPojo.setBinano(etNo.getText().toString());

                    UrunYayinla(UrunSatPojo.getKullanici_id(),UrunSatPojo.getIlce(),UrunSatPojo.getMahalle(),UrunSatPojo.getSokak(),UrunSatPojo.getBinano(),UrunSatPojo.getBaslik(),UrunSatPojo.getAciklama(),UrunSatPojo.getMarka(),UrunSatPojo.getFiyat(),UrunSatPojo.getUretimyeri());

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Tüm bilgileri giriniz", Toast.LENGTH_LONG).show();
                }

            }
        });
        btnAdresBilgiGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdresBilgileriActivity.this,UrunBilgileriActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in_ters,R.anim.anim_out_ters);
                finish();
            }
        });
    }

        public void UrunYayinla(String kid, String ilce, String mahalle, String sokak, String binano, String baslik, String aciklama, String marka, String fiyat, String uretimyeri)
        {

            Call<UrunSonucPojo> request = ManagerAll.getInstance().urunsonuc(kid,ilce,mahalle,sokak,binano,baslik,aciklama,marka,fiyat,uretimyeri);
            request.enqueue(new Callback<UrunSonucPojo>() {
                @Override
                public void onResponse(Call<UrunSonucPojo> call, Response<UrunSonucPojo> response) {

                    if(response.body().isTruefalse())
                    {
                        Toast.makeText(getApplicationContext(),"Ürün Başarıyla Satışa Koyuldu",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(AdresBilgileriActivity.this,UrunResimler.class);
                        intent.putExtra("urun_id",response.body().getUrunid());
                        intent.putExtra("uye_id",String.valueOf(response.body().getUyeid()));
                        startActivity(intent);
                        overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                     Log.i("test",response.body().getUrunid() + "       /// " + response.body().getUyeid());

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Ürün Satışa Koyulamadı",Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(Call<UrunSonucPojo> call, Throwable t) {

                }
            });

        }



}
