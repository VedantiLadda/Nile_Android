package com.example.vedantiladda.ecommerce;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.vedantiladda.ecommerce.LoginAndSignup.LoginActivity;
import com.example.vedantiladda.ecommerce.LogoutAndEditProfile.LogoutActivity;
import com.example.vedantiladda.ecommerce.cart.Cart_Activity;
import com.example.vedantiladda.ecommerce.product.ProductDetailsActivity;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class BaseActivity extends AppCompatActivity {
    public String LoginStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbar);


    }

    public void toolbarButtons(final Intent login, final Intent cartActivity, final Intent userPage) {
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        final String loginStatus = sharedPreferences.getString("userId", " ");
        TextView signin = findViewById(R.id.login);
        final TextView cart = findViewById(R.id.cart);
        if (loginStatus.equals(" ")) {
            signin.setText("SIGN IN");
            cart.setText("0");

            //signin.setCompoundDrawables();

        } else {
            signin.setText(sharedPreferences.getString("firstName", "  ") + " ");
            signin.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            signin.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.loginicon, 0);
            OkHttpClient client = new OkHttpClient.Builder().build();
            final Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.177.2.196:8080") // need to change the url
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
            final Call<Integer> count = retrofit.create(IApiCall.class).getCartCount(loginStatus);
            count.enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    cart.setText(response.body().toString());
                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {

                }
            });


        }

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loginStatus.equals(" ")) {

                    startActivity(login);
                } else {

                    startActivity(cartActivity);
                }

            }
        });


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loginStatus.equals(" ")) {

                    startActivity(login);
                } else {

                    startActivity(userPage);
                }


            }
        });


    }
}
