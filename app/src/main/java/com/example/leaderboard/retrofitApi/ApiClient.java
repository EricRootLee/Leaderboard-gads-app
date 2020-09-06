package com.example.leaderboard.retrofitApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

        private static Retrofit getRetrofit(){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(" https://gadsapi.herokuapp.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return retrofit;
        }
}

