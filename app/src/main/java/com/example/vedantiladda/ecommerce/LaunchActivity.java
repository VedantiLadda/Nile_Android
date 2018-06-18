package com.example.vedantiladda.ecommerce;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.vedantiladda.ecommerce.LoginAndSignup.LoginActivity;
import com.example.vedantiladda.ecommerce.LogoutAndEditProfile.LogoutActivity;
import com.example.vedantiladda.ecommerce.cart.Cart_Activity;
import com.example.vedantiladda.ecommerce.model.Category;
import com.example.vedantiladda.ecommerce.product.ProductListActivity;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LaunchActivity extends BaseActivity implements LaunchAdaptor.LaunchCommunicator{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Category> categoryList = new ArrayList<>();
    private String url = "http://10.177.2.201:8080";
    OkHttpClient client = new OkHttpClient.Builder().build();
    final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();
//    String LoginStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        final Intent login = new Intent(LaunchActivity.this, LoginActivity.class);
        final Intent cartActivity = new Intent(LaunchActivity.this, Cart_Activity.class);
        final Intent userPage = new Intent(LaunchActivity.this, LogoutActivity.class);
        toolbarButtons(login, cartActivity, userPage);
        mRecyclerView = (RecyclerView) findViewById(R.id.CategoryRecycler);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
//        mLayoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(mLayoutManager);

        mLayoutManager = new GridLayoutManager(this, 2, 1, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        // specify an adapter (see also next example)
        mAdapter = new LaunchAdaptor(categoryList, LaunchActivity.this);
        mRecyclerView.setAdapter(mAdapter);

        GetCategory();
//        Category category1 = new Category();
//        category1.setCategoryId("1");
//        category1.setCategoryName("Phones");
//        Category category2 = new Category();
//        category2.setCategoryId("2");
//        category2.setCategoryName("Laptops");
//        Category category3 = new Category();
//        category3.setCategoryId("3");
//        category3.setCategoryName("TVs");
//        Category category4 = new Category();
//        category4.setCategoryId("4");
//        category4.setCategoryName("Headphones");
//        Category category5 = new Category();
//        category5.setCategoryId("5");
//        category5.setCategoryName("Speakers");
//        categoryList.add(category1);
//        categoryList.add(category2);
//        categoryList.add(category3);
//        categoryList.add(category4);
//        categoryList.add(category5);



    }

    @Override
    public void onClickTextView(String name) {
        Intent i = new Intent(LaunchActivity.this, ProductListActivity.class);
        i.putExtra("categoryName", name);
        startActivity(i);


    }

    public void GetCategory(){

        IApiCall iApiCall = retrofit.create(IApiCall.class);
        final Call<List<Category>> getAllCall = iApiCall.getAllCategories();
        getAllCall.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                categoryList.clear();
                categoryList.addAll(response.body());
                mAdapter.notifyDataSetChanged();
                Toast.makeText(LaunchActivity.this, "received", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.d("API",t.getMessage());
                Toast.makeText(LaunchActivity.this, "failed", Toast.LENGTH_LONG).show();

            }

        });
    }
}
