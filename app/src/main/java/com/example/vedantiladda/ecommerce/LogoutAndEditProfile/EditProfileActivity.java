package com.example.vedantiladda.ecommerce.LogoutAndEditProfile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
//import android.util.Log;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vedantiladda.ecommerce.BaseActivity;
import com.example.vedantiladda.ecommerce.LoginAndSignup.LoginActivity;
import com.example.vedantiladda.ecommerce.LoginAndSignup.PostAll;
import com.example.vedantiladda.ecommerce.R;
import com.example.vedantiladda.ecommerce.cart.Cart_Activity;
import com.example.vedantiladda.ecommerce.model.AddressEntity;
import com.example.vedantiladda.ecommerce.model.UserEntity;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditProfileActivity extends BaseActivity {

    private Retrofit retrofit;
    private String url = "https://lit-anchorage-36944.herokuapp.com/";

    /*public boolean isValidEmailAddress3(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile_activity);
        final Intent login = new Intent(EditProfileActivity.this, LoginActivity.class);
        final Intent cartActivity = new Intent(EditProfileActivity.this, Cart_Activity.class);
        final Intent userPage = new Intent(EditProfileActivity.this, LogoutActivity.class);
        toolbarButtons(login,cartActivity,userPage);
        final EditText editText4 = findViewById(R.id.editText4);
        final EditText editText5 = findViewById(R.id.editText5);
        //final EditText editText6 = findViewById(R.id.editText6);
        final EditText editText7 = findViewById(R.id.editText7);
        final EditText editText8 = findViewById(R.id.editText8);
        final EditText editText12 = findViewById(R.id.editText12);
        final EditText editText13 = findViewById(R.id.editText13);
        final EditText editText14 = findViewById(R.id.editText14);
        final EditText editText15 = findViewById(R.id.editText15);
        final EditText editText16 = findViewById(R.id.editText16);
        final EditText editText17 = findViewById(R.id.editText17);

        final String editTextValue4 = editText4.getText().toString();
        final String editTextValue5 = editText5.getText().toString();
        Log.d("aaaaaaa",editTextValue4);
        //final String editTextValue6 = editText6.getText().toString();
        final String editTextValue7 = editText7.getText().toString();
        final String editTextValue8 = editText8.getText().toString();
        final String editTextValue12 = editText12.getText().toString();
        final String editTextValue13 = editText13.getText().toString();
        final String editTextValue14 = editText14.getText().toString();
        final String editTextValue15 = editText15.getText().toString();
        final String editTextValue16 = editText16.getText().toString();
        final String editTextValue17 = editText17.getText().toString();

        final     SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        Log.d("API",sharedPreferences.getString("firstName","firstName"));
        editText4.setText(sharedPreferences.getString("firstName",editTextValue4));
        editText5.setText(sharedPreferences.getString("lastName",editTextValue5));
        //editText6.setText(sharedPreferences.getString("email",editTextValue6));
        editText7.setText(sharedPreferences.getString("password",editTextValue7));
        editText8.setText(sharedPreferences.getString("mobile",editTextValue8));
        editText12.setText(sharedPreferences.getString("line1",editTextValue12));
        editText13.setText(sharedPreferences.getString("line2",editTextValue13));
        editText14.setText(sharedPreferences.getString("zip",editTextValue14));
        editText15.setText(sharedPreferences.getString("city",editTextValue15));
        editText16.setText(sharedPreferences.getString("landmark",editTextValue16));
        editText17.setText(sharedPreferences.getString("state",editTextValue17));

        OkHttpClient client = new OkHttpClient.Builder().build();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        Button edit_profile_button = findViewById(R.id.save_button);
        edit_profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final UserEntity userEntity = new UserEntity();
                final AddressEntity addressEntity = new AddressEntity();

                userEntity.setFirstName(editText4.getText().toString());
                userEntity.setLastName(editText5.getText().toString());
                userEntity.setPassword(editText7.getText().toString());
                userEntity.setMobile(editText8.getText().toString());

                //userEntity.setEmail(sharedPreferences.getString("email",editTextValue6));
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

                /*if (!isValidEmailAddress3(editText6.getText().toString().trim())) {
                    editText6.setError("Enter a valid email id!");
                    return;
                }

                userEntity.setEmail(editText6.getText().toString());*/

                if (editText7.getText().toString().isEmpty()) {
                    editText7.setError("Required field!");
                    return;
                }
                userEntity.setPassword(editText7.getText().toString());

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


                            PostAll editprofile = retrofit.create(PostAll.class);
                            Call<UserEntity> editprofileCall = editprofile.editProfile(userEntity);

                            editprofileCall.enqueue(new Callback<UserEntity>() {
                                @Override
                                public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
                                    if(response.body() == null){
                                        Toast.makeText(getApplicationContext(),"please enter details!",Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(),"Successfully edited!",Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(EditProfileActivity.this,LogoutActivity.class);
                                        UserEntity userEntity1=response.body();
                                        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("email", userEntity1.getEmail());
                                        editor.putString("password", userEntity1.getPassword());
                                        editor.putString("userId", userEntity1.getUserId());
                                        editor.putString("firstName", userEntity1.getFirstName());
                                        editor.putString("lastName", userEntity1.getLastName());
                                        editor.putString("mobile", userEntity1.getMobile());
                                        editor.putString("line1", userEntity1.getAddressEntity().getLine1());
                                        editor.putString("line2", userEntity1.getAddressEntity().getLine2());
                                        editor.putString("zip", userEntity1.getAddressEntity().getZip());
                                        editor.putString("city", userEntity1.getAddressEntity().getCity());
                                        editor.putString("landmark", userEntity1.getAddressEntity().getLandmark());
                                        editor.putString("state", userEntity1.getAddressEntity().getState());
                                        editor.apply();
                                        Log.d("API",userEntity1.getFirstName());
                                        startActivity(intent);
                                        finish();
                                    }
                                }

                                @Override
                                public void onFailure(Call<UserEntity> call, Throwable t) {
                                    Toast.makeText(getApplicationContext(), "failed to edit!", Toast.LENGTH_SHORT).show();
                                }
                            });






            }
        });


    }
}
