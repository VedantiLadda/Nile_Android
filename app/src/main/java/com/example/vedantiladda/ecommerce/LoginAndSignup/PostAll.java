package com.example.vedantiladda.ecommerce.LoginAndSignup;

import com.example.vedantiladda.ecommerce.model.UserEntity;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PostAll {
    @POST("user/register")
    Call<Boolean> saveCredentials(@Body UserEntity userEntity);

    @POST("user/authenticate")
    Call<UserEntity> loginDetails(@Body UserEntity userEntity);

    @GET("user/emailExists/{email}")
    Call<Boolean> emailExists(@Path("email") String email);

    @POST("user/editProfile")
    Call<UserEntity> editProfile(@Body UserEntity userEntity);
}
