package com.example.sanayisepet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sanayisepet.Models.BilgilerPojo;
import com.example.sanayisepet.Models.Update;
import com.example.sanayisepet.RestApi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BilgiActivity extends AppCompatActivity {

    EditText etUserKid,etUserSifre;
    Button btnuserGuncelle;
    SharedPreferences sharedPreferences;
    String uye_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bilgi);

        Tanimlama();
        sharedPreferences = this.getSharedPreferences("giris",0);
        uye_id = sharedPreferences.getString("uye_id",null);
        istekAt(uye_id);

    }
    public void Tanimlama()
    {
        etUserKid = findViewById(R.id.etUserKid);
        etUserSifre = findViewById(R.id.etUserSifre);
        btnuserGuncelle = findViewById(R.id.btnuserGuncelle);
        btnuserGuncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guncelle(uye_id,etUserKid.getText().toString(),etUserSifre.getText().toString());
            }
        });
    }

    public void istekAt(String uye_id)
    {
        Call<BilgilerPojo> request = ManagerAll.getInstance().BilgiGetir(uye_id);
        request.enqueue(new Callback<BilgilerPojo>() {
            @Override
            public void onResponse(Call<BilgilerPojo> call, Response<BilgilerPojo> response) {

                if(response.isSuccessful())
                {
                    etUserKid.setText(response.body().getKadi());
                    etUserSifre.setText(response.body().getSifre());
                }
            }

            @Override
            public void onFailure(Call<BilgilerPojo> call, Throwable t) {

            }
        });
    }

    public void guncelle(String userid,String user,String sifre)
    {
        Call<Update> request = ManagerAll.getInstance().BilgiGuncelle(userid,user, sifre);
        request.enqueue(new Callback<Update>() {
            @Override
            public void onResponse(Call<Update> call, Response<Update> response) {

                if(response.body().isTf())
                {
                    Intent intent = new Intent(BilgiActivity.this,BilgiActivity.class);
                    startActivity(intent);
                    finish();
                }
                Toast.makeText(getApplicationContext(),"Güncelleme gerçekleşti",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Update> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"hata"+t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }



}
