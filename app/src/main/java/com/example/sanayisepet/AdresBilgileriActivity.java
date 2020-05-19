package com.example.sanayisepet;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sanayisepet.Models.UrunSatPojo;
import com.example.sanayisepet.Models.UrunSonucPojo;
import com.example.sanayisepet.RestApi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdresBilgileriActivity extends AppCompatActivity {

    Button btnAdresBilgiIleri,btnAdresBilgiGeri;
    EditText etNo,etSokak,etIlceBilgi,etMahalleBilgi;

    private View mProgressBar;
    private View swAdres;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adres_bilgileri);
        tanimlama();
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            swAdres.setVisibility(show ? View.GONE : View.VISIBLE);
            swAdres.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    swAdres.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressBar.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
            swAdres.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    public void tanimlama()
    {

        sharedPreferences = getApplicationContext().getSharedPreferences("giris",0);
        UrunSatPojo.setKullanici_id(sharedPreferences.getString("uye_id",null));

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        swAdres = (View) findViewById(R.id.swAdres);

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
                    Toast.makeText(getApplicationContext(),"btn if içindeyiz", Toast.LENGTH_LONG).show();

                    UrunSatPojo.setIlce(etIlceBilgi.getText().toString());
                    UrunSatPojo.setMahalle(etMahalleBilgi.getText().toString());
                    UrunSatPojo.setSokak(etSokak.getText().toString());
                    UrunSatPojo.setBinano(etNo.getText().toString());

                    UrunYayinla(UrunSatPojo.getKullanici_id(),UrunSatPojo.getIlce(),UrunSatPojo.getMahalle(),UrunSatPojo.getSokak(),UrunSatPojo.getBinano(),UrunSatPojo.getBaslik(),UrunSatPojo.getAciklama(),UrunSatPojo.getMarka(),UrunSatPojo.getFiyat(),UrunSatPojo.getUretimyeri());
                    Toast.makeText(getApplicationContext(),"btn if sonundayız", Toast.LENGTH_LONG).show();
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
            Toast.makeText(getApplicationContext(),"urunyayinla içindeyiz",Toast.LENGTH_LONG).show();
            Call<UrunSonucPojo> request = ManagerAll.getInstance().urunsonuc(kid,ilce,mahalle,sokak,binano,baslik,aciklama,marka,fiyat,uretimyeri);
            request.enqueue(new Callback<UrunSonucPojo>() {
                @Override
                public void onResponse(Call<UrunSonucPojo> call, Response<UrunSonucPojo> response) {
                    Toast.makeText(getApplicationContext(),"Kayıt yapılmadı if girmedik",Toast.LENGTH_LONG).show();
                    if(response.body().isTruefalse())
                    {
                        Toast.makeText(getApplicationContext(),"Kayıt yapıldı",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(AdresBilgileriActivity.this,UrunResimler.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.anim_in,R.anim.anim_out);

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Kayıt yapılamadı",Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(Call<UrunSonucPojo> call, Throwable t) {

                }
            });






        }
    //




}
