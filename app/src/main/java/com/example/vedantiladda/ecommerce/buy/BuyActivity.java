package com.example.vedantiladda.ecommerce.buy;

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

import com.example.vedantiladda.ecommerce.BaseActivity;
import com.example.vedantiladda.ecommerce.IApiCall;
import com.example.vedantiladda.ecommerce.LaunchActivity;
import com.example.vedantiladda.ecommerce.LoginAndSignup.LoginActivity;
import com.example.vedantiladda.ecommerce.LogoutAndEditProfile.LogoutActivity;
import com.example.vedantiladda.ecommerce.R;
import com.example.vedantiladda.ecommerce.cart.Cart_Activity;
import com.example.vedantiladda.ecommerce.model.OrderNoDTO;
import com.example.vedantiladda.ecommerce.model.ProductDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BuyActivity extends BaseActivity implements BuyInterface {


    //String userId ="049bb238-11bc-4269-bd7e-b35133b765f3";

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<ProductDTO> productList = new ArrayList<>();
    OkHttpClient client = new OkHttpClient.Builder().build();

    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.177.2.196:8089") // need to change the url
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build();

    final Retrofit retrofit2 = new Retrofit.Builder()
            .baseUrl("http://10.177.2.196:8080") // need to change the url
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();

    String userId, emailId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_layout);
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        userId = sharedPreferences.getString("userId", null);
        emailId = sharedPreferences.getString("email", null);
        final Intent login = new Intent(BuyActivity.this, LoginActivity.class);
        final Intent cartActivity = new Intent(BuyActivity.this, Cart_Activity.class);
        final Intent userPage = new Intent(BuyActivity.this, LogoutActivity.class);
        toolbarButtons(login, cartActivity, userPage);
        Log.i("application", "onCreate: of buy activity ");


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview2);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new BuyAdaptor(productList, BuyActivity.this);
        mRecyclerView.setAdapter(mAdapter);
        final TextView txtTotalPrice, txtPrice;
        txtTotalPrice = (TextView) findViewById(R.id.totalPrice);
        txtPrice = (TextView) findViewById(R.id.price);


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
                Intent intent = new Intent(BuyActivity.this, LaunchActivity.class);
                startActivity(intent);
            }
        });

        IApiCall iApiCall = retrofit.create(IApiCall.class);
        final Call<List<String>> getCartId = iApiCall.getCartId(userId);
        // this is the call to fet the cart product list
        getCartId.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {

                IApiCall iApiCall = retrofit2.create(IApiCall.class);
                final Call<List<ProductDTO>> getCartProducts = iApiCall.getCartProducts(response.body());
                getCartProducts.enqueue(new Callback<List<ProductDTO>>() {
                    @Override
                    public void onResponse(Call<List<ProductDTO>> call, Response<List<ProductDTO>> response) {
                        productList.clear();
                        productList.addAll(response.body());

                        mAdapter.notifyDataSetChanged();
                        IApiCall iApiCall1 = retrofit.create(IApiCall.class);
                        Log.d("API", "called");
                        final Call<String> getOrderId = iApiCall1.getOrderId(userId, emailId);
                        getOrderId.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                //set the text orderid
                                TextView orderId1 = (TextView) findViewById(R.id.orderId);
                                Log.d("APIL",response.body());
                                orderId1.setText(response.body());
                                Toast.makeText(BuyActivity.this, "success", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Log.d("APILE", t.getMessage());
                                System.out.println(t);
                                //   Toast.makeText(BuyActivity.this, "failed!", Toast.LENGTH_SHORT).show();


                            }
                        });
                        double sum = priceSum();
                        txtPrice.setText("" + sum);
                        txtTotalPrice.setText("Total Price");


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

    @Override
    public double priceSum() {
        //
        double sum = 0;
        for (ProductDTO productDTO : productList) {
            sum += productDTO.getDefaultMerchantPrice() ;
        }
        return sum;


    }
}