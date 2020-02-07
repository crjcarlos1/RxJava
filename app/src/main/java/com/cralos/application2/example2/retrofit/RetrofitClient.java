package com.cralos.application2.example2.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofitInstance = null;

    private RetrofitClient() {
    }

    public synchronized static Retrofit getRetrofitInstance() {
        if (retrofitInstance == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .readTimeout(45, TimeUnit.SECONDS)
                    .connectTimeout(45, TimeUnit.SECONDS)
                    .build();
            retrofitInstance = new Retrofit.Builder()
                    .baseUrl(EndPoints.URL_SERVER)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofitInstance;
    }


}
