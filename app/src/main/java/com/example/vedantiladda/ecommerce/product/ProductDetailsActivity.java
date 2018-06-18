package com.example.vedantiladda.ecommerce.product;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.vedantiladda.ecommerce.BaseActivity;
import com.example.vedantiladda.ecommerce.IApiCall;
import com.example.vedantiladda.ecommerce.LaunchActivity;
import com.example.vedantiladda.ecommerce.LoginAndSignup.LoginActivity;
import com.example.vedantiladda.ecommerce.LogoutAndEditProfile.LogoutActivity;
import com.example.vedantiladda.ecommerce.R;
import com.example.vedantiladda.ecommerce.cart.AddCartActivity;
import com.example.vedantiladda.ecommerce.cart.Cart_Activity;
import com.example.vedantiladda.ecommerce.model.MerchantDTO;
import com.example.vedantiladda.ecommerce.model.ProductDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductDetailsActivity extends BaseActivity implements ProductDetailsAdaptor.MerchantCommunicator {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    String productId;
    private List<MerchantDTO> merchantList = new ArrayList<>();
    private ProductDTO product = new ProductDTO();
    private String url = "http://10.177.2.201:8080";
    OkHttpClient client = new OkHttpClient.Builder().build();
    final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        userId = sharedPreferences.getString("userId", " ");

        final Intent login = new Intent(ProductDetailsActivity.this, LoginActivity.class);
        final Intent cartActivity = new Intent(ProductDetailsActivity.this, Cart_Activity.class);
        final Intent userPage = new Intent(ProductDetailsActivity.this, LogoutActivity.class);
        toolbarButtons(login,cartActivity,userPage);

        productId = getIntent().getExtras().getString("productId");
        getProductDetails(productId);

        mRecyclerView = (RecyclerView) findViewById(R.id.merchantRecycler);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new ProductDetailsAdaptor(merchantList, ProductDetailsActivity.this);
        mRecyclerView.setAdapter(mAdapter);

//        final TextView name = findViewById(R.id.name);
//        final TextView brand = findViewById(R.id.brand);
//        final TextView rating = findViewById(R.id.rating);
//        final TextView stock = findViewById(R.id.stock);
//        final TextView description = findViewById(R.id.description);
//        final TextView attributes = findViewById(R.id.attributes);
//        final ImageView image = findViewById(R.id.productImage);
//
//        name.setText(product.getProductName());
//        brand.setText(product.getBrand());
//        //rating.setText(product.getMerchants().get(0).getRating());
//        stock.setText(product.getStock().toString());
//        description.setText(product.getDescription());
//        attributes.setText(product.getAttribute().toString());
//        Glide.with(image.getContext()).load(product.getImages().get(0))
//                .thumbnail(0.5f)
//                .crossFade()
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(image);
    }

    @Override
    public void onClickButton(String id, String price ) {
        Intent cart = new Intent(ProductDetailsActivity.this, AddCartActivity.class);
        Intent loginIntent = new Intent (ProductDetailsActivity.this, LoginActivity.class);
        if(userId.equals(" ")){
            loginIntent.putExtra("productId", productId);
            startActivity(loginIntent);
        }
        else{
            cart.putExtra("productId", productId);
            cart.putExtra("merchantId", id);
            cart.putExtra("price",price );
            startActivity(cart);
        }
    }

    public void getProductDetails(String id){
        Log.d("API","call");
        IApiCall iApiCall = retrofit.create(IApiCall.class);
        final Call<ProductDTO> getAllCall = iApiCall.getProductDetails(id);
        getAllCall.enqueue(new Callback<ProductDTO>() {
            @Override
            public void onResponse(Call<ProductDTO> call, Response<ProductDTO> response) {
                product = response.body();
                System.out.println(product.getMerchants());
                merchantList.clear();
                Log.d("API",product.getMerchants().get(0).getName());
                merchantList .addAll(product.getMerchants());
                mAdapter.notifyDataSetChanged();

                final TextView name = findViewById(R.id.name);
                final TextView brand = findViewById(R.id.brand);
                //final TextView rating = findViewById(R.id.rating);
                final TextView stock = findViewById(R.id.stock);
                final TextView description = findViewById(R.id.descriptionContent);
                final TextView height = findViewById(R.id.heightContent);
                final TextView weight = findViewById(R.id.weightContent);
                final TextView colour = findViewById(R.id.colourContent);
                final TextView width = findViewById(R.id.widthContent);
                final TextView material = findViewById(R.id.materialContent);
                final ImageView image = findViewById(R.id.productImage);
                final TextView price = findViewById(R.id.price);

                name.setText(product.getProductName());
                brand.setText("By: " + product.getBrand());
                price.setText("Rs. " + product.getPrice().toString());
                //rating.setText(product.getMerchants().get(0).getRating());
                if(product.getStock()>0) {
                    stock.setText("In Stock");
                }
                description.setText(product.getDescription());

                List<Map> list = product.getAttribute(); // this is what you have already

                for (Map<String, Object> map : list) {
                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                        String key = entry.getKey();
                        Object value = entry.getValue();
                        if(key.equals("height")){
                            height.setText(value.toString());
                        }
                        if(key.equals("width")){
                            width.setText(value.toString());
                        }
                        if(key.equals("weight")){
                            weight.setText(value.toString());
                        }
                        if(key.equals("colour")){
                            colour.setText(value.toString());
                        }
                        if(key.equals("materials")){
                            material.setText(value.toString());
                        }

                    }
                }

                Glide.with(image.getContext()).load(product.getImages().get(0))
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(image);
                Toast.makeText(ProductDetailsActivity.this, "received", Toast.LENGTH_LONG).show();



            }

            @Override
            public void onFailure(Call<ProductDTO> call, Throwable t) {
                Toast.makeText(ProductDetailsActivity.this, "failed", Toast.LENGTH_LONG).show();

            }

        });

    }
}
