package com.example.vedantiladda.ecommerce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Cart_Activity extends AppCompatActivity {
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
        setContentView(R.layout.recyclerview);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview1);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new CartAdaptor(productList,Cart_Activity.this);
        mRecyclerView.setAdapter(mAdapter);


        // we will get the user id.. check if there is user id in shared preferences... assign it to the string initialized
        String userId;


        IApiCall iApiCall = retrofit.create(IApiCall.class);
        final Call<List<Product>> getCartId = iApiCall.getCartId(userId);
        // this is the call to fet the cart product list
        getCartId.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                productList.clear();
                productList.addAll(response.body());
                mAdapter.notifyDataSetChanged();
                Toast.makeText(Cart_Activity.this, "passed", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(Cart_Activity.this, "failed to display the list", Toast.LENGTH_LONG).show();

            }
        });


    }
}
