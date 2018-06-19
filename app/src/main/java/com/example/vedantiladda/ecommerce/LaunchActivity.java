package com.example.vedantiladda.ecommerce;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vedantiladda.ecommerce.LoginAndSignup.LoginActivity;
import com.example.vedantiladda.ecommerce.LogoutAndEditProfile.LogoutActivity;
import com.example.vedantiladda.ecommerce.cart.Cart_Activity;
import com.example.vedantiladda.ecommerce.model.Category;
import com.example.vedantiladda.ecommerce.product.ProductListActivity;
import com.example.vedantiladda.ecommerce.search.SearchActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LaunchActivity extends BaseActivity implements LaunchAdaptor.LaunchCommunicator{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Category> categoryList = new ArrayList<>();
    private String url = "http://10.177.2.201:8080";
    OkHttpClient client = new OkHttpClient.Builder().build();
    final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();
    //    String LoginStatus;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] XMEN= {R.drawable.ph5,R.drawable.ph2,R.drawable.ph3,R.drawable.ph4,R.drawable.ph6};
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        init();

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        final Intent login = new Intent(LaunchActivity.this, LoginActivity.class);
        final Intent cartActivity = new Intent(LaunchActivity.this, Cart_Activity.class);
        final Intent userPage = new Intent(LaunchActivity.this, LogoutActivity.class);
        toolbarButtons(login, cartActivity, userPage);
        mRecyclerView = (RecyclerView) findViewById(R.id.CategoryRecycler);
        final Intent search = new Intent(LaunchActivity.this, SearchActivity.class);
        ImageButton searchButton = (ImageButton) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView searchText = findViewById(R.id.editText);
                Log.d("search", searchText.getText().toString());
                search.putExtra("search", searchText.getText().toString());
                startActivity(search);

            }
        });
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
//        mLayoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(mLayoutManager);

        mLayoutManager = new GridLayoutManager(this, 3, 1, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        // specify an adapter (see also next example)
        mAdapter = new LaunchAdaptor(categoryList, LaunchActivity.this);
        mRecyclerView.setAdapter(mAdapter);

        GetCategory();




    }
    private void init() {
        for(int i=0;i<XMEN.length;i++)
            XMENArray.add(XMEN[i]);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new MyAdapter(LaunchActivity.this,XMENArray));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);


        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == XMEN.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 10000, 5000);
    }

    @Override
    public void onClickTextView(String name) {
        Intent i = new Intent(LaunchActivity.this, ProductListActivity.class);
        i.putExtra("categoryName", name);
        startActivity(i);


    }

    public void GetCategory(){

        IApiCall iApiCall = retrofit.create(IApiCall.class);
        final Call<List<Category>> getAllCall = iApiCall.getAllCategories();
        getAllCall.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                categoryList.clear();
                categoryList.addAll(response.body());
                mAdapter.notifyDataSetChanged();
                Toast.makeText(LaunchActivity.this, "received", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.d("API",t.getMessage());
                Toast.makeText(LaunchActivity.this, "failed", Toast.LENGTH_LONG).show();

            }

        });
    }
}

