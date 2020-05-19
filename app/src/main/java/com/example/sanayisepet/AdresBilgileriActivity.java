package com.example.sanayisepet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

public class AdresBilgileriActivity extends AppCompatActivity {


    Button btnAdresBilgi,btnAdresBilgiGeri;
    EditText etNo,etSokak,etIlceBilgi,etMahalleBilgi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adres_bilgileri);
    }
}
