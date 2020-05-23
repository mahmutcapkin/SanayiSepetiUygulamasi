package com.example.sanayisepet.Models;

public class FavoriIslem{
	private boolean truefalse;
	private String text;

	public void setTruefalse(boolean truefalse){
		this.truefalse = truefalse;
	}

	public boolean isTruefalse(){
		return truefalse;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	@Override
 	public String toString(){
		return 
			"FavoriIslem{" + 
			"truefalse = '" + truefalse + '\'' + 
			",text = '" + text + '\'' + 
			"}";
		}
}
