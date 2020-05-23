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

public class ManagerAll extends  BaseManager {
    private static ManagerAll ourInstance = new ManagerAll();

    public static synchronized ManagerAll getInstance(){return ourInstance;}

    public Call<LoginPojo> login(String kad, String sifre){

        Call<LoginPojo> x = getRestApi().control(kad,sifre);
        return x;
    }
    public Call<RegisterPojo> register(String kad, String sifre){

        Call<RegisterPojo> x = getRestApi().kayitol(kad,sifre);
        return x;
    }
    public Call<DogrulamaPojo> dogrula(String kad, String kod){

        Call<DogrulamaPojo> x = getRestApi().dogrulama(kad,kod);
        return x;
    }
    public Call<UrunSonucPojo> urunsonuc(String kid, String ilce, String mahalle, String sokak, String binano, String baslik, String aciklama, String marka ,String fiyat, String uretimyeri){

        Call<UrunSonucPojo> x = getRestApi().urunSat(kid,ilce,mahalle,sokak,binano,baslik,aciklama,marka,fiyat,uretimyeri);
        return x;
    }

    public Call<ResimEklePojo> resimEkle(String kid, String urun_id, String image){

        Call<ResimEklePojo> x = getRestApi().resimYukle(kid,urun_id,image);
        return x;
    }
    public Call<List<UrunlerimPojo>> urunlerim(String kid){

        Call<List<UrunlerimPojo>> x = getRestApi().urunlerim(kid);
        return x;
    }

    public Call<UrunlerimSilPojo> urunlerimSil(String urunid){

        Call<UrunlerimSilPojo> x = getRestApi().urunlerimdensil(urunid);
        return x;
    }

    public  Call<List<TumUrunlerPojo>> tumurunler(){

        Call<List<TumUrunlerPojo>> x = getRestApi().tumurunler();

        return x;
    }

    public Call<UrunDetayPojo> urunDetay(String urunid){

        Call<UrunDetayPojo> x = getRestApi().urundetay(urunid);

        return x;
    }

    public Call<List<SliderPojo>> urunDetayResim(String urunid){

        Call<List<SliderPojo>> x = getRestApi().urundetayResimler(urunid);

        return x;
    }
    public Call<FavoriKontrol> favoritext(String kullaniciid,String urunid){

        Call<FavoriKontrol> x = getRestApi().favoritext(kullaniciid,urunid);

        return x;
    }

    public Call<FavoriIslem> favoriIslem(String kullaniciid, String urunid){

        Call<FavoriIslem> x = getRestApi().favoriIslem(kullaniciid,urunid);

        return x;
    }

    public Call<List<FavoriSliderPojo>> sliderislem(String kid){

        Call<List<FavoriSliderPojo>> x = getRestApi().sliderislem(kid);

        return x;
    }

    public Call<BilgilerPojo> BilgiGetir(String kullaniciid){

        Call<BilgilerPojo> x = getRestApi().bilgigetir(kullaniciid);

        return x;
    }

    public Call<Update> BilgiGuncelle(String id,String user,String sifre){

        Call<Update> x = getRestApi().bilgiguncelle(id,user,sifre);

        return x;
    }

}
