package com.example.sanayisepet;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sanayisepet.Adapters.UrunlerimAdapter;
import com.example.sanayisepet.Models.UrunlerimPojo;
import com.example.sanayisepet.Models.UrunlerimSilPojo;
import com.example.sanayisepet.RestApi.ManagerAll;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Urunlerim extends AppCompatActivity {

    ListView listView;
    UrunlerimAdapter urunlerimAdapter;
    List<UrunlerimPojo> urunlerimPojos;
    SharedPreferences sharedPreferences;
    String uye_id;
    AlertDialogClass alertDialogClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urunlerim);

        listView = (ListView)findViewById(R.id.lvUrunler);
        sharedPreferences = getApplicationContext().getSharedPreferences("giris",0);
        uye_id = sharedPreferences.getString("uye_id",null);
        urunlerimiGoruntule();
        alertDialogClass = new AlertDialogClass();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                alertDialogClass.UrunlerimAlertDialog(Urunlerim.this,urunlerimPojos.get(position).getUrunid());
            }
        });

    }

    public void urunlerimiGoruntule(){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Ürünlerim");
        progressDialog.setMessage("Ürünleriniz yükleniyor..Lütfen bekleyiniz..");
        progressDialog.setCancelable(false);
        progressDialog.show();

        urunlerimPojos = new ArrayList<>();
        Call<List<UrunlerimPojo>> request = ManagerAll.getInstance().urunlerim(uye_id);

        request.enqueue(new Callback<List<UrunlerimPojo>>() {
            @Override
            public void onResponse(Call<List<UrunlerimPojo>> call, Response<List<UrunlerimPojo>> response) {

                if(response.isSuccessful()) {

                    urunlerimPojos = response.body();
                    if(response.body().get(0).isTruefalse())
                    {
                        urunlerimAdapter = new UrunlerimAdapter(urunlerimPojos, getApplicationContext(), Urunlerim.this);
                        listView.setAdapter(urunlerimAdapter);
                        Toast.makeText(getApplicationContext(), response.body().get(0).getUrunsayi()+" tane ürününüz bulunmaktadır.", Toast.LENGTH_LONG).show();
                        progressDialog.cancel();
                    }
                    else{
                        progressDialog.cancel();
                        Toast.makeText(getApplicationContext(), " Ürününüz bulunmamaktadır.", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Urunlerim.this,MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.anim_in_ters,R.anim.anim_out_ters);
                        finish();
                    }

                }
            }

            @Override
            public void onFailure(Call<List<UrunlerimPojo>> call, Throwable t) {
                Log.i("hata",t.toString());
                Log.i("hata",t.getMessage());
                Toast.makeText(getApplicationContext(),"Hata", Toast.LENGTH_LONG).show();
                progressDialog.cancel();
            }
        });



    }



    public void UrunlerimAlertDialog(final Activity activity, final String urun_id)
    {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.alertlayout,null);

        Button btnSil = view.findViewById(R.id.btnSil);
        Button btnCik = view.findViewById(R.id.btnCikis);

        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setView(view);
        alert.setCancelable(false);

        final AlertDialog dialog = alert.create();

        btnSil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sil(urun_id,activity);
                dialog.cancel();
            }
        });
        btnCik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    public void sil(String urunId, final Activity activity)
    {
        Call<UrunlerimSilPojo> request = ManagerAll.getInstance().urunlerimSil(urunId);
        request.enqueue(new Callback<UrunlerimSilPojo>() {
            @Override
            public void onResponse(Call<UrunlerimSilPojo> call, Response<UrunlerimSilPojo> response) {

                if(response.isSuccessful()) {
                    Toast.makeText(activity,"silindi"+response.body().getResult(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UrunlerimSilPojo> call, Throwable t) {
                Toast.makeText(activity,"HATA"+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }//sil







}
