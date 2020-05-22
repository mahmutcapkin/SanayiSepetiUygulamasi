package com.example.sanayisepet.Models;

public class UrunSonucPojo{
	private boolean truefalse;
	private int uyeid;
	private String urunid;

	public void setTruefalse(boolean truefalse){
		this.truefalse = truefalse;
	}

	public boolean isTruefalse(){
		return truefalse;
	}

	public void setUyeid(int uyeid){
		this.uyeid = uyeid;
	}

	public int getUyeid(){
		return uyeid;
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
				"Response{" +
						"truefalse = '" + truefalse + '\'' +
						",uyeid = '" + uyeid + '\'' +
						",urunid = '" + urunid + '\'' +
						"}";
	}
}
