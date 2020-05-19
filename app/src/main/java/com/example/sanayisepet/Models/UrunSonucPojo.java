package com.example.sanayisepet.Models;

public class UrunSonucPojo{
	private boolean truefalse;
	private String urunId;
	private int uyeId;

	public void setTruefalse(boolean truefalse){
		this.truefalse = truefalse;
	}

	public boolean isTruefalse(){
		return truefalse;
	}

	public void setUrunId(String urunId){
		this.urunId = urunId;
	}

	public String getUrunId(){
		return urunId;
	}

	public void setUyeId(int uyeId){
		this.uyeId = uyeId;
	}

	public int getUyeId(){
		return uyeId;
	}

	@Override
 	public String toString(){
		return 
			"UrunSonucPojo{" + 
			"truefalse = '" + truefalse + '\'' + 
			",urun_id = '" + urunId + '\'' + 
			",uye_id = '" + uyeId + '\'' + 
			"}";
		}
}
