package com.example.cardie.RetrofitClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient
{
    private static Retrofit instance;
    public static Retrofit getInstance()
    {
        if (instance==null)
        {
            instance = new Retrofit.Builder()
                    .baseUrl(API.LOCAL_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instance;
    }
}
