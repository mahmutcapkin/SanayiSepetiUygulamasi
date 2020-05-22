package com.example.sanayisepet.Models;

public class SliderPojo{
	private String resimyolu;
	private String resimsayiadres;

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

	@Override
 	public String toString(){
		return 
			"SliderPojo{" + 
			"resimyolu = '" + resimyolu + '\'' + 
			",resimsayiadres = '" + resimsayiadres + '\'' + 
			"}";
		}
}
