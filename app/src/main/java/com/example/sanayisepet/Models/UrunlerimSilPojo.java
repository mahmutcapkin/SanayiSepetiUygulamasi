package com.example.sanayisepet.Models;

public class UrunlerimSilPojo{
	private String result;
	private boolean truefalse;

	public void setResult(String result){
		this.result = result;
	}

	public String getResult(){
		return result;
	}

	public void setTruefalse(boolean truefalse){
		this.truefalse = truefalse;
	}

	public boolean isTruefalse(){
		return truefalse;
	}

	@Override
 	public String toString(){
		return 
			"UrunlerimSilPojo{" + 
			"result = '" + result + '\'' + 
			",truefalse = '" + truefalse + '\'' + 
			"}";
		}
}
