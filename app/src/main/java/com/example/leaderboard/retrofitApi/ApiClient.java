package com.example.leaderboard.retrofitApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static Retrofit retrofit;
    private static final String BASE_URL = "https://gadsapi.herokuapp.com";
    public static final String GOOGLE_DOCS_BASE_URL ="https://docs.google.com/forms/d/e/";

    public static ApiClient getClient(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        ApiClient apiClient = retrofit.create(ApiClient.class);
        return apiClient;
    }
    public static ApiClient getGoogleDocsClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(GOOGLE_DOCS_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        ApiClient apiClient = retrofit.create(ApiClient.class);
        return apiClient;
    }
}

