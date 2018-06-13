package com.example.vedantiladda.ecommerce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BuyActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Product> productList = new ArrayList<>();
    OkHttpClient client = new OkHttpClient.Builder().build();

    final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.177.1.76:8080/employee/") // need to change the url
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_layout);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview1);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new BuyAdaptor(productList,BuyActivity.this);
        mRecyclerView.setAdapter(mAdapter);
        IApiCall iApiCall = retrofit.create(IApiCall.class);


    }
}
