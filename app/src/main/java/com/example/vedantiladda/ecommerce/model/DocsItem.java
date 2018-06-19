package com.example.vedantiladda.ecommerce.model;

import java.util.List;

public class DocsItem{
	private List<Integer> defaultMerchantPrice;
	private List<String> attributeColorStr;
	private List<String> brandStr;
	private List<String> images;
	private List<String> discription;
	private List<String> attributeColor;
	private List<Integer> attributeWeight;
	private List<String> nameStr;
	private List<String> defaultMerchantNameStr;
	private List<Integer> attributeHeight;
	private List<Integer> defaultRating;
	private List<String> discriptionStr;
	private long version;
	private List<String> name;
	private List<String> attributeMaterials;
	private String id;
	private List<String> defaultMerchantName;
	private List<String> attributeMaterialsStr;
	private List<String> imagesStr;
	private List<String> category;
	private List<Integer> stock;
	private List<String> categoryStr;
	private List<Integer> attributeWidth;
	private List<String> brand;

	public void setDefaultMerchantPrice(List<Integer> defaultMerchantPrice){
		this.defaultMerchantPrice = defaultMerchantPrice;
	}

	public List<Integer> getDefaultMerchantPrice(){
		return defaultMerchantPrice;
	}

	public void setAttributeColorStr(List<String> attributeColorStr){
		this.attributeColorStr = attributeColorStr;
	}

	public List<String> getAttributeColorStr(){
		return attributeColorStr;
	}

	public void setBrandStr(List<String> brandStr){
		this.brandStr = brandStr;
	}

	public List<String> getBrandStr(){
		return brandStr;
	}

	public void setImages(List<String> images){
		this.images = images;
	}

	public List<String> getImages(){
		return images;
	}

	public void setDiscription(List<String> discription){
		this.discription = discription;
	}

	public List<String> getDiscription(){
		return discription;
	}

	public void setAttributeColor(List<String> attributeColor){
		this.attributeColor = attributeColor;
	}

	public List<String> getAttributeColor(){
		return attributeColor;
	}

	public void setAttributeWeight(List<Integer> attributeWeight){
		this.attributeWeight = attributeWeight;
	}

	public List<Integer> getAttributeWeight(){
		return attributeWeight;
	}

	public void setNameStr(List<String> nameStr){
		this.nameStr = nameStr;
	}

	public List<String> getNameStr(){
		return nameStr;
	}

	public void setDefaultMerchantNameStr(List<String> defaultMerchantNameStr){
		this.defaultMerchantNameStr = defaultMerchantNameStr;
	}

	public List<String> getDefaultMerchantNameStr(){
		return defaultMerchantNameStr;
	}

	public void setAttributeHeight(List<Integer> attributeHeight){
		this.attributeHeight = attributeHeight;
	}

	public List<Integer> getAttributeHeight(){
		return attributeHeight;
	}

	public void setDefaultRating(List<Integer> defaultRating){
		this.defaultRating = defaultRating;
	}

	public List<Integer> getDefaultRating(){
		return defaultRating;
	}

	public void setDiscriptionStr(List<String> discriptionStr){
		this.discriptionStr = discriptionStr;
	}

	public List<String> getDiscriptionStr(){
		return discriptionStr;
	}

	public void setVersion(long version){
		this.version = version;
	}

	public long getVersion(){
		return version;
	}

	public void setName(List<String> name){
		this.name = name;
	}

	public List<String> getName(){
		return name;
	}

	public void setAttributeMaterials(List<String> attributeMaterials){
		this.attributeMaterials = attributeMaterials;
	}

	public List<String> getAttributeMaterials(){
		return attributeMaterials;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setDefaultMerchantName(List<String> defaultMerchantName){
		this.defaultMerchantName = defaultMerchantName;
	}

	public List<String> getDefaultMerchantName(){
		return defaultMerchantName;
	}

	public void setAttributeMaterialsStr(List<String> attributeMaterialsStr){
		this.attributeMaterialsStr = attributeMaterialsStr;
	}

	public List<String> getAttributeMaterialsStr(){
		return attributeMaterialsStr;
	}

	public void setImagesStr(List<String> imagesStr){
		this.imagesStr = imagesStr;
	}

	public List<String> getImagesStr(){
		return imagesStr;
	}

	public void setCategory(List<String> category){
		this.category = category;
	}

	public List<String> getCategory(){
		return category;
	}

	public void setStock(List<Integer> stock){
		this.stock = stock;
	}

	public List<Integer> getStock(){
		return stock;
	}

	public void setCategoryStr(List<String> categoryStr){
		this.categoryStr = categoryStr;
	}

	public List<String> getCategoryStr(){
		return categoryStr;
	}

	public void setAttributeWidth(List<Integer> attributeWidth){
		this.attributeWidth = attributeWidth;
	}

	public List<Integer> getAttributeWidth(){
		return attributeWidth;
	}

	public void setBrand(List<String> brand){
		this.brand = brand;
	}

	public List<String> getBrand(){
		return brand;
	}

	@Override
 	public String toString(){
		return 
			"DocsItem{" + 
			"defaultMerchantPrice = '" + defaultMerchantPrice + '\'' + 
			",attribute.color_str = '" + attributeColorStr + '\'' + 
			",brand_str = '" + brandStr + '\'' + 
			",images = '" + images + '\'' + 
			",discription = '" + discription + '\'' + 
			",attribute.color = '" + attributeColor + '\'' + 
			",attribute.weight = '" + attributeWeight + '\'' + 
			",name_str = '" + nameStr + '\'' + 
			",defaultMerchantName_str = '" + defaultMerchantNameStr + '\'' + 
			",attribute.height = '" + attributeHeight + '\'' + 
			",defaultRating = '" + defaultRating + '\'' + 
			",discription_str = '" + discriptionStr + '\'' + 
			",_version_ = '" + version + '\'' + 
			",name = '" + name + '\'' + 
			",attribute.materials = '" + attributeMaterials + '\'' + 
			",id = '" + id + '\'' + 
			",defaultMerchantName = '" + defaultMerchantName + '\'' + 
			",attribute.materials_str = '" + attributeMaterialsStr + '\'' + 
			",images_str = '" + imagesStr + '\'' + 
			",category = '" + category + '\'' + 
			",stock = '" + stock + '\'' + 
			",category_str = '" + categoryStr + '\'' + 
			",attribute.width = '" + attributeWidth + '\'' + 
			",brand = '" + brand + '\'' + 
			"}";
		}
}