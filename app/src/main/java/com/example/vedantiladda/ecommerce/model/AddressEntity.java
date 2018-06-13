package com.example.vedantiladda.ecommerce.model;

//import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class AddressEntity{

	@SerializedName("zip")
	private String zip;

	@SerializedName("city")
	private String city;

	@SerializedName("state")
	private String state;

	@SerializedName("landmark")
	private String landmark;

	@SerializedName("line2")
	private String line2;

	@SerializedName("line1")
	private String line1;

	public void setZip(String zip){
		this.zip = zip;
	}

	public String getZip(){
		return zip;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}

	public void setState(String state){
		this.state = state;
	}

	public String getState(){
		return state;
	}

	public void setLandmark(String landmark){
		this.landmark = landmark;
	}

	public String getLandmark(){
		return landmark;
	}

	public void setLine2(String line2){
		this.line2 = line2;
	}

	public String getLine2(){
		return line2;
	}

	public void setLine1(String line1){
		this.line1 = line1;
	}

	public String getLine1(){
		return line1;
	}

	@Override
 	public String toString(){
		return 
			"AddressEntity{" + 
			"zip = '" + zip + '\'' + 
			",city = '" + city + '\'' + 
			",state = '" + state + '\'' + 
			",landmark = '" + landmark + '\'' + 
			",line2 = '" + line2 + '\'' + 
			",line1 = '" + line1 + '\'' + 
			"}";
		}
}