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

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Cart_Activity extends AppCompatActivity implements CartInterface{
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
        mAdapter = new CartAdaptor(productList,Cart_Activity.this);
        mRecyclerView.setAdapter(mAdapter);

        Button buy =(Button) findViewById(R.id.buy);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("application", "onClick of checkout button: ");
                Intent intent = new Intent(Cart_Activity.this, BuyActivity.class);
                Log.i("application", "onClick: next step is to start the intent ");
                startActivity(intent);
            }
        });


        IApiCall iApiCall = retrofit.create(IApiCall.class);
        final Call<List<Product>> getCartId = iApiCall.getCartId(userId);

//        Product product1=new Product();
//        product1.setBrand("Samsung1");
//        product1.setProductName("Galaxy61");
//        product1.setProductprice("57831.00");
//        product1.setImageUrl("https://www.static-src.com/wcsstore/Indraprastha/images/catalog/medium/MTA-2165006/samsung_samsung-galaxy-j7-duo-smartphone---gold--32gb--3gb--o-_full09.jpg");
//        Product product2=new Product();
//        product2.setBrand("Samsung2");
//        product2.setProductName("Galaxy62");
//        product2.setProductprice("57832.00");
//        product2.setImageUrl("https://www.static-src.com/wcsstore/Indraprastha/images/catalog/medium/MTA-2165006/samsung_samsung-galaxy-j7-duo-smartphone---gold--32gb--3gb--o-_full09.jpg");
//
//        Product product3=new Product();
//        product3.setBrand("Samsung3");
//        product3.setProductName("Galaxy63");
//        product3.setProductprice("57833.00");
//        product3.setImageUrl("https://www.static-src.com/wcsstore/Indraprastha/images/catalog/medium/MTA-2165006/samsung_samsung-galaxy-j7-duo-smartphone---gold--32gb--3gb--o-_full09.jpg");
//
//        Product product4=new Product();
//        product4.setBrand("Samsung4");
//        product4.setProductName("Galaxy64");
//        product4.setProductprice("578344.00");
//        product4.setImageUrl("https://www.static-src.com/wcsstore/Indraprastha/images/catalog/medium/MTA-2165006/samsung_samsung-galaxy-j7-duo-smartphone---gold--32gb--3gb--o-_full09.jpg");
//
//        productList.clear();
//        productList.add(product1);
//        productList.add(product2);
//        productList.add(product3);
//        productList.add(product4);
//        mAdapter.notifyDataSetChanged();

        //product.setImageUrl(https://www.google.com/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=2ahUKEwipi_yjqdDbAhVGs48KHdUuCl8QjRx6BAgBEAU&url=https%3A%2F%2Fgadgets.ndtv.com%2Fsamsung-galaxy-j6-5454&psig=AOvVaw1NUnggLIf4Bvnn2RI6ivfs&ust=1528968398070531);

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

    @Override
    public void onClickRemove(String productId) {

        IApiCall iApiCall = retrofit.create(IApiCall.class);
        final Call<Boolean> removeProduct = iApiCall.removeProductId(productId);
        removeProduct.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {

                //if the response is true . this says the product is deleted.
                //check if the condition checking is right
                if (response.body()){
                    //the response body is true..
                    //refresh the cart view again
                    IApiCall iApiCall1 = retrofit.create(IApiCall.class);
                    final Call<List<Product>> getCartId = iApiCall1.getCartId(userId);
                    // this is the call to fet the cart product list
                    getCartId.enqueue(new Callback<List<Product>>() {
                        @Override
                        public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                            productList.clear();
                            productList.addAll(response.body());
                            mAdapter.notifyDataSetChanged();
                            Toast.makeText(Cart_Activity.this, "passed to refresh the cart products", Toast.LENGTH_LONG).show();


                        }

                        @Override
                        public void onFailure(Call<List<Product>> call, Throwable t) {
                            Toast.makeText(Cart_Activity.this, "failed to refresh the cart products", Toast.LENGTH_LONG).show();


                        }
                    });



                }

            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(Cart_Activity.this, "failed to remove the product", Toast.LENGTH_SHORT).show();

            }
        });

//
    }
}
