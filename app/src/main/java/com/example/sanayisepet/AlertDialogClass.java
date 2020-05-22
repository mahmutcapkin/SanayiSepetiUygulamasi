package com.example.sanayisepet;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sanayisepet.Models.UrunlerimSilPojo;
import com.example.sanayisepet.RestApi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlertDialogClass {

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
