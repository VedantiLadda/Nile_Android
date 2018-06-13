package com.example.vedantiladda.ecommerce;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        Log.i("application", "onCreate:of launch ");
//        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(myToolbar);

        SharedPreferences sharedPreferences = getSharedPreferences("training", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("userId","1234");
//                editor.putString("lastName",userLastName);
//                editor.putString("Date of Birth",dateOfBirth);
//                editor.putString("join date",joinDate);
                editor.apply();

        Log.i("application", "onCreate of launch activity next we have the intent : ");

        Intent intent = new Intent(this,Cart_Activity.class);
        startActivity(intent);


    }
}
