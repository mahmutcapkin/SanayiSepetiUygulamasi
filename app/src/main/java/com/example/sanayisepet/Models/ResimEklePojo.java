package com.example.sanayisepet.Models;

public class ResimEklePojo{
	private boolean truefalse;
	private String sonuc;

	public void setTruefalse(boolean truefalse){
		this.truefalse = truefalse;
	}

	public boolean isTruefalse(){
		return truefalse;
	}

	public void setSonuc(String sonuc){
		this.sonuc = sonuc;
	}

	public String getSonuc(){
		return sonuc;
	}

	@Override
 	public String toString(){
		return 
			"ResimEklePojo{" + 
			"truefalse = '" + truefalse + '\'' + 
			",sonuc = '" + sonuc + '\'' + 
			"}";
		}
}
