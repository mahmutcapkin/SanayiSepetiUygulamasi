package com.example.sanayisepet.Models;

public class RegisterPojo{
	private String result;
	private boolean truefalse;
	private int dogrulamaKodu;

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

	public void setDogrulamaKodu(int dogrulamaKodu){
		this.dogrulamaKodu = dogrulamaKodu;
	}

	public int getDogrulamaKodu(){
		return dogrulamaKodu;
	}

	@Override
 	public String toString(){
		return 
			"RegisterPojo{" + 
			"result = '" + result + '\'' + 
			",truefalse = '" + truefalse + '\'' + 
			",dogrulamaKodu = '" + dogrulamaKodu + '\'' + 
			"}";
		}
}
