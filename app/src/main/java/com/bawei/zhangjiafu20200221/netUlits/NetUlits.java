package com.bawei.zhangjiafu20200221;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author: 张家辅
 * @date: 2020/02/26
 */
public class NetUlits {
    private static NetUlits netUlits;
    private final Retrofit retrofit;

    private NetUlits() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        retrofit = new Retrofit.Builder().client(okHttpClient)
                .baseUrl("http://mobile.bwstudent.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetUlits getInstance() {
        if (netUlits==null){
            synchronized (NetUlits.class){
                if(netUlits==null){
                    netUlits=new NetUlits();
                }
            }
        }
        return netUlits;
    }
    public <T>T create(Class<T> tClass){
        return retrofit.create(tClass);
    }
}
