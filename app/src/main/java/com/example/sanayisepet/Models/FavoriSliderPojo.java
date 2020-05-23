package com.example.sanayisepet.Models;

public class FavoriSliderPojo{
	private boolean truefalse;
	private String resimsayiadres;
	private String urunid;

	public void setTruefalse(boolean truefalse){
		this.truefalse = truefalse;
	}

	public boolean isTruefalse(){
		return truefalse;
	}

	public void setResimsayiadres(String resimsayiadres){
		this.resimsayiadres = resimsayiadres;
	}

	public String getResimsayiadres(){
		return resimsayiadres;
	}

	public void setUrunid(String urunid){
		this.urunid = urunid;
	}

	public String getUrunid(){
		return urunid;
	}

	@Override
 	public String toString(){
		return 
			"FavoriSliderPojo{" + 
			"truefalse = '" + truefalse + '\'' + 
			",resimsayiadres = '" + resimsayiadres + '\'' + 
			",urunid = '" + urunid + '\'' + 
			"}";
		}
}
