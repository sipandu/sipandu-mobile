package com.sipanduteam.sipandu.api;

import com.sipanduteam.sipandu.model.user.UserLoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface BaseApi {

    //auth stuff

    @FormUrlEncoded
    @POST("Login")
    Call<UserLoginResponse> loginUser(
            @Field("email") String username,
            @Field("password") String password
    );

}
