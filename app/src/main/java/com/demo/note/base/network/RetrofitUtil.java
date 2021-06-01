package com.demo.note.base.network;


import com.demo.note.app.NoteApp;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {

    private final static String BASE_URL =  NoteApp.IP;
    public static volatile Retrofit retrofit;

    private RetrofitUtil() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(10, TimeUnit.SECONDS);
        // 初始化retrofit
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    public static Retrofit getRetrofit() {
        getInstance();
        return retrofit;
    }

    private static class SingletonHolder {
        private static final RetrofitUtil INSTANCE = new RetrofitUtil();
    }

    // 获取单例
    private static RetrofitUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
