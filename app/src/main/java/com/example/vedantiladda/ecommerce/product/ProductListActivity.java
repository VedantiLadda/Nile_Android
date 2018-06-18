package com.example.vedantiladda.ecommerce.product;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.vedantiladda.ecommerce.BaseActivity;
import com.example.vedantiladda.ecommerce.IApiCall;
import com.example.vedantiladda.ecommerce.LaunchActivity;
import com.example.vedantiladda.ecommerce.LoginAndSignup.LoginActivity;
import com.example.vedantiladda.ecommerce.LogoutAndEditProfile.LogoutActivity;
import com.example.vedantiladda.ecommerce.R;
import com.example.vedantiladda.ecommerce.cart.Cart_Activity;
import com.example.vedantiladda.ecommerce.model.Category;
import com.example.vedantiladda.ecommerce.model.ProductDTO;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductListActivity extends BaseActivity implements ProductListAdaptor.ProductListCommunicator{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String url = "http://10.177.2.201:8080";
    private List<ProductDTO> productList = new ArrayList<>();
    OkHttpClient client = new OkHttpClient.Builder().build();
    final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();
    String category;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        final Intent login = new Intent(ProductListActivity.this, LoginActivity.class);
        final Intent cartActivity = new Intent(ProductListActivity.this, Cart_Activity.class);
        final Intent userPage = new Intent(ProductListActivity.this, LogoutActivity.class);
        toolbarButtons(login,cartActivity,userPage);

        Button home = findViewById(R.id.ProductListHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchActivity = new Intent(ProductListActivity.this, LaunchActivity.class);
                startActivity(launchActivity);

            }
        });
        category = getIntent().getExtras().getString("categoryName");
        mRecyclerView = (RecyclerView) findViewById(R.id.ProductListRecycler);



        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        GetProductList(category);

        // specify an adapter (see also next example)
        mAdapter = new ProductListAdaptor(productList, ProductListActivity.this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();




    }

    @Override
    public void onClickTextView(String id) {
        Intent i = new Intent(ProductListActivity.this, ProductDetailsActivity.class);
        i.putExtra("productId", id);
        startActivity(i);
    }

    public void GetProductList(String categoryname){
//        productList.clear();
//        ProductDTO product1 = new ProductDTO();
//        ProductDTO product2 = new ProductDTO();
//        ProductDTO product3 = new ProductDTO();
//        ProductDTO product4 = new ProductDTO();
//        ProductDTO product5 = new ProductDTO();
//
//        List<String> url = new ArrayList<>();
//        url.add("https://www.static-src.com/wcsstore/Indraprastha/images/catalog/med" +
//                "ium/MTA-2165006/samsung_samsung-galaxy-j7-duo-smartphone---gold--32gb--3gb--o-_full09.jpg");
//        product1.setPrice(100);
//        product1.setProductId("1");
//        product1.setProductName("Phone1");
//        product1.setDescription("Samsung Phone");
//        product1.setImages(url);
//        product1.setCategory("1");
//
//        product2.setPrice(100);
//        product2.setProductId("1");
//        product2.setProductName("Phone1");
//        product2.setDescription("Samsung Phone");
//        product2.setImages(url);
//        product2.setCategory("1");
//
//        product3.setPrice(100);
//        product3.setProductId("1");
//        product3.setProductName("Phone1");
//        product3.setDescription("Samsung Phone");
//        product3.setImages(url);
//        product3.setCategory("1");
//
//        product4.setPrice(100);
//        product4.setProductId("1");
//        product4.setProductName("Phone1");
//        product4.setDescription("Samsung Phone");
//        product4.setImages(url);
//        product4.setCategory("1");
//
//        product5.setPrice(100);
//        product5.setProductId("1");
//        product5.setProductName("Phone1");
//        product5.setDescription("Samsung Phone");
//        product5.setImages(url);
//        product5.setCategory("1");
//
//        productList.add(product1);
//        productList.add(product2);
//        productList.add(product3);
//        productList.add(product4);
//        productList.add(product5);
        //mAdapter.notifyDataSetChanged();




        IApiCall iApiCall = retrofit.create(IApiCall.class);
        final Call<List<ProductDTO>> getAllCall = iApiCall.getAllProducts(categoryname);
        getAllCall.enqueue(new Callback<List<ProductDTO>>() {
            @Override
            public void onResponse(Call<List<ProductDTO>> call, Response<List<ProductDTO>> response) {
                productList.clear();
                productList.addAll(response.body());
                mAdapter.notifyDataSetChanged();
                Toast.makeText(ProductListActivity.this, "received", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<ProductDTO>> call, Throwable t) {
                Toast.makeText(ProductListActivity.this, "failed", Toast.LENGTH_LONG).show();

            }

        });

    }
}
