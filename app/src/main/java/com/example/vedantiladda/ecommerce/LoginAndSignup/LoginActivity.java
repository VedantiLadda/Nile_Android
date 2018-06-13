package com.example.vedantiladda.ecommerce.LoginAndSignup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vedantiladda.ecommerce.CredentialsActivity;
import com.example.vedantiladda.ecommerce.R;
import com.example.vedantiladda.ecommerce.model.UserEntity;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private String url = "https://lit-anchorage-36944.herokuapp.com/";

    public boolean isValidEmailAddress2(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        final EditText editText = findViewById(R.id.editText);
        final EditText editText9 = findViewById(R.id.editText9);


        TextView signupScreen = (TextView) findViewById(R.id.link_to_signup);

        signupScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(i);
            }
        });

        OkHttpClient client = new OkHttpClient.Builder().build();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        Button login_button = findViewById(R.id.login_button);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editTextValue = editText.getText().toString();
                String editTextValue9 = editText9.getText().toString();


                if(!isValidEmailAddress2(editText.getText().toString().trim())){
                    editText.setError("Enter a valid email id!");
                    return;
                }

                if (editText9.getText().toString().isEmpty()) {
                    editText9.setError("Required field!");
                    return;
                }
                UserEntity userEntity = new UserEntity();

                userEntity.setEmail(editText.getText().toString());
                userEntity.setPassword(editText9.getText().toString());

                PostAll login = retrofit.create(PostAll.class);
                Call<UserEntity> loginCall = login.loginDetails(userEntity);

                loginCall.enqueue(new Callback<UserEntity>() {
                    @Override
                    public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
                        if(response.body().getEmail() == null || response.body().getPassword()==null){
                            Toast.makeText(getApplicationContext(), "please enter valid credentials!", Toast.LENGTH_SHORT).show();

                        }
                        else {
                            Toast.makeText(getApplicationContext(), "successfully logged in!", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<UserEntity> call, Throwable t) {
                        Log.d("hey",t.getMessage());
                        System.out.println(t);
                        Toast.makeText(getApplicationContext(), "login failed!", Toast.LENGTH_SHORT).show();

                    }
                });

                SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Email", editTextValue);
                editor.putString("Password", editTextValue9);

                Intent intent = new Intent(LoginActivity.this,CredentialsActivity.class);
                //intent.putExtra("Email",editTextValue);
                //intent.putExtra("Password",editTextValue9);
                startActivity(intent);
            }
        });
    }
}
