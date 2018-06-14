package com.example.vedantiladda.ecommerce.model;

import java.util.List;
import java.util.Map;

public class ProductDTO {
    private String productId;
    private String productName;
    private String category;
    private List<MerchantDTO> merchants;
    private String description;

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    private String discription;
    private List<Map> attribute;
    private Integer stock;
    private List<String> images;
    private Integer price;
    private String brand;


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<MerchantDTO> getMerchants() {
        return merchants;
    }

    public void setMerchants(List<MerchantDTO> merchants) {
        this.merchants = merchants;
    }

    public String getDescription() {
        return getDiscription();
    }

    public void setDescription(String description) {
        setDiscription(description);
    }

    public List<Map> getAttribute() {
        return attribute;
    }

    public void setAttribute(List<Map> attribute) {
        this.attribute = attribute;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
