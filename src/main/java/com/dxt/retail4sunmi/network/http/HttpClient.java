package com.dxt.retail4sunmi.network.http;


import com.dxt.retail4sunmi.network.interceptor.LoggingInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;


/**
 * Created by star on 2017/9/7 0007.
 */

public class HttpClient {

    private static final int CONNECT_TIMEOUT = 10;  //连接超时
    private static final int READ_TIMEOUT = 25;    //读取超时
    private static final int WRITE_TIMEOUT = 25;   //写入超时

    private static HttpClient instance;


    public static HttpClient getInstance() {
        if (instance == null) {
            synchronized (SingleRxHttp.class) {
                if (instance == null) {
                    instance = new HttpClient();
                }
            }

        }
        return instance;
    }


    /**
     * 获取单个 OkHttpClient.Builder
     * <p>
     * 0     * @return
     */
    public OkHttpClient getOkHttpClient() {

        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);
//        if (BuildConfig.DEBUG)
            okHttpBuilder.addInterceptor(new LoggingInterceptor()); //请求信息的打印

        okHttpBuilder.build();
//      .addInterceptor(new HeaderInterceptor()) //添加 header
//      .sslSocketFactory(customHttpsTrust.sSLSocketFactory, customHttpsTrust.x509TrustManager)// https 配置
//      .retryOnConnectionFailure(true);  //错误重连

        return okHttpBuilder.build();
    }
}
