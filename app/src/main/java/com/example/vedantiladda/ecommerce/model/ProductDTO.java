package com.example.vedantiladda.ecommerce.model;

import java.util.List;
import java.util.Map;

public class ProductDTO {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<MerchantDTO> getMerchantList() {
        return merchantList;
    }

    public void setMerchantList(List<MerchantDTO> merchantList) {
        this.merchantList = merchantList;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public List<Map> getAttribute() {
        return attribute;
    }

    public void setAttribute(List<Map> attribute) {
        this.attribute = attribute;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getDefaultMerchantName() {
        return defaultMerchantName;
    }

    public void setDefaultMerchantName(String defaultMerchantName) {
        this.defaultMerchantName = defaultMerchantName;
    }

    public Double getDefaultMerchantPrice() {
        return defaultMerchantPrice;
    }

    public void setDefaultMerchantPrice(Double defaultMerchantPrice) {
        this.defaultMerchantPrice = defaultMerchantPrice;
    }

    public Double getDefaultRating() {
        return defaultRating;
    }

    public void setDefaultRating(Double defaultRating) {
        this.defaultRating = defaultRating;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    private String id;
    private String name;
    private String category;
    private List<MerchantDTO> merchantList;
    private String discription;
    private List<Map> attribute;
    private String brand;
    private List<String> images;
    private String defaultMerchantName;
    private Double defaultMerchantPrice;
    private Double defaultRating;
    private Integer stock;

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", merchantList=" + merchantList +
                ", discription='" + discription + '\'' +
                ", attribute=" + attribute +
                ", brand='" + brand + '\'' +
                ", images=" + images +
                ", defaultMerchantName='" + defaultMerchantName + '\'' +
                ", defaultMerchantPrice=" + defaultMerchantPrice +
                ", defaultRating=" + defaultRating +
                ", Stock=" + stock +
                ", quantity=" + quantity +
                '}';
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    private Integer quantity;
}
