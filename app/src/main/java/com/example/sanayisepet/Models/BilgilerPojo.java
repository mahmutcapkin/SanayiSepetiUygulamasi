package com.example.sanayisepet.Models;

public class BilgilerPojo{
	private String sifre;
	private String kadi;

	public void setSifre(String sifre){
		this.sifre = sifre;
	}

	public String getSifre(){
		return sifre;
	}

	public void setKadi(String kadi){
		this.kadi = kadi;
	}

	public String getKadi(){
		return kadi;
	}

	@Override
 	public String toString(){
		return 
			"BilgilerPojo{" + 
			"sifre = '" + sifre + '\'' + 
			",kadi = '" + kadi + '\'' + 
			"}";
		}
}
