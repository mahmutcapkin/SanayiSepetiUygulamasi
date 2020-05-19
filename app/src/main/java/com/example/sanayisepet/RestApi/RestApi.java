package com.example.sanayisepet.RestApi;

import com.example.sanayisepet.Models.DogrulamaPojo;
import com.example.sanayisepet.Models.LoginPojo;
import com.example.sanayisepet.Models.RegisterPojo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

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
}
