package com.example.vedantiladda.ecommerce;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IApiCall {

    //calls....................

    @GET("/cart/getCartItems/{userId}")// we need to fetch the details via user id, we get list of products added in the cart.
    Call <List<Product>> getCartId(@Path("userId") String userId);

    @GET("/cart/removeProduct/{productId}")
    Call <Boolean> removeProductId(@Path("productId") String productId);

//    @GET("buy....../{userId}") //to fetch the product .. i think we can get info frm cart only...
//    Call <List<Product>> getCartId(@Path("userId") String userId);


}
