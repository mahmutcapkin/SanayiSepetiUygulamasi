package com.example.sanayisepet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.navigation.ui.AppBarConfiguration;

import me.relex.circleindicator.CircleIndicator;

//import android.support.annotation.NonNull;

public class MainActivity extends AppCompatActivity  implements  NavigationView.OnNavigationItemSelectedListener{

    String navHeaderText;
    TextView tvNavHeaderText;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String uyeId;
    Button btnUrunSat,btnUrunlerim,btnSatilikUrun,btnIletisim,btnMesajlarim;
    ViewPager MainurunDetaySlider;
    CircleIndicator MainsliderNokta;

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);

        sharedPreferences = getSharedPreferences("giris",0);
        navHeaderText = sharedPreferences.getString("uye_kullaniciAdi",null);
        uyeId = sharedPreferences.getString("uye_id",null);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);

        View v = navigationView.getHeaderView(0);
        tvNavHeaderText = v.findViewById(R.id.navHeaderText);
        tvNavHeaderText.setText(navHeaderText);

        navigationView.setNavigationItemSelectedListener(this);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_add, R.id.nav_message, R.id.nav_cikis)
                .setDrawerLayout(drawer)
                .build();

        tanimlamalar();
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

     /* public  void getSlider()
    {
        final Call<List<FavoriSliderPojo>> request = ManagerALL.getInstance().setSlider(uyeId);
        request.enqueue(new Callback<List<FavoriSliderPojo>>() {
            @Override
            public void onResponse(Call<List<FavoriSliderPojo>> call, Response<List<FavoriSliderPojo>> response) {

                if(response.body().size() > 0)
                {
                    favoriSliderAdapter = new FavoriSliderAdapter(response.body(),getApplicationContext(),MainActivity.this);

                    MainilanDetaySlider.setAdapter(favoriSliderAdapter);
                    MainsliderNokta.setViewPager(MainilanDetaySlider);
                    MainsliderNokta.bringToFront();
                }
            }

            @Override
            public void onFailure(Call<List<FavoriSliderPojo>> call, Throwable t) {

            }
        });
    }*/

    public void tanimlamalar()
    {
        btnSatilikUrun = findViewById(R.id.btnSatilikUrun);
        btnUrunSat = findViewById(R.id.btnUrunSat);
        btnUrunlerim = findViewById(R.id.btnUrunlerim);
        btnIletisim = findViewById(R.id.btnIletisimBilgi);
        MainurunDetaySlider = (ViewPager)findViewById(R.id.VPurunDetaySlider);
        MainsliderNokta = (CircleIndicator)findViewById(R.id.MainsliderNokta);
        btnMesajlarim = findViewById(R.id.btnKisiMesaj);

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



        /*
        btnMesajlarim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,IlanBilgileriActivity.class);
                startActivity(intent);
            }
        });





        btnIletisim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,IlanBilgileriActivity.class);
                startActivity(intent);
            }
        });*/

    }

}