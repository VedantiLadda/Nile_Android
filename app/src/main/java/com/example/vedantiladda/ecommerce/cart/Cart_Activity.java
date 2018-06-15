package com.example.vedantiladda.ecommerce.cart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.vedantiladda.ecommerce.IApiCall;
import com.example.vedantiladda.ecommerce.R;
import com.example.vedantiladda.ecommerce.buy.BuyActivity;
import com.example.vedantiladda.ecommerce.model.ProductDTO;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Cart_Activity extends AppCompatActivity implements CartInterface {
    private RecyclerView mRecyclerView;
    static String url1="http://10.177.2.196:8080";
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

    // we will get the user id.. check if there is user id in shared preferences... assign it to the string initialized
//    SharedPreferences sharedPreferences = getSharedPreferences("training", Context.MODE_PRIVATE);
//    String userId = sharedPreferences.getString("userId",null);
String userId ="049bb238-11bc-4269-bd7e-b35133b765f3";


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

        url1="http://10.177.2.196:8080";
        IApiCall iApiCall = retrofit.create(IApiCall.class);
        final Call<List<String>> getCartId = iApiCall.getCartId(userId);

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
        getCartId.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                url1 = "http://10.177.2.201:8080";
                IApiCall iApiCall2 = retrofit2.create(IApiCall.class);
                Log.i("application", "onResponse: "+response.body());
                final Call<List<ProductDTO>> getCartProducts = iApiCall2.getCartProducts(response.body());
                getCartProducts.enqueue(new Callback<List<ProductDTO>>() {
                    @Override
                    public void onResponse(Call<List<ProductDTO>> call, Response<List<ProductDTO>> response) {

                        Log.i("application", "onResponse: "+response.body());
                        if(response.body() != null){
                            productList.clear();
                            productList.addAll(response.body());
                            mAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ProductDTO>> call, Throwable t) {
                        Log.i("application", "onFailure: ");

                    }
                });

            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

            }
        });


    }

    @Override
    public void onClickRemove(String productId) {
      final  String userId1 = userId;
     //   url1="http://10.177.2.196:8080";

        IApiCall iApiCall = retrofit.create(IApiCall.class);
        final Call<Boolean> removeProduct = iApiCall.removeProductCartId(userId1,productId);
        removeProduct.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {

                //if the response is true . this says the product is deleted.
                //check if the condition checking is right
                    //the response body is true..
                    //refresh the cart view again
                    IApiCall iApiCall1 = retrofit.create(IApiCall.class);
                    final Call<List<String>> getCartId = iApiCall1.getCartId(userId1);
                    // this is the call to fet the cart product list
                    getCartId.enqueue(new Callback<List<String>>() {
                        @Override
                        public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                           // url1 = "http://10.177.2.201:8080";
                            IApiCall iApiCall = retrofit2.create(IApiCall.class);
                            final Call<List<ProductDTO>> getCartProducts = iApiCall.getCartProducts(response.body());
                            getCartProducts.enqueue(new Callback<List<ProductDTO>>() {
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
                        public void onFailure(Call<List<String>> call, Throwable t) {

                        }
                    });
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(Cart_Activity.this, "failed to remove the product", Toast.LENGTH_SHORT).show();

            }
        });

//
    }
}
