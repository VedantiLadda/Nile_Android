package com.example.vedantiladda.ecommerce.product;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
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
import com.example.vedantiladda.ecommerce.model.DocsItem;
import com.example.vedantiladda.ecommerce.model.MerchantDTO;
import com.example.vedantiladda.ecommerce.model.ProductDTO;
import com.example.vedantiladda.ecommerce.model.SearchResponse;
import com.example.vedantiladda.ecommerce.search.SearchAPI;

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
    private String url = "http://10.177.2.196:8080";
    private static String URL="http://10.177.2.201:8983";
    OkHttpClient client = new OkHttpClient.Builder().build();
    final Retrofit retrofitSearch = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();
    final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();
    String userId;
    Integer quantity;

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


        Spinner dropdown = findViewById(R.id.productQuantity);

        Integer[] items = new Integer[]{1, 2, 3,4,5};

        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object item = adapterView.getItemAtPosition(i);
                if(item!=null){
                    quantity = (Integer)item;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                quantity = 1;
            }
        });

        productId = getIntent().getExtras().getString("productId");
        getProductDetails(productId);

        mRecyclerView = findViewById(R.id.merchantRecycler);

        mRecyclerView.setHasFixedSize(true);


        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ProductDetailsAdaptor(merchantList, ProductDetailsActivity.this);
        mRecyclerView.setAdapter(mAdapter);

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
            Log.d("API",id);
            cart.putExtra("productId", productId);
            cart.putExtra("merchantId", id);
            cart.putExtra("price",Double.parseDouble(price));
            cart.putExtra("quantity", quantity);
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
                System.out.println("api"+product.toString());
                merchantList.clear();

                merchantList .addAll(product.getMerchantList());
                mAdapter.notifyDataSetChanged();

                final TextView name = findViewById(R.id.productName);
                final TextView brand = findViewById(R.id.brand);
                final TextView rating = findViewById(R.id.rating);
                final TextView stock = findViewById(R.id.stock);
                final TextView description = findViewById(R.id.description);
                final TextView height = findViewById(R.id.heightContent);
                final TextView weight = findViewById(R.id.weightContent);
                final TextView colour = findViewById(R.id.colourContent);
                final TextView width = findViewById(R.id.widthContent);
                final TextView material = findViewById(R.id.materialContent);
                final ImageView image = findViewById(R.id.productImage);
                final TextView price = findViewById(R.id.price);

                name.setText(product.getName());
               // brand.setText("By: " + product.getBrand());
                if(product.getDefaultMerchantPrice()!=null){
                    price.setText("Rs. " + product.getDefaultMerchantPrice().toString());
                }

                rating.setText("Rating:  " + product.getDefaultRating().toString()+ "/5");
                if(product.getStock()>0) {
                    stock.setText("In Stock");
                }
                description.setText(product.getDiscription());

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
                        if(key.equals("color")){
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
//    void getProductListFromSorl(String category){
//        SearchAPI searchAPI=retrofitSearch.create(SearchAPI.class);
//        String query="category:"+category;
//        Call<SearchResponse> list=searchAPI.search(query,"json");
//        list.enqueue(new Callback<SearchResponse>() {
//            @Override
//            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
//                System.out.println("call"+response.body());
//                mAdapter = new ProductListAdaptor(productList, ProductListActivity.this);
//                mRecyclerView.setAdapter(mAdapter);
//
//                for (DocsItem docsItem:response.body().getResponse().getDocs()){
//                    ProductDTO productDTO=new ProductDTO();
//                    productDTO.setBrand(docsItem.getBrand().get(0));
//                    productDTO.setCategory(docsItem.getCategory().get(0));
//                    productDTO.setImages(docsItem.getImages());
//                    productDTO.setId(docsItem.getId());
//                    productDTO.setName(docsItem.getName().get(0));
//                    productDTO.setDefaultMerchantPrice(docsItem.getDefaultMerchantPrice().get(0).doubleValue());
//                    productDTO.setDiscription(docsItem.getDiscription().get(0));
//                    productDTO.setDiscription(docsItem.getDiscription().get(0));
//                    Log.d("API",productDTO.toString());
//                    productList.add(productDTO);
//                }
//                mAdapter.notifyDataSetChanged();
//
//                //   Log.d("API","success"+response.body().getResponse().getDocs().get(0));
//            }
//
//            @Override
//            public void onFailure(Call<SearchResponse> call, Throwable t) {
//                Log.d("API","failed"+t.getMessage());
//
//            }
//        });
//
//    }

}
