package com.example.vedantiladda.ecommerce;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CredentialsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credentials);

        TextView textView14 = findViewById(R.id.textView14);
        TextView textView15 = findViewById(R.id.textView15);

        SharedPreferences sharedPreferences = getSharedPreferences("Sample", Context.MODE_PRIVATE);

        String textValue14 = textView14.getText().toString();
        String textValue15 = textView15.getText().toString();


        textView14.setText(sharedPreferences.getString("Email",textValue14));
        textView15.setText(sharedPreferences.getString("Password",textValue15));
    }
}
