package com.dxt.retail4sunmi.network.http;

import retrofit2.Retrofit;

/**
 * 网络请求工具类---使用的是全局配置的变量
 */

public class GlobalRxHttp {

    private static GlobalRxHttp instance;

    public static GlobalRxHttp getInstance() {

        if (instance == null) {
            synchronized (GlobalRxHttp.class) {
                if (instance == null) {
                    instance = new GlobalRxHttp();
                }
            }

        }
        return instance;
    }



    /**
     * 全局的 retrofit
     */
    private static Retrofit getGlobalRetrofit() {
        return RetrofitClient.getInstance().getRetrofit();
    }


    /**
     * 使用全局变量的请求
     */
    public static <K> K createGApi(final Class<K> cls) {
        return getGlobalRetrofit().create(cls);
    }


}
