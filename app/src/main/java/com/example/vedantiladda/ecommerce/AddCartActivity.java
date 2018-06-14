package com.example.vedantiladda.ecommerce;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.vedantiladda.ecommerce.model.ProductDTO;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddCartActivity extends AppCompatActivity implements CartInterface{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<ProductDTO> productList = new ArrayList<>();
    OkHttpClient client = new OkHttpClient.Builder().build();

    final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.177.1.76:8080/employee/") // need to change the url
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();
    // we will get the user id.. check if there is user id in shared preferences... assign it to the string initialized
    SharedPreferences sharedPreferences = getSharedPreferences("training", Context.MODE_PRIVATE);
    String userId = sharedPreferences.getString("userId",null);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);
        Log.i("application", "onCreate:of cart activity ");
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview1);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new CartAdaptor(productList,AddCartActivity.this);
        mRecyclerView.setAdapter(mAdapter);

        Button buy =(Button) findViewById(R.id.buy);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("application", "onClick of checkout button: ");
                Intent intent = new Intent(AddCartActivity.this, BuyActivity.class);
                Log.i("application", "onClick: next step is to start the intent ");
                startActivity(intent);
            }
        });

        Intent intent1 = getIntent();
        String productId = intent1.getStringExtra("productId");
        String merchantId = intent1.getStringExtra("merchantId");


        IApiCall iApiCall = retrofit.create(IApiCall.class);
        final Call<Void> addProduct = iApiCall.addProduct(userId,productId,merchantId);
        addProduct.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i("application", "trying to refresh the view ");

                IApiCall iApiCall2 = retrofit.create(IApiCall.class);
                final Call<List<ProductDTO>> getAll = iApiCall2.getCartId(userId);
                getAll.enqueue(new Callback<List<ProductDTO>>() {
                    @Override
                    public void onResponse(Call<List<ProductDTO>> call, Response<List<ProductDTO>> response) {
                        productList.clear();
                        productList.addAll(response.body());
                        mAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onFailure(Call<List<ProductDTO>> call, Throwable t) {

                    }
                });

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });






    }

    @Override
    public void onClickRemove(String productId) {
        final  String userId1 = userId;

        IApiCall iApiCall = retrofit.create(IApiCall.class);
        final Call<Boolean> removeProduct = iApiCall.removeProductCartId(userId1,productId);
        removeProduct.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {

                //if the response is true . this says the product is deleted.
                //check if the condition checking is right
                if (response.body()){
                    //the response body is true..
                    //refresh the cart view again
                    IApiCall iApiCall1 = retrofit.create(IApiCall.class);
                    final Call<List<ProductDTO>> getCartId = iApiCall1.getCartId(userId1);
                    // this is the call to fet the cart product list
                    getCartId.enqueue(new Callback<List<ProductDTO>>() {
                        @Override
                        public void onResponse(Call<List<ProductDTO>> call, Response<List<ProductDTO>> response) {

                            productList.clear();
                            productList.addAll(response.body());
                            mAdapter.notifyDataSetChanged();
                            Toast.makeText(AddCartActivity.this, "passed to refresh the cart products", Toast.LENGTH_LONG).show();


                        }

                        @Override
                        public void onFailure(Call<List<ProductDTO>> call, Throwable t) {
                            Toast.makeText(AddCartActivity.this, "failed to refresh the cart products", Toast.LENGTH_LONG).show();


                        }
                    });



                }

            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(AddCartActivity.this, "failed to remove the product", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
