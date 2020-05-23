package com.example.sanayisepet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sanayisepet.Models.UrunSatPojo;

public class UrunBilgileriActivity extends AppCompatActivity {

    Button btnUrunBilgiIleri,btnUrunBilgiGeri;
    EditText etUrunBaslik,etUrunAciklama,etMarka,etFiyat,etUretimYeri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urun_bilgileri);
        tanimlama();
    }

    public void tanimlama()
    {
        etUrunBaslik = findViewById(R.id.etUrunBaslik);
        etUrunAciklama = findViewById(R.id.etUrunAciklama);
        etMarka = findViewById(R.id.etMarka);
        etFiyat = findViewById(R.id.etFiyat);
        etUretimYeri = findViewById(R.id.etUretimYeri);
        btnUrunBilgiIleri=findViewById(R.id.btnUrunIleri);
        btnUrunBilgiGeri=findViewById(R.id.btnUrunGeri);

        etUrunBaslik.setText(UrunSatPojo.getBaslik());
        etUrunAciklama.setText(UrunSatPojo.getAciklama());
        etMarka.setText(UrunSatPojo.getMarka());
        etFiyat.setText(UrunSatPojo.getFiyat());
        etUretimYeri.setText(UrunSatPojo.getUretimyeri());


        btnUrunBilgiIleri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!etUrunAciklama.getText().toString().equals("") &&
                        !etUrunBaslik.getText().toString().equals("") &&
                        !etMarka.getText().toString().equals("") &&
                        !etFiyat.getText().toString().equals("") &&
                        !etUretimYeri.getText().toString().equals(""))
                {
                    UrunSatPojo.setAciklama(etUrunAciklama.getText().toString());
                    UrunSatPojo.setBaslik(etUrunBaslik.getText().toString());
                    UrunSatPojo.setMarka(etMarka.getText().toString());
                    UrunSatPojo.setFiyat(etFiyat.getText().toString());
                    UrunSatPojo.setUretimyeri(etUretimYeri.getText().toString());

                    Intent intent = new Intent(UrunBilgileriActivity.this,AdresBilgileriActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                    finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Tüm bilgileri giriniz", Toast.LENGTH_LONG).show();
                }

            }
        });
        btnUrunBilgiGeri.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(UrunBilgileriActivity.this,MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_in_ters,R.anim.anim_out_ters);
                    finish();
            }
        });

    }
}
