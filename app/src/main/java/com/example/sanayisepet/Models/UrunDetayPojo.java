package com.example.sanayisepet.Models;

public class UrunDetayPojo{
	private String sokak;
	private String aciklama;
	private String ilce;
	private String uyeid;
	private String fiyat;
	private String marka;
	private String uretimyeri;
	private String mahalle;
	private String binano;
	private String baslik;

	public void setSokak(String sokak){
		this.sokak = sokak;
	}

	public String getSokak(){
		return sokak;
	}

	public void setAciklama(String aciklama){
		this.aciklama = aciklama;
	}

	public String getAciklama(){
		return aciklama;
	}

	public void setIlce(String ilce){
		this.ilce = ilce;
	}

	public String getIlce(){
		return ilce;
	}

	public void setUyeid(String uyeid){
		this.uyeid = uyeid;
	}

	public String getUyeid(){
		return uyeid;
	}

	public void setFiyat(String fiyat){
		this.fiyat = fiyat;
	}

	public String getFiyat(){
		return fiyat;
	}

	public void setMarka(String marka){
		this.marka = marka;
	}

	public String getMarka(){
		return marka;
	}

	public void setUretimyeri(String uretimyeri){
		this.uretimyeri = uretimyeri;
	}

	public String getUretimyeri(){
		return uretimyeri;
	}

	public void setMahalle(String mahalle){
		this.mahalle = mahalle;
	}

	public String getMahalle(){
		return mahalle;
	}

	public void setBinano(String binano){
		this.binano = binano;
	}

	public String getBinano(){
		return binano;
	}

	public void setBaslik(String baslik){
		this.baslik = baslik;
	}

	public String getBaslik(){
		return baslik;
	}

	@Override
 	public String toString(){
		return 
			"UrunDetayPojo{" + 
			"sokak = '" + sokak + '\'' + 
			",aciklama = '" + aciklama + '\'' + 
			",ilce = '" + ilce + '\'' + 
			",uyeid = '" + uyeid + '\'' + 
			",fiyat = '" + fiyat + '\'' + 
			",marka = '" + marka + '\'' + 
			",uretimyeri = '" + uretimyeri + '\'' + 
			",mahalle = '" + mahalle + '\'' + 
			",binano = '" + binano + '\'' + 
			",baslik = '" + baslik + '\'' + 
			"}";
		}
}
