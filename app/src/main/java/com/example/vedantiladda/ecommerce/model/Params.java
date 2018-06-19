package com.example.vedantiladda.ecommerce.model;

public class Params{
	private String Q;

	public void setQ(String Q){
		this.Q = Q;
	}

	public String getQ(){
		return Q;
	}

	@Override
 	public String toString(){
		return 
			"Params{" + 
			"q = '" + Q + '\'' + 
			"}";
		}
}
