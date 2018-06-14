package com.example.vedantiladda.ecommerce.product;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vedantiladda.ecommerce.IApiCall;
import com.example.vedantiladda.ecommerce.LaunchActivity;
import com.example.vedantiladda.ecommerce.R;
import com.example.vedantiladda.ecommerce.model.MerchantDTO;
import com.example.vedantiladda.ecommerce.model.ProductDTO;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductDetailsActivity extends AppCompatActivity implements ProductDetailsAdaptor.MerchantCommunicator {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    String productId;
    private List<MerchantDTO> merchantList = new ArrayList<>();
    private ProductDTO product = new ProductDTO();
    OkHttpClient client = new OkHttpClient.Builder().build();
    final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.177.1.76:8080/employee/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
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

        final TextView name = findViewById(R.id.name);
        final TextView brand = findViewById(R.id.brand);
        final TextView rating = findViewById(R.id.rating);
        final TextView stock = findViewById(R.id.stock);
        final TextView description = findViewById(R.id.description);
        final TextView attributes = findViewById(R.id.attributes);





    }

    @Override
    public void onClickTextView(String id) {
//        Intent i = new Intent(ProductDetailsActivity.this, AddCartActivity.class);
//        i.putExtra("productId", productId);
//        i.putExtra("merchantId", id);
//        startActivity(i);
    }

    public void getProductDetails(String id){
        IApiCall iApiCall = retrofit.create(IApiCall.class);
        final Call<ProductDTO> getAllCall = iApiCall.getProductDetails(id);
        getAllCall.enqueue(new Callback<ProductDTO>() {
            @Override
            public void onResponse(Call<ProductDTO> call, Response<ProductDTO> response) {
                product = response.body();
                merchantList = product.getMerchants();
                mAdapter.notifyDataSetChanged();
                Toast.makeText(ProductDetailsActivity.this, "received", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ProductDTO> call, Throwable t) {
                Toast.makeText(ProductDetailsActivity.this, "failed", Toast.LENGTH_LONG).show();

            }

        });

    }
}
