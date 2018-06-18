package com.example.vedantiladda.ecommerce.LogoutAndEditProfile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.vedantiladda.ecommerce.BaseActivity;
import com.example.vedantiladda.ecommerce.LaunchActivity;
import com.example.vedantiladda.ecommerce.LoginAndSignup.LoginActivity;
import com.example.vedantiladda.ecommerce.R;
import com.example.vedantiladda.ecommerce.cart.Cart_Activity;
import com.example.vedantiladda.ecommerce.model.UserEntity;
import com.example.vedantiladda.ecommerce.product.ProductListActivity;

public class LogoutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        final SharedPreferences sharedPreferences = getSharedPreferences("user",Context.MODE_PRIVATE);
        final Intent login = new Intent(LogoutActivity.this, LoginActivity.class);
        final Intent cartActivity = new Intent(LogoutActivity.this, Cart_Activity.class);
        final Intent userPage = new Intent(LogoutActivity.this, LogoutActivity.class);
        toolbarButtons(login,cartActivity,userPage);

        Button edit_profile_button = findViewById(R.id.edit_profile_button);
        edit_profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),EditProfileActivity.class);
                startActivity(i);
                //finish();


            }
        });

        Button home_button = findViewById(R.id.home_button);
        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(),LaunchActivity.class);
                startActivity(intent1);

            }
        });

        Button logout_button = findViewById(R.id.logout_button);
        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserEntity userEntity1 = new UserEntity();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("email");
                editor.remove("password");
                editor.remove("userId");
                editor.commit();

                Toast.makeText(getApplicationContext(),"Succesfully logged out!",Toast.LENGTH_SHORT).show();

                //Put the updated launcher activity here
                Intent intent = new Intent(LogoutActivity.this,LaunchActivity.class);
                startActivity(intent);
                //finish();

            }
        });
    }
}
