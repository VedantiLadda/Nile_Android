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
import android.widget.TextView;
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

public class BuyActivity extends AppCompatActivity implements BuyInterface {
//    SharedPreferences sharedPreferences = getSharedPreferences("training", Context.MODE_PRIVATE);
//    String userId = sharedPreferences.getString("userId",null);
    static String url2="http://10.177.2.196:8080";

    String userId ="049bb238-11bc-4269-bd7e-b35133b765f3";

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<ProductDTO> productList = new ArrayList<>();
    OkHttpClient client = new OkHttpClient.Builder().build();

    final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.177.2.196:8080") // need to change the url
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();

    final Retrofit retrofit2 = new Retrofit.Builder()
            .baseUrl("http://10.177.2.201:8080") // need to change the url
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_layout);
        Log.i("application", "onCreate: of buy activity ");


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview2);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new BuyAdaptor(productList,BuyActivity.this);
        mRecyclerView.setAdapter(mAdapter);
        ///added this part
//        Product product1=new Product();
//        product1.setProductName("Galaxy61");
//        product1.setProductprice("57831.00");
//        Product product2=new Product();
//        product2.setProductName("Galaxy62");
//        product2.setProductprice("57832.00");
//
//        Product product3=new Product();
//        product3.setProductName("Galaxy63");
//        product3.setProductprice("57833.00");
//
//        Product product4=new Product();
//        product4.setProductName("Galaxy64");
//        product4.setProductprice("578344.00");
//
//        productList.clear();
//        productList.add(product1);
//        productList.add(product2);
//        productList.add(product3);
//        productList.add(product4);
//        mAdapter.notifyDataSetChanged();
//till here

        Button contShopping = (Button) findViewById(R.id.continueshop);
        contShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //onclicking it should go to launcher page
                Intent intent = new Intent(BuyActivity.this,LaunchActivity.class);
                startActivity(intent);
            }
        });
        url2="http://10.177.2.196:8080";
        IApiCall iApiCall = retrofit.create(IApiCall.class);
        final Call<List<String>> getCartId = iApiCall.getCartId(userId);
        // this is the call to fet the cart product list
        getCartId.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                url2 = "http://10.177.2.201:8080";
                IApiCall iApiCall = retrofit2.create(IApiCall.class);
                final Call<List<ProductDTO>> getCartProducts = iApiCall.getCartProducts(response.body());
                getCartProducts.enqueue(new Callback<List<ProductDTO>>() {
                    @Override
                    public void onResponse(Call<List<ProductDTO>> call, Response<List<ProductDTO>> response) {
                        productList.clear();
                        productList.addAll(response.body());
                        mAdapter.notifyDataSetChanged();
                   //     TextView txtTotalPrice;
                     //   TextView txtPrice;
                       // txtTotalPrice = (TextView) findViewById(R.id.totalPrice);
                       // txtPrice = (TextView) findViewById(R.id.price);

                     //   txtPrice.setText(""+BuyAdaptor.priceSum);
                       // txtTotalPrice.setText("Total Price");

                    }

                    @Override
                    public void onFailure(Call<List<ProductDTO>> call, Throwable t) {

                    }
                });

            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

            }
        });



    }
}
