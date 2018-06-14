package com.example.vedantiladda.ecommerce.LogoutAndEditProfile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.vedantiladda.ecommerce.LaunchActivty;
import com.example.vedantiladda.ecommerce.R;
import com.example.vedantiladda.ecommerce.model.UserEntity;

public class LogoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

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
                Intent intent1 = new Intent(getApplicationContext(),LaunchActivty.class);
                startActivity(intent1);

            }
        });

        Button logout_button = findViewById(R.id.logout_button);
        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserEntity userEntity1 = new UserEntity();
                SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("email");
                editor.remove("password");
                editor.remove("id");
                editor.commit();

                Toast.makeText(getApplicationContext(),"Succesfully logged out!",Toast.LENGTH_SHORT).show();

                //Put the updated launcher activity here
                Intent intent = new Intent(LogoutActivity.this,LaunchActivty.class);
                startActivity(intent);
                //finish();

            }
        });
    }
}
