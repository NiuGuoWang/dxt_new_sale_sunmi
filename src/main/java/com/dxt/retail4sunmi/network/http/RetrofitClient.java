package com.dxt.retail4sunmi.network.http;


import com.dxt.retail4sunmi.constant.NetConstant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * RetrofitClient工具类
 */

public class RetrofitClient {


    private static RetrofitClient instance;

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    private Retrofit mRetrofit;


    private RetrofitClient() {


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        OkHttpClient okHttpClient = HttpClient.getInstance().getOkHttpClient();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(NetConstant.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient).build();
    }


    public static RetrofitClient getInstance() {

        if (instance == null) {
            synchronized (RetrofitClient.class) {
                if (instance == null) {
                    instance = new RetrofitClient();
                }
            }
        }
        return instance;
    }


}
