package com.example.vedantiladda.ecommerce.model;

import java.util.List;

public class Response{
	private List<DocsItem> docs;
	private int numFound;
	private int start;

	public void setDocs(List<DocsItem> docs){
		this.docs = docs;
	}

	public List<DocsItem> getDocs(){
		return docs;
	}

	public void setNumFound(int numFound){
		this.numFound = numFound;
	}

	public int getNumFound(){
		return numFound;
	}

	public void setStart(int start){
		this.start = start;
	}

	public int getStart(){
		return start;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"docs = '" + docs + '\'' + 
			",numFound = '" + numFound + '\'' + 
			",start = '" + start + '\'' + 
			"}";
		}
}