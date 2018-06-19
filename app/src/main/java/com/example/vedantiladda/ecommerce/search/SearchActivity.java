package com.example.vedantiladda.ecommerce.search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.example.vedantiladda.ecommerce.model.DocsItem;
import com.example.vedantiladda.ecommerce.model.ProductDTO;
import com.example.vedantiladda.ecommerce.model.SearchResponse;
import com.example.vedantiladda.ecommerce.product.ProductDetailsActivity;
import com.example.vedantiladda.ecommerce.product.ProductListActivity;
import com.example.vedantiladda.ecommerce.product.ProductListAdaptor;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends BaseActivity  implements ProductListAdaptor.ProductListCommunicator{

    private static String URL="http://10.177.2.201:8983";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    List<ProductDTO> productDTOList=new ArrayList<ProductDTO>();
    OkHttpClient client = new OkHttpClient.Builder().build();
    final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();


    @Override
    public void onClickTextView(String id) {
        Intent i = new Intent(SearchActivity.this, ProductDetailsActivity.class);
        i.putExtra("productId", id);
        startActivity(i);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        final Intent login = new Intent(SearchActivity.this, LoginActivity.class);
        final Intent cartActivity = new Intent(SearchActivity.this, Cart_Activity.class);
        final Intent userPage = new Intent(SearchActivity.this, LogoutActivity.class);
        toolbarButtons(login,cartActivity,userPage);

        // use a linear layout manager


        Button home = findViewById(R.id.ProductListHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchActivity = new Intent(SearchActivity.this, LaunchActivity.class);
                startActivity(launchActivity);

            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.ProductListRecycler);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(SearchActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        GetProductList();
        mAdapter = new ProductListAdaptor(productDTOList, SearchActivity.this);
        mRecyclerView.setAdapter(mAdapter);



    }

    public void GetProductList(){
        String search=getIntent().getStringExtra("search");
        Log.d("search",search);
        SearchAPI searchAPI=retrofit.create(SearchAPI.class);
        String query="name:"+search+" OR "+"brand:"+search+" OR "+"discription:"+search+" OR category:"+search;
        Call<SearchResponse> list=searchAPI.search(query,"json");
        list.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                System.out.println(response.body());
                productDTOList.clear();
                for (DocsItem docsItem:response.body().getResponse().getDocs()){
                    ProductDTO productDTO=new ProductDTO();
                    productDTO.setBrand(docsItem.getBrand().get(0));
                    productDTO.setCategory(docsItem.getCategory().get(0));
                    productDTO.setImages(docsItem.getImages());
                    productDTO.setId(docsItem.getId());
                    productDTO.setName(docsItem.getName().get(0));
                    productDTO.setDefaultMerchantPrice(docsItem.getDefaultMerchantPrice().get(0).doubleValue());
                    productDTO.setDiscription(docsItem.getDiscription().get(0));
                    productDTO.setDiscription(docsItem.getDiscription().get(0));
                    Log.d("API",productDTO.toString());
                    productDTOList.add(productDTO);
                }
                mAdapter.notifyDataSetChanged();

                //   Log.d("API","success"+response.body().getResponse().getDocs().get(0));
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                Log.d("API","failed"+t.getMessage());

            }
        });

    }
}

