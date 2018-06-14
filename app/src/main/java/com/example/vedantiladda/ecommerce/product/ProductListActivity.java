package com.example.vedantiladda.ecommerce.product;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.vedantiladda.ecommerce.IApiCall;
import com.example.vedantiladda.ecommerce.LaunchActivity;
import com.example.vedantiladda.ecommerce.R;
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

public class ProductListActivity extends AppCompatActivity implements ProductListAdaptor.ProductListCommunicator{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<ProductDTO> productList = new ArrayList<>();
    OkHttpClient client = new OkHttpClient.Builder().build();
    final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.177.1.76:8080/employee/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();
    String category;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        category = getIntent().getExtras().getString("categoryId");
        mRecyclerView = (RecyclerView) findViewById(R.id.ProductListRecycler);



        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new ProductListAdaptor(productList, ProductListActivity.this);
        mRecyclerView.setAdapter(mAdapter);
        GetProductList(category);



    }

    @Override
    public void onClickTextView(String id) {

    }

    public void GetProductList(String categoryId){

        ProductDTO product1 = new ProductDTO();
        ProductDTO product2 = new ProductDTO();
        ProductDTO product3 = new ProductDTO();
        ProductDTO product4 = new ProductDTO();
        ProductDTO product5 = new ProductDTO();

        product1.setPrice("100");
        product1.setProductId("1");
        product1.setProductName("Phone1");
        product1.setProductDescription("Samsung Phone");
        product1.setImageURL("https://www.static-src.com/wcsstore/Indraprastha/images/catalog/medium/MTA-2165006/samsung_samsung-galaxy-j7-duo-smartphone---gold--32gb--3gb--o-_full09.jpg");
        product1.setCategoryId("1");

        product2.setPrice("100");
        product2.setProductId("1");
        product2.setProductName("Phone1");
        product2.setProductDescription("Samsung Phone");
        product2.setImageURL("https://www.static-src.com/wcsstore/Indraprastha/images/catalog/medium/MTA-2165006/samsung_samsung-galaxy-j7-duo-smartphone---gold--32gb--3gb--o-_full09.jpg");
        product2.setCategoryId("1");

        product3.setPrice("100");
        product3.setProductId("1");
        product3.setProductName("Phone1");
        product3.setProductDescription("Samsung Phone");
        product3.setImageURL("https://www.static-src.com/wcsstore/Indraprastha/images/catalog/medium/MTA-2165006/samsung_samsung-galaxy-j7-duo-smartphone---gold--32gb--3gb--o-_full09.jpg");
        product3.setCategoryId("1");

        product4.setPrice("100");
        product4.setProductId("1");
        product4.setProductName("Phone1");
        product4.setProductDescription("Samsung Phone");
        product4.setImageURL("https://www.static-src.com/wcsstore/Indraprastha/images/catalog/medium/MTA-2165006/samsung_samsung-galaxy-j7-duo-smartphone---gold--32gb--3gb--o-_full09.jpg");
        product4.setCategoryId("1");

        product5.setPrice("100");
        product5.setProductId("1");
        product5.setProductName("Phone1");
        product5.setProductDescription("Samsung Phone");
        product5.setImageURL("https://www.static-src.com/wcsstore/Indraprastha/images/catalog/medium/MTA-2165006/samsung_samsung-galaxy-j7-duo-smartphone---gold--32gb--3gb--o-_full09.jpg");
        product5.setCategoryId("1");
        productList.clear();

        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        productList.add(product4);
        productList.add(product5);
        mAdapter.notifyDataSetChanged();




//        IApiCall iApiCall = retrofit.create(IApiCall.class);
//        final Call<List<ProductDTO>> getAllCall = iApiCall.getAllProducts(categoryId);
//        getAllCall.enqueue(new Callback<List<ProductDTO>>() {
//            @Override
//            public void onResponse(Call<List<ProductDTO>> call, Response<List<ProductDTO>> response) {
//                productList.clear();
//                productList.addAll(response.body());
//                mAdapter.notifyDataSetChanged();
//                Toast.makeText(ProductListActivity.this, "received", Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onFailure(Call<List<ProductDTO>> call, Throwable t) {
//                Toast.makeText(ProductListActivity.this, "failed", Toast.LENGTH_LONG).show();
//
//            }
//
//        });

    }
}
