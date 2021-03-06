package com.example.vedantiladda.ecommerce.cart;

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
import com.example.vedantiladda.ecommerce.LoginAndSignup.LoginActivity;
import com.example.vedantiladda.ecommerce.LogoutAndEditProfile.LogoutActivity;
import com.example.vedantiladda.ecommerce.R;
import com.example.vedantiladda.ecommerce.buy.BuyActivity;
import com.example.vedantiladda.ecommerce.model.ProductDTO;
import com.example.vedantiladda.ecommerce.product.ProductListActivity;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddCartActivity extends BaseActivity implements CartInterface {

    static String url = "http://10.177.2.196:8080";

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<ProductDTO> productList = new ArrayList<>();
    OkHttpClient client = new OkHttpClient.Builder().build();
    String userId;
    final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.177.2.196:8089") // need to change the url
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();

    final Retrofit retrofit2 = new Retrofit.Builder()
            .baseUrl("http://10.177.2.196:8080") // need to change the url
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();

    // we will get the user id.. check if there is user id in shared preferences... assign it to the string initialized




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        userId = sharedPreferences.getString("userId","");

        final Intent login = new Intent(AddCartActivity.this, LoginActivity.class);
        final Intent cartActivity = new Intent(AddCartActivity.this, Cart_Activity.class);
        final Intent userPage = new Intent(AddCartActivity.this, LogoutActivity.class);
        toolbarButtons(login,cartActivity,userPage);
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
        Double productPrice = intent1.getDoubleExtra("price",0.0);
        Integer productQuantity = intent1.getIntExtra("quantity",1);


        IApiCall iApiCall = retrofit.create(IApiCall.class);
        final Call<Void> addProduct = iApiCall.addProduct(userId,productId,merchantId,productQuantity,productPrice);
        addProduct.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i("application", "trying to refresh the view ");

                final IApiCall iApiCall2 = retrofit.create(IApiCall.class);
                final Call<List<String>> getAll = iApiCall2.getCartId(userId);
                getAll.enqueue(new Callback<List<String>>() {
                    @Override
                    public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                        final IApiCall iApiCall = retrofit2.create(IApiCall.class);
                        final Call<List<ProductDTO>> getCartProducts = iApiCall.getCartProducts(response.body());
                        getCartProducts.enqueue(new Callback<List<ProductDTO>>() {
                            @Override
                            public void onResponse(Call<List<ProductDTO>> call, Response<List<ProductDTO>> response) {
                                productList.clear();
                                productList.addAll(response.body());
                                TextView cart=findViewById(R.id.cart);
                                cart.setText(response.body().size()+"");

                                mAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(Call<List<ProductDTO>> call, Throwable t) {
                                    Log.d("API",t.getMessage());

                            }
                        });


                    }

                    @Override
                    public void onFailure(Call<List<String>> call, Throwable t) {
                        Log.d("API",t.getMessage());

                    }
                });
//                getAll.enqueue(new Callback<List<ProductDTO>>() {
//                    @Override
//                    public void onResponse(Call<List<ProductDTO>> call, Response<List<ProductDTO>> response) {
////                        productList.clear();
////                        productList.addAll(response.body());
////                        mAdapter.notifyDataSetChanged();
//                        IApiCall iApiCall = retrofit.create(IApiCall.class);
//
//
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<ProductDTO>> call, Throwable t) {
//
//                    }
//                });

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("API",t.getMessage());


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
                    final Call<List<String>> getCartId = iApiCall1.getCartId(userId1);
                    // this is the call to fet the cart product list
                    getCartId.enqueue(new Callback<List<String>>() {
                        @Override
                        public void onResponse(Call<List<String>> call, Response<List<String>> response) {

                            url = "http://10.177.2.201:8080";
                            IApiCall iApiCall = retrofit2.create(IApiCall.class);
                            final Call<List<ProductDTO>> getCartProducts = iApiCall.getCartProducts(response.body());
                            getCartProducts.enqueue(new Callback<List<ProductDTO>>() {
                                @Override
                                public void onResponse(Call<List<ProductDTO>> call, Response<List<ProductDTO>> response) {
                                    productList.clear();
                                    productList.addAll(response.body());
                                    TextView cart=findViewById(R.id.cart);
                                    cart.setText(response.body().size()+"");
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

            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(AddCartActivity.this, "failed to remove the product", Toast.LENGTH_SHORT).show();

            }
        });
    }
}