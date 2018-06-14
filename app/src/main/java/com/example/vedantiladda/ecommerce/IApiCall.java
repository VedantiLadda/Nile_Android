package com.example.vedantiladda.ecommerce;

import com.example.vedantiladda.ecommerce.model.ProductDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IApiCall {

    //calls....................

    @GET("/cart/getAllCartItems/{userId}")// we need to fetch the details via user id, we get list of products added in the cart.
    Call <List<String>> getCartId(@Path("userId") String userId);


    @DELETE("/cart/deleteFromCart/{userId}/{productId}")
    Call <Boolean> removeProductCartId(@Path("userId") String userId,@Path("productId") String productId);

    //add item to te cart need to send user id and product id

    @GET("/cart/addToCart/{userId}/{productId}/{merchantId}")
    Call <Void> addProduct(@Path("userId") String userId,@Path("productId") String productId,@Path("merchantId") String merchantId);

    @POST("/product/getProductsByIds")
    Call <List<ProductDTO>> getCartProducts(@Body List<String> stringList);




//    @GET("buy....../{userId}") //to fetch the product .. i think we can get info frm cart only...
//    Call <List<Product>> getCartId(@Path("userId") String userId);


}
