package com.example.sanayisepet.Models;

public class DogrulamaPojo{
	private boolean truefalse;
	private String result;
	private int id;
	private String kad;

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

	public void setId(int id){
		this.id = id;
	}

	public Object getId(){
		return id;
	}

	public void setKad(String kad){
		this.kad = kad;
	}

	public String getKad(){
		return kad;
	}

	@Override
 	public String toString(){
		return 
			"DogrulamaPojo{" + 
			"truefalse = '" + truefalse + '\'' + 
			",result = '" + result + '\'' + 
			",id = '" + id + '\'' + 
			",kad = '" + kad + '\'' + 
			"}";
		}
}
