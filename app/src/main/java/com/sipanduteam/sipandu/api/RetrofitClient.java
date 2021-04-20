package com.sipanduteam.sipandu.api;

import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String baseURL = "http://192.168.1.3:8000/api/mobileuser/";
    private static retrofit2.Retrofit retrofit;

    public static retrofit2.Retrofit buildRetrofit() {
        if(retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
