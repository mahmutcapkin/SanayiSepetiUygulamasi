package com.example.sanayisepet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.viewpager.widget.ViewPager;

import com.example.sanayisepet.Adapters.FavoriSliderAdapter;
import com.example.sanayisepet.Models.FavoriSliderPojo;
import com.example.sanayisepet.RestApi.ManagerAll;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import android.support.annotation.NonNull;

public class MainActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{

    String navHeaderText;
    TextView tvNavHeaderText;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String uyeId;
    Button btnUrunSat,btnUrunlerim,btnSatilikUrun,btnIletisim,btnMesajlarim,btnUrunFavori;
    ViewPager vpFavoridetay;
    CircleIndicator urunDetaySlider;
    FavoriSliderAdapter favoriSliderAdapter;

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);

        sharedPreferences = getSharedPreferences("giris",0);
        navHeaderText = sharedPreferences.getString("uye_kullaniciAdi",null);
        uyeId = sharedPreferences.getString("uye_id",null);
        Toast.makeText(getApplicationContext(),uyeId,Toast.LENGTH_LONG).show();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener((DrawerLayout.DrawerListener) toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);

        View v = navigationView.getHeaderView(0);
        tvNavHeaderText = v.findViewById(R.id.navHeaderText);
        tvNavHeaderText.setText(navHeaderText);

        navigationView.setNavigationItemSelectedListener(this);
        /*
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_add, R.id.nav_message, R.id.nav_cikis)
                .setDrawerLayout(drawer)
                .build();*/

        tanimlamalar();
        getSlider();
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_cikis) {

            editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();

            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

      public  void getSlider()
    {
        Call<List<FavoriSliderPojo>> request = ManagerAll.getInstance().sliderislem(uyeId);
        request.enqueue(new Callback<List<FavoriSliderPojo>>() {
            @Override
            public void onResponse(Call<List<FavoriSliderPojo>> call, Response<List<FavoriSliderPojo>> response) {
                if(response.body().get(0).isTruefalse())
                {
                    if(response.body().size() > 0)
                    {
                        favoriSliderAdapter = new FavoriSliderAdapter(response.body(),getApplicationContext(),MainActivity.this);
                        vpFavoridetay.setAdapter(favoriSliderAdapter);
                        urunDetaySlider.setViewPager(vpFavoridetay);
                        urunDetaySlider.bringToFront();
                    }

                }
                else
                {
                    favoriSliderAdapter = new FavoriSliderAdapter(response.body(),getApplicationContext(),MainActivity.this);
                    vpFavoridetay.setAdapter(favoriSliderAdapter);
                    urunDetaySlider.setViewPager(vpFavoridetay);
                    urunDetaySlider.bringToFront();
                }


            }

            @Override
            public void onFailure(Call<List<FavoriSliderPojo>> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getSlider();
    }

    public void tanimlamalar()
    {
        btnSatilikUrun = findViewById(R.id.btnSatilikUrun);
        btnUrunSat = findViewById(R.id.btnUrunSat);
        btnUrunlerim = findViewById(R.id.btnUrunlerim);
        btnIletisim = findViewById(R.id.btnIletisimBilgi);
        btnUrunFavori = findViewById(R.id.btnUrunFavori);
        btnMesajlarim = findViewById(R.id.btnKisiMesaj);

        vpFavoridetay = (ViewPager)findViewById(R.id.vpFavoridetay);
        urunDetaySlider = (CircleIndicator)findViewById(R.id.urunDetaySlider);


        btnUrunSat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UrunBilgileriActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
            }
        });

        btnSatilikUrun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TumUrunler.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
            }
        });

        btnUrunlerim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Urunlerim.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
            }
        });


        btnIletisim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,BilgiActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
            }
        });


        btnMesajlarim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MesajlarActivity.class);
                startActivity(intent);
                 overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
            }
        });

    }

}
