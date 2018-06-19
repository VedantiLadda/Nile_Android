package com.example.vedantiladda.ecommerce.model;

public class SearchResponse{
	private Response response;
	private ResponseHeader responseHeader;

	public void setResponse(Response response){
		this.response = response;
	}

	public Response getResponse(){
		return response;
	}

	public void setResponseHeader(ResponseHeader responseHeader){
		this.responseHeader = responseHeader;
	}

	public ResponseHeader getResponseHeader(){
		return responseHeader;
	}

	@Override
 	public String toString(){
		return 
			"SearchResponse{" + 
			"response = '" + response + '\'' + 
			",responseHeader = '" + responseHeader + '\'' + 
			"}";
		}
}
