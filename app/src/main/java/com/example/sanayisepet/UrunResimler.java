package com.example.sanayisepet;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sanayisepet.Models.ResimEklePojo;
import com.example.sanayisepet.RestApi.ManagerAll;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UrunResimler extends AppCompatActivity {

    Button btnResimSec, btnResimEkle, btnCikis;
    ImageView secilenUrunResim;
    Bitmap bitmap;
    String uye_id, urun_id, image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urun_resimler);
        tanimla();
        Bundle bundle = getIntent().getExtras();

        //uye_id ve urun_ farklı türde dönüyr ona göre adresbilgileri java dan da putextra da kontrol et hata cıkarsa
        uye_id = bundle.getString("uye_id");
        urun_id = bundle.getString("urun_id");

    }

    public void tanimla()
    {
        btnResimSec = findViewById(R.id.btnResimSec);
        btnResimEkle = findViewById(R.id.btnResimEkle);
        btnCikis = findViewById(R.id.btnCikis);
        secilenUrunResim = findViewById(R.id.secilenUrunResimImageView);

        btnResimSec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resimGoster();
            }
        });

        btnResimEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yukle();
            }
        });
        btnCikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UrunResimler.this,MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in_ters,R.anim.anim_out_ters);
                finish();
            }
        });

    }

    public void resimGoster(){ // galeriyi açar
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,777);

        //secilenUrunResim.setImageResource(R.drawable.rest);
    }
    public  void yukle()
    {
         image = imageToString();
        Call<ResimEklePojo> request = ManagerAll.getInstance().resimEkle(uye_id,urun_id,image);
        request.enqueue(new Callback<ResimEklePojo>() {
            @Override
            public void onResponse(Call<ResimEklePojo> call, Response<ResimEklePojo> response) {
                if(response.body().isTruefalse())
                {
                    Toast.makeText(getApplicationContext(),response.body().getSonuc(),Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),response.body().getSonuc(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResimEklePojo> call, Throwable t) {

            }
        });
    }

    public String imageToString(){

        //RESMI BITMAP NESNESINE ATAMA
       // bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.rest);

        //RESMI POST ISLEMI ILE YOLLAMAK ICIN YAPLAN ENCODE ISLEMI
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);

        byte[] byt = byteArrayOutputStream.toByteArray();
        String imageToString = Base64.encodeToString(byt,Base64.DEFAULT);


        return imageToString;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 777 && resultCode == RESULT_OK && data != null){
            Uri path = data.getData();
            try{

               // bitmap = BitmapFactory.decodeStream(getResources().getDrawable(R.drawable.rest,null));
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                secilenUrunResim.setImageBitmap(bitmap);
                secilenUrunResim.setVisibility(View.VISIBLE);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}
