package com.example.vedantiladda.ecommerce;

import com.example.vedantiladda.ecommerce.model.Category;
import com.example.vedantiladda.ecommerce.model.ProductDTO;

import java.util.List;
import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.Path;

public interface IApiCall {
    @GET("/product/getAllCategories/getAll")
    Call<List<Category>> getAllCategories();

    @GET("/product/getProductDetailsById/{productId}")
    Call<ProductDTO> getProductDetails(@Path("productId") String productId);

    @GET("/product/getProductsByCategory/{category}" )
    Call<List<ProductDTO>> getAllProducts(@Path("category") String category);

}
