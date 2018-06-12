package com.example.vedantiladda.ecommerce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Cart_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_);

        // we will get the user id.. check if there is user id in shared preferences... assign it to the string initialized
        String userId;

        OkHttpClient client = new OkHttpClient.Builder().build();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.177.1.76:8080/employee/") // need to change the url
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        IApiCall iApiCall = retrofit.create(IApiCall.class);
        //final Call<List<ProductDTO>> getCartId = iApiCall.getCartId();
        // this is the call to fet the cart product list






    }
}
