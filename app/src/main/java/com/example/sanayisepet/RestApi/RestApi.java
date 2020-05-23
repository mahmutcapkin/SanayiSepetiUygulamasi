package com.example.sanayisepet.RestApi;

import com.example.sanayisepet.Models.BilgilerPojo;
import com.example.sanayisepet.Models.DogrulamaPojo;
import com.example.sanayisepet.Models.FavoriIslem;
import com.example.sanayisepet.Models.FavoriKontrol;
import com.example.sanayisepet.Models.FavoriSliderPojo;
import com.example.sanayisepet.Models.LoginPojo;
import com.example.sanayisepet.Models.RegisterPojo;
import com.example.sanayisepet.Models.ResimEklePojo;
import com.example.sanayisepet.Models.SliderPojo;
import com.example.sanayisepet.Models.TumUrunlerPojo;
import com.example.sanayisepet.Models.Update;
import com.example.sanayisepet.Models.UrunDetayPojo;
import com.example.sanayisepet.Models.UrunSonucPojo;
import com.example.sanayisepet.Models.UrunlerimPojo;
import com.example.sanayisepet.Models.UrunlerimSilPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RestApi {
    @FormUrlEncoded
    @POST("login.php")
    Call<LoginPojo> control(@Field("kullaniciadi") String kad, @Field("kullanicisifre") String sifre);

    @FormUrlEncoded
    @POST("register.php")
    Call<RegisterPojo> kayitol(@Field("kullaniciadi") String kad, @Field("kullanicisifre") String sifre);

    @FormUrlEncoded
    @POST("dogrulama.php")
    Call<DogrulamaPojo> dogrulama(@Field("kullaniciadi") String kad, @Field("dogrulamakodu") String sifre);

    @FormUrlEncoded
    @POST("urunsat.php")
    Call<UrunSonucPojo> urunSat(@Field("kullanici_id") String kullanici_id, @Field("ilce") String ilce, @Field("mahalle") String mahalle, @Field("sokak") String sokak, @Field("binano") String binano, @Field("baslik") String baslik, @Field("aciklama") String aciklama, @Field("marka") String marka, @Field("fiyat") String fiyat, @Field("uretimyeri") String uretimyeri);

    @FormUrlEncoded
    @POST("resimekle.php")
    Call<ResimEklePojo> resimYukle(@Field("Kullanici_id") String Kullanici_id, @Field("urun_id") String urun_id, @Field("resim") String base64StringResim);


    @GET("urunlerim.php")
    Call<List<UrunlerimPojo>> urunlerim(@Query("kullanici_id") String kullanici_id);

    @GET("urunlerimdensil.php")
    Call<UrunlerimSilPojo> urunlerimdensil(@Query("urun_id") String urun_id);

    @GET("tumurunler.php")
    Call<List<TumUrunlerPojo>> tumurunler();


    @GET("urundetay.php")
    Call<UrunDetayPojo> urundetay(@Query("urun_id") String urun_id);

    @GET("urundetayresimler.php")
    Call<List<SliderPojo>> urundetayResimler(@Query("urun_id") String urun_id);

    @GET("favori.php")
    Call<FavoriKontrol> favoritext(@Query("kullanici_id") String kullanici_id, @Query("urun_id") String urun_id);

    @GET("favorisayfa.php")
    Call<FavoriIslem> favoriIslem(@Query("kullanici_id") String kullanici_id, @Query("urun_id") String urun_id);

    @GET("favorislider.php")
    Call<List<FavoriSliderPojo>> sliderislem(@Query("kullanici_id") String kullanici_id);

    @GET("bilgiler.php")
    Call<BilgilerPojo> bilgigetir(@Query("kullanici_id") String kullanici_id);

    @GET("bilgiguncelle.php")
    Call<Update> bilgiguncelle(@Query("kullanici_id") String kullanici_id, @Query("user") String user, @Query("sifre") String sifre);



}
