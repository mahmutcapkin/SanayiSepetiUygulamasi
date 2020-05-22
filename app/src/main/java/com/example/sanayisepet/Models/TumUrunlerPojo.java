package com.example.sanayisepet.Models;

public class TumUrunlerPojo{
	private String resimyolu;
	private String fiyat;
	private String mahalle;
	private String binano;
	private int urunsayi;
	private boolean truefalse;
	private String result;
	private String sokak;
	private String resimsayiadres;
	private String aciklama;
	private String ilce;
	private String urunid;
	private String uyeid;
	private String baslik;

	public void setResimyolu(String resimyolu){
		this.resimyolu = resimyolu;
	}

	public String getResimyolu(){
		return resimyolu;
	}

	public void setFiyat(String fiyat){
		this.fiyat = fiyat;
	}

	public String getFiyat(){
		return fiyat;
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

	public void setUrunsayi(int urunsayi){
		this.urunsayi = urunsayi;
	}

	public int getUrunsayi(){
		return urunsayi;
	}

	public void setTruefalse(boolean truefalse){
		this.truefalse = truefalse;
	}

	public boolean isTruefalse(){
		return truefalse;
	}

	public void setResult(String result){
		this.result = result;
	}

	public String getResult(){
		return result;
	}

	public void setSokak(String sokak){
		this.sokak = sokak;
	}

	public String getSokak(){
		return sokak;
	}

	public void setResimsayiadres(String resimsayiadres){
		this.resimsayiadres = resimsayiadres;
	}

	public String getResimsayiadres(){
		return resimsayiadres;
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

	public void setUrunid(String urunid){
		this.urunid = urunid;
	}

	public String getUrunid(){
		return urunid;
	}

	public void setUyeid(String uyeid){
		this.uyeid = uyeid;
	}

	public String getUyeid(){
		return uyeid;
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
			"TumUrunlerPojo{" + 
			"resimyolu = '" + resimyolu + '\'' + 
			",fiyat = '" + fiyat + '\'' + 
			",mahalle = '" + mahalle + '\'' + 
			",binano = '" + binano + '\'' + 
			",urunsayi = '" + urunsayi + '\'' + 
			",truefalse = '" + truefalse + '\'' + 
			",result = '" + result + '\'' + 
			",sokak = '" + sokak + '\'' + 
			",resimsayiadres = '" + resimsayiadres + '\'' + 
			",aciklama = '" + aciklama + '\'' + 
			",ilce = '" + ilce + '\'' + 
			",urunid = '" + urunid + '\'' + 
			",uyeid = '" + uyeid + '\'' + 
			",baslik = '" + baslik + '\'' + 
			"}";
		}
}
