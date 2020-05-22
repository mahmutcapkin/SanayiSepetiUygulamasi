package com.example.sanayisepet.Models;

public class UrunlerimPojo{
	private boolean truefalse;
	private String result;
	private String resimyolu;
	private String resimsayiadres;
	private String aciklama;
	private String urunid;
	private String uyeid;
	private String fiyat;
	private String baslik;
	private int urunsayi;

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

	public void setResimyolu(String resimyolu){
		this.resimyolu = resimyolu;
	}

	public String getResimyolu(){
		return resimyolu;
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

	public void setFiyat(String fiyat){
		this.fiyat = fiyat;
	}

	public String getFiyat(){
		return fiyat;
	}

	public void setBaslik(String baslik){
		this.baslik = baslik;
	}

	public String getBaslik(){
		return baslik;
	}

	public void setUrunsayi(int urunsayi){
		this.urunsayi = urunsayi;
	}

	public int getUrunsayi(){
		return urunsayi;
	}

	@Override
 	public String toString(){
		return 
			"UrunlerimPojo{" + 
			"truefalse = '" + truefalse + '\'' + 
			",result = '" + result + '\'' + 
			",resimyolu = '" + resimyolu + '\'' + 
			",resimsayiadres = '" + resimsayiadres + '\'' + 
			",aciklama = '" + aciklama + '\'' + 
			",urunid = '" + urunid + '\'' + 
			",uyeid = '" + uyeid + '\'' + 
			",fiyat = '" + fiyat + '\'' + 
			",baslik = '" + baslik + '\'' + 
			",urunsayi = '" + urunsayi + '\'' + 
			"}";
		}
}
