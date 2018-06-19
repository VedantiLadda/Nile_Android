package com.example.vedantiladda.ecommerce.model;

public class MerchantDTO {

    @Override
    public String toString() {
        return "MerchantDTO{" +
                "id='" + mId + '\'' +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", price=" + price +
                '}';
    }

    public String mId;
    public String name;
    public Integer rating;
    public Integer price;


    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
