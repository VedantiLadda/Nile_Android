package com.example.vedantiladda.ecommerce.model;

public class ResponseHeader{
	private int qTime;
	private Params params;
	private int status;

	public void setQTime(int qTime){
		this.qTime = qTime;
	}

	public int getQTime(){
		return qTime;
	}

	public void setParams(Params params){
		this.params = params;
	}

	public Params getParams(){
		return params;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"ResponseHeader{" + 
			"qTime = '" + qTime + '\'' + 
			",params = '" + params + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
