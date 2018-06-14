package com.example.vedantiladda.ecommerce.LoginAndSignup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.vedantiladda.ecommerce.R;
import com.example.vedantiladda.ecommerce.model.AddressEntity;
import com.example.vedantiladda.ecommerce.model.UserEntity;
import com.example.vedantiladda.ecommerce.Utility.Validation;

import okhttp3.OkHttpClient;
//import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignupActivity extends AppCompatActivity {
    private Validation validation;
    private Retrofit retrofit;
    private String url = "https://lit-anchorage-36944.herokuapp.com/";

    public boolean isValidEmailAddress1(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        //final EditText editText3 = findViewById(R.id.editText3);
        final EditText editText4 = findViewById(R.id.editText4);
        final EditText editText5 = findViewById(R.id.editText5);
        final EditText editText6 = findViewById(R.id.editText6);
        final EditText editText7 = findViewById(R.id.editText7);
        final EditText editText8 = findViewById(R.id.editText8);
        final EditText editText12 = findViewById(R.id.editText12);
        final EditText editText13 = findViewById(R.id.editText13);
        final EditText editText14 = findViewById(R.id.editText14);
        final EditText editText15 = findViewById(R.id.editText15);
        final EditText editText16 = findViewById(R.id.editText16);
        final EditText editText17 = findViewById(R.id.editText17);

        TextView loginScreen = (TextView) findViewById(R.id.link_to_login);

        loginScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        OkHttpClient client = new OkHttpClient.Builder().build();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();


        Button signup_button = findViewById(R.id.signup_button);

        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String editTextValue3 = editText3.getText().toString();
                String editTextValue4 = editText4.getText().toString();
                String editTextValue5 = editText5.getText().toString();
                String editTextValue6 = editText6.getText().toString();
                String editTextValue7 = editText7.getText().toString();
                String editTextValue8 = editText8.getText().toString();
                String editTextValue12 = editText12.getText().toString();
                String editTextValue13 = editText13.getText().toString();
                String editTextValue14 = editText14.getText().toString();
                String editTextValue15 = editText15.getText().toString();
                String editTextValue16 = editText16.getText().toString();
                String editTextValue17 = editText17.getText().toString();

                final UserEntity userEntity = new UserEntity();
                final AddressEntity addressEntity = new AddressEntity();

                userEntity.setFirstName(editText4.getText().toString());
                userEntity.setLastName(editText5.getText().toString());
                userEntity.setPassword(editText7.getText().toString());
                userEntity.setMobile(editText8.getText().toString());
                userEntity.setEmail(editText6.getText().toString());
                addressEntity.setLine1(editText12.getText().toString());
                addressEntity.setLine2(editText13.getText().toString());
                addressEntity.setZip(editText14.getText().toString());
                addressEntity.setCity(editText15.getText().toString());
                addressEntity.setLandmark(editText16.getText().toString());
                addressEntity.setState(editText17.getText().toString());
                userEntity.setAddressEntity(addressEntity);

                if (editText4.getText().toString().isEmpty()) {
                    editText4.setError("Required field!");
                    return;
                }
                userEntity.setFirstName(editText4.getText().toString());

                if (editText5.getText().toString().isEmpty()) {
                    editText5.setError("Required field!");
                    return;
                }
                userEntity.setLastName(editText5.getText().toString());

                if (!isValidEmailAddress1(editText6.getText().toString().trim())) {
                    editText6.setError("Enter a valid email id!");
                    return;
                }

                userEntity.setEmail(editText6.getText().toString());

                if (editText7.getText().toString().isEmpty()) {
                    editText7.setError("Required field!");
                    return;

                }
                else if(editText7.getText().toString().length()<4){
                    editText7.setError("Minimum 4 characters required!");
                }
                else{userEntity.setPassword(editText7.getText().toString());}

                if (editText8.getText().toString().isEmpty()) {
                    editText8.setError("Required field!");
                    return;
                }
                userEntity.setMobile(editText8.getText().toString());

                if (editText12.getText().toString().isEmpty()) {
                    editText12.setError("Required field!");
                    return;
                }
                addressEntity.setLine1(editText12.getText().toString());

                if (editText13.getText().toString().isEmpty()) {
                    editText13.setError("Required field!");
                    return;
                }
                addressEntity.setLine2(editText13.getText().toString());

                if (editText14.getText().toString().isEmpty()) {
                    editText14.setError("Required field!");
                    return;
                }
                addressEntity.setZip(editText14.getText().toString());

                if (editText15.getText().toString().isEmpty()) {
                    editText15.setError("Required field!");
                    return;
                }
                addressEntity.setCity(editText15.getText().toString());

                if (editText16.getText().toString().isEmpty()) {
                    editText16.setError("Required field!");
                    return;
                }
                addressEntity.setLandmark(editText16.getText().toString());

                if (editText17.getText().toString().isEmpty()) {
                    editText17.setError("Required field!");
                    return;
                }
                addressEntity.setState(editText17.getText().toString());

                PostAll emailValidity = retrofit.create(PostAll.class);
                Call<Boolean> emailValidityCall = emailValidity.emailExists(userEntity.getEmail());

                emailValidityCall.enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        Log.d("API", "call");

                        System.out.println(response.body());
                        if (response.body()) {
                                Log.d("API","succ");
                            Toast.makeText(getApplicationContext(), "email id already exists!", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            Log.d("API", "call2");

                            PostAll postAll = retrofit.create(PostAll.class);
                            Call<Boolean> postAllCall = postAll.saveCredentials(userEntity);

                            postAllCall.enqueue(new Callback<Boolean>() {
                                @Override
                                public void onResponse(Call<Boolean> call, retrofit2.Response<Boolean> response) {
                                    if (response.body() == true) {
                                        Toast.makeText(getApplicationContext(), "hi, you are successfully added!", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                                        startActivity(i);
                                    } else {
                                        Toast.makeText(getApplicationContext(), "failed to signup!", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Boolean> call, Throwable t) {
                                    Log.d("API", t.getMessage());
                                    Toast.makeText(getApplicationContext(), "signup error!", Toast.LENGTH_SHORT).show();

                                }
                            });

                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {

                        Toast.makeText(getApplicationContext(), "email successfully entered!", Toast.LENGTH_SHORT);

                    }
                });


              /*  PostAll postAll = retrofit.create(PostAll.class);
                Call<Boolean> postAllCall = postAll.saveCredentials(userEntity);

                postAllCall.enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, retrofit2.Response<Boolean> response) {
                        if(response.body() == true) {
                            Toast.makeText(getApplicationContext(), "hi, you are successfully added!", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(i);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "failed to signup!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        Log.d("API",t.getMessage());
                        Toast.makeText(getApplicationContext(), "signup error!", Toast.LENGTH_SHORT).show();

                    }
                });*/

            }

        });

    }
}
