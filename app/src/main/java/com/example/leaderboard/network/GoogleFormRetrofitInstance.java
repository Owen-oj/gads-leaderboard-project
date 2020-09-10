package com.example.leaderboard.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GoogleFormRetrofitInstance {

    private static Retrofit retrofit;
    private static final String GOOGLE_FORM_BASE_URL = "https://docs.google.com/forms/d/e/";

    public static Retrofit getGoogleFormRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(GOOGLE_FORM_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
